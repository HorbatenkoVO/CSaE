package service;

import lombok.SneakyThrows;
import org.apache.hc.client5.http.utils.Hex;
import org.testng.asserts.Assertion;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static helpers.ConfigHelper.terminalConfig;

public class CashBoxSocketService implements CashBoxService {
    private String hostname;
    private final int port = 2000;
    private Socket clientSocket;
    private static final Integer TIMEOUT = 65;

    @Override
    public void handShake() {
        String json = "{\"method\":\"PingDevice\",\"step\":0}";
        List<String> ips = terminalConfig().getEcrConnectIps();
        boolean isPortFound = false;

        for (String ip : ips) {
            hostname = ip;

            try {
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                outputStream.write(0);
                outputStream.write(json.getBytes());

                write(outputStream.toByteArray());
                String read = read();
                if (read.equals("{\"method\":\"PingDevice\",\"step\":0,\"params\":{\"code\":\"00\",\"responseCode\":\"0000\"},\"error\":false,\"errorDescription\":\"\"}") |
                        read.equals("{\"method\": \"PingDevice\", \"step\": 0, \"params\": {\"code\": \"00\", \"responseCode\": \"0000\"}, \"error\": false, \"errorDescription\": \"\"}")) {
                    System.out.println("Device found at " + hostname + ":" + port);
                    isPortFound = true;
                    break;
                } else {
                    System.out.println("Device not found at " + hostname + ":" + port);
                }

            } catch (UnknownHostException ex) {
                System.out.println("Server not found: " + ex.getMessage() + " " + hostname + ":" + port);
            } catch (IOException ex) {
                System.out.println("I/O error: " + ex.getMessage() + " " + hostname + ":" + port);
            }
        }

        if (!isPortFound) {
            new Assertion().assertEquals("Device not found", "Device found");
        }
    }

    @Override
    public void identify() {
        String json = "{\"method\":\"ServiceMessage\",\"step\":0,\"params\":{\"msgType\":\"identify\"}}";
        write(json);
        read();
    }

    @SneakyThrows
    @Override
    public void closePort() {
        clientSocket.close();
        clientSocket = null;
        System.out.println("close socket " + hostname + ":" + port);
    }

    @SneakyThrows
    @Override
    public boolean openPort() {
        clientSocket = new Socket(hostname, port);
        clientSocket.setSoTimeout(TIMEOUT * 1000);
        System.out.println("open socket " + hostname + ":" + port);
        return true;
    }

    @Override
    public void write(String json) {
        write(json.getBytes());
    }

    @SneakyThrows
    private void write(byte[] bytes) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        outputStream.write(bytes);
        outputStream.write(0);

        OutputStream output = clientSocket().getOutputStream();
        PrintWriter writer = new PrintWriter(output, true);
        writer.println(outputStream);
    }

    @Override
    public String read() {
        byte[] resultBuff = new byte[0];
        byte[] buff = new byte[1];
        int k;
        try {
            while ((k = clientSocket().getInputStream().read(buff, 0, 1)) > -1) {
                byte[] tbuff = new byte[resultBuff.length + k];
                System.arraycopy(resultBuff, 0, tbuff, 0, resultBuff.length);
                System.arraycopy(buff, 0, tbuff, resultBuff.length, k);
                resultBuff = tbuff;

                if (Hex.encodeHexString(resultBuff).endsWith("00")) {
                    break;
                }
            }
        } catch (IOException e) {
            clearBuffer();
            throw new RuntimeException(e);
        }

        String result = new String(resultBuff, StandardCharsets.UTF_8);
        result = result.substring(0, result.length() - 1);

        System.out.println(result);
        return result;
    }

    @Override
    public void clearBuffer() {
        closePort();
        openPort();
    }

    private Socket clientSocket() {
        if (clientSocket == null) {
            openPort();
        }
        return clientSocket;
    }
}

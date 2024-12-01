package service;

import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortList;
import lombok.SneakyThrows;

import java.io.ByteArrayOutputStream;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class CashBoxSerialService implements CashBoxService {
    private static SerialPort serialPort;
    private final static List<String> buffer = new ArrayList<>();
    private static final Integer TIMEOUT = 65;

    @SneakyThrows
    @Override
    public void handShake() {
        String json = "{\"method\":\"PingDevice\",\"step\":0}";
        for (String port : SerialPortList.getPortNames()) {
            System.out.println(port);

            serialPort = new SerialPort(port);

            if (!serialPort.isOpened() && openPort() && !port.equals("COM3")) {

                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                outputStream.write(0);
                outputStream.write(json.getBytes());
                byte[] result = outputStream.toByteArray();

                long deltaTime;
                long startTime = ZonedDateTime.now().toInstant().toEpochMilli();
                String trashFromComPort;
                do {
                    trashFromComPort = read(2);
                    System.out.println("read trash from preview tests: " + trashFromComPort);
                    long currentTime = ZonedDateTime.now().toInstant().toEpochMilli();
                    deltaTime = MILLISECONDS.toMillis(currentTime - startTime);
                } while (!trashFromComPort.isEmpty() & deltaTime > 6000);

                System.out.println(json);
                write(result);
                String read = read(5);
                if (read.equals("{\"method\":\"PingDevice\",\"step\":0,\"params\":{\"code\":\"00\",\"responseCode\":\"0000\"},\"error\":false,\"errorDescription\":\"\"}")
                        | read.equals("{\"method\": \"PingDevice\", \"step\": 0, \"params\": {\"code\": \"00\", \"responseCode\": \"0000\"}, \"error\": false, \"errorDescription\": \"\"}")) {
                    System.out.println("Device found at port " + port);
                    break;
                }
            }
            closePort();
        }
    }

    @Override
    public void identify() {
        String json = "{\"method\":\"ServiceMessage\",\"step\":0,\"params\":{\"msgType\":\"identify\"}}";
        openPort();
        write(json);
        read();
    }

    @SneakyThrows
    @Override
    public void closePort() {
        serialPort.closePort();
        System.out.println("close port " + serialPort.getPortName());
    }

    @SneakyThrows
    @Override
    public boolean openPort() {
        if (serialPort.openPort()) {
            serialPort.setParams(SerialPort.BAUDRATE_115200,
                    SerialPort.DATABITS_8,
                    SerialPort.STOPBITS_1,
                    SerialPort.PARITY_NONE);
            serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_RTSCTS_IN |
                    SerialPort.FLOWCONTROL_RTSCTS_OUT);
            serialPort.addEventListener(new PortReader(), SerialPort.MASK_RXCHAR);
            System.out.println("open port " + serialPort.getPortName());
            return true;
        } else {
            System.out.println("port " + serialPort.getPortName() + " not opened");
            return false;
        }
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
        byte[] result = outputStream.toByteArray();
        serialPort.writeBytes(result);
    }

    @Override
    public String read() {
        return read(TIMEOUT);
    }

    @SneakyThrows
    private String read(int timeOutSek) {
        String result = "";
        for (int i = 0; i < timeOutSek * 10; i++) {
            if (!buffer.isEmpty()) {
                result = buffer.get(0);
                buffer.remove(0);
                break;
            } else {
                Thread.sleep(100);
            }
        }
        System.out.println(result);
        return result;
    }

    @Override
    public void clearBuffer() {
        buffer.clear();
    }

    private static class PortReader implements SerialPortEventListener {
        @SneakyThrows
        public void serialEvent(SerialPortEvent event) {
            if (event.isRXCHAR() && event.getEventValue() > 0) {
                String data = serialPort.readString(event.getEventValue(), TIMEOUT * 1000);
                data = data.substring(0, data.length() - 1);
                buffer.add(data);
            }
        }
    }
}

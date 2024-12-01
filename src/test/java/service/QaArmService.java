package service;

import jssc.*;
import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class QaArmService {
    private static SerialPort serialPort;
    private final static List<String> buffer = new ArrayList<>();
    private final static int TIMEOUT = 15;

    @SneakyThrows
    public void handShake() {
        for (String port : SerialPortList.getPortNames()) {
            System.out.println(port);

            serialPort = new SerialPort(port);

            if (!serialPort.isOpened() && openPort() && !port.equals("COM3")) {
                TimeUnit.SECONDS.sleep(2);

                write("9998");
                String read = read(5);
                if (read.contains("QaArm")) {
                    System.out.println("Device found at port " + port);
                    break;
                } else {
                    closePort();
                }
            }
        }
    }

    public void write(String command) {
        try {
            System.out.println(">> " + command);
            serialPort.writeBytes(command.getBytes());
        } catch (SerialPortException ex) {
            System.out.println(ex);
        }
    }

    public String read() {
        return read(TIMEOUT);
    }

    @SneakyThrows
    public String read(int timeOutSek) {
        String result = "";
        for (int i = 0; i < timeOutSek * 10; i++) {
            if (buffer.size() > 0) {
                result = buffer.get(0);
                buffer.remove(0);
                break;
            } else {
                TimeUnit.MILLISECONDS.sleep(100);
            }
        }
        System.out.println("<< " + result);
        return result;
    }

    private boolean openPort() {
        try {
            if (serialPort.openPort()) {
                //Выставляем параметры
                serialPort.setParams(SerialPort.BAUDRATE_9600,
                        SerialPort.DATABITS_8,
                        SerialPort.STOPBITS_1,
                        SerialPort.PARITY_NONE);
                //Включаем аппаратное управление потоком
                serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_RTSCTS_IN |
                        SerialPort.FLOWCONTROL_RTSCTS_OUT);
                //Устанавливаем ивент лисенер и маску
                serialPort.addEventListener(new PortReader(), SerialPort.MASK_RXCHAR);
                System.out.println("open port " + serialPort.getPortName());
                return true;
            } else {
                System.out.println("port " + serialPort.getPortName() + " not opened");
                return false;
            }
        } catch (SerialPortException ex) {
            System.out.println(ex);
        }
        return false;
    }

    @SneakyThrows
    public void closePort() {
        serialPort.closePort();
        System.out.println("close port " + serialPort.getPortName());
    }

    private static class PortReader implements SerialPortEventListener {
        public void serialEvent(SerialPortEvent event) {
            if (event.isRXCHAR() && event.getEventValue() > 0) {
                try {
                    //Получаем ответ от устройства, обрабатываем данные и т.д.
                    String data = serialPort.readString(event.getEventValue(), TIMEOUT * 1000);
                    data = data.substring(0, data.length() - 1);
                    buffer.add(data);
                } catch (SerialPortException | SerialPortTimeoutException ex) {
                    System.out.println(ex);
                }
            }
        }
    }
}

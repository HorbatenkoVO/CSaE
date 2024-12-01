package service;

public interface CashBoxService {
    void handShake();
    void identify();
    void closePort();
    boolean openPort();
    void write(String json);
    String read();
    void clearBuffer();
}

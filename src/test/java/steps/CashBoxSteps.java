package steps;

import assertions.JsonAssertion;
import io.qameta.allure.Step;
import lombok.SneakyThrows;
import model.transaction.cashBox.CashBoxMessage;
import service.CashBoxSerialService;
import service.CashBoxService;
import service.CashBoxSocketService;
import service.ConverterService;

import java.util.concurrent.TimeUnit;

import static helpers.AttachmentsHelper.attachAsJson;

public class CashBoxSteps {
    private CashBoxService cashBoxService;
    private final ConverterService converter = new ConverterService();

    public CashBoxSteps(String interfaceAttr) {
        if (interfaceAttr.equals("serial")) {
            this.cashBoxService = new CashBoxSerialService();
        } else {
            this.cashBoxService = new CashBoxSocketService();
        }
    }

    public CashBoxSteps handShake() {
        cashBoxService.handShake();
        return this;
    }

    public CashBoxSteps identify() {
        cashBoxService.identify();
        return this;
    }

    public CashBoxSteps closePort() {
        cashBoxService.closePort();
        return this;
    }

    public void openPort() {
        cashBoxService.openPort();
    }


    public CashBoxSteps write(CashBoxMessage body) {
        String json = converter.getJson(body);
        write(json);
        return this;
    }

    @Step(">> Касса терминалу")
    public CashBoxSteps write(String json) {
        attachAsJson("write", json);
        System.out.println(json);
        cashBoxService.write(json);
        return this;
    }

    @Step("<< Терминал кассе")
    public JsonAssertion check() {
        String json = cashBoxService.read();
        attachAsJson("read", json);
        return new JsonAssertion(json);
    }

    public CashBoxSteps waite() {
        waite(2);
        return this;
    }

    @SneakyThrows
    @Step("Жду {sec} сек")
    public CashBoxSteps waite(Integer sec) {
        TimeUnit.SECONDS.sleep(sec);
        return this;
    }

    public void clearBuffer() {
        cashBoxService.clearBuffer();
    }
}

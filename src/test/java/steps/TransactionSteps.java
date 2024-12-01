package steps;

import io.qameta.allure.Step;
import lombok.SneakyThrows;
import model.transaction.checkupProcess.Checkup;
import service.TransactionService;

import java.util.concurrent.TimeUnit;

public class TransactionSteps {
    private final TransactionService transactionService = new TransactionService();

    @Step("Обновление settings_hash")
    public TransactionSteps updateSettingsHash() {
        String settingsHash = transactionService.given(checkup().hash("")).getValue("hash");

        if (settingsHash != null) {
            System.setProperty("settings.hash", settingsHash);
        }

        return this;
    }

    @SneakyThrows
    @Step("Жду {sec} сек")
    public TransactionSteps waitTime(Integer sec) {
        TimeUnit.SECONDS.sleep(sec);
        return this;
    }

    private Checkup checkup() {
        return new Checkup();
    }
}

package pages.payApp.cashBox;

import io.qameta.allure.Step;
import pages.payApp.BasePagePayApp;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static io.appium.java_client.AppiumBy.id;

public class CashBoxCorrectionPage extends BasePagePayApp {
    private final String TEXT_STATUS = "id/status";

    @Step("Перевіряю чи я на екрані касового суміщення")

    public boolean isOnPage() {
        return $(id(TEXT_STATUS)).has(text("очікую корекцію"));
    }

    @Step("Очікую екран корекції транзакції")
    public CashBoxCorrectionPage isLoaded() {
        $(id(TEXT_STATUS)).shouldHave(text("очікую корекцію"));
        return this;
    }
}

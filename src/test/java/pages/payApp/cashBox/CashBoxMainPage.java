package pages.payApp.cashBox;

import io.qameta.allure.Step;
import pages.payApp.BasePagePayApp;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static io.appium.java_client.AppiumBy.id;

public class CashBoxMainPage extends BasePagePayApp {
    private final String BUTTON_SETTINGS = "id/btn_cashboxSettings";

    @Step("Перевіряю чи я на екрані касового суміщення")
    public boolean isOnPage() {
        return $(id(BUTTON_SETTINGS)).isDisplayed();
    }

    public void updateSettings() {
        $(id(BUTTON_SETTINGS)).click();
    }

    public CashBoxMainPage isLoaded() {
        $(id(BUTTON_SETTINGS)).shouldBe(visible);
        return this;
    }
}

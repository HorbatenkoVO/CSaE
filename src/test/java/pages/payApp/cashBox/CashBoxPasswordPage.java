package pages.payApp.cashBox;

import io.qameta.allure.Step;
import pages.payApp.BasePagePayApp;

import static com.codeborne.selenide.Selenide.$;
import static io.appium.java_client.AppiumBy.id;

public class CashBoxPasswordPage extends BasePagePayApp {
    private final String BUTTON_ENTER = "id/btn_enter";
    private final String BUTTON_EXIT = "id/btn_cancel";

    @Step("Перевіряю чи я на екрані введення паролю")
    public boolean isOnPage() {
        return $(id(BUTTON_EXIT)).isDisplayed() &&
                $(id(BUTTON_ENTER)).isDisplayed() &&
                $(id("id/appCompatTextView")).isDisplayed();
    }

    public void exit() {
        $(id(BUTTON_EXIT)).click();
    }
}

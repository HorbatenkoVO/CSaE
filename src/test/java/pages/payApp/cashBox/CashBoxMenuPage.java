package pages.payApp.cashBox;

import io.qameta.allure.Step;
import pages.payApp.BasePagePayApp;

import static com.codeborne.selenide.Selenide.$;
import static org.openqa.selenium.By.xpath;

public class CashBoxMenuPage extends BasePagePayApp {
    private final String BUTTON_X_REPORT = "//android.widget.Button[@resource-id=\"id/btnItem\" and @text=\"Х-БАЛАНС\"]";
    private final String BUTTON_Z_REPORT = "//android.widget.Button[@resource-id=\"id/btnItem\" and @text=\"ЗАГАЛЬНИЙ ЗВІТ\"]";
    private final String BUTTON_EXIT = "//android.widget.Button[@resource-id=\"id/btnItem\" and @text=\"ВИХІД\"]";

    @Step("Перевіряю чи я на екрані меню")
    public boolean isOnPage() {
        return $(xpath(BUTTON_EXIT)).isDisplayed() &&
                $(xpath(BUTTON_X_REPORT)).isDisplayed() &&
                $(xpath(BUTTON_Z_REPORT)).isDisplayed();
    }

    public void exit() {
        $(xpath(BUTTON_EXIT)).click();
    }
}

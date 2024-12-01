package pages.payApp.cashBox;

import pages.payApp.BasePagePayApp;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static io.appium.java_client.AppiumBy.id;

public class SelectConnectionMethodPage extends BasePagePayApp {
    public SelectConnectionMethodPage() {
        $(id("id/rv_dialog_cash_box")).isDisplayed();
    }

    public void clickConnection(String typeConnection) {
        $(byXpath("//android.widget.Button[@text='" + typeConnection + "']")).click();
    }

}

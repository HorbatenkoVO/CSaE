package pages.payApp.purchase;

import pages.payApp.BasePagePayApp;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static io.appium.java_client.AppiumBy.id;

public class AccountSelectionPage extends BasePagePayApp {

    public AccountSelectionPage() {
        $(id("id/header")).shouldHave(text("Оберіть компанію"));
    }

    public void chooseAccount() {
        $(id("id/acc")).click();
    }

    public void clickPayment() {
        click("payment");
    }

    public void clickCancel() {
        click("cancel");
    }

    private void click(String btn) {
        $(id("id/btn_" + btn)).click();
    }
}

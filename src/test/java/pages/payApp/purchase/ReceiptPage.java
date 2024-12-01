package pages.payApp.purchase;

import io.qameta.allure.Step;
import pages.payApp.BasePagePayApp;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static io.appium.java_client.AppiumBy.id;

public class ReceiptPage extends BasePagePayApp {

    @Step("Очікую завантаження екрану друку чеку")
    public ReceiptPage isLoaded() {
        $(id("container_screen_receipt")).shouldBe(visible);
        return this;
    }

    @Step("Натискаю кнопку Далі")
    public void clickButtonNext() {
        $(id("id/btn_ok")).click();
    }

    @Step("Натискаю кнопку Друк")
    public void clickButtonPrint() {
        $(id("id/btn_cancel")).click();
    }

    @Step("Перевіряю що я на екрані друку чеку")
    public boolean isOnPage() {
        return $(id("container_screen_receipt")).isDisplayed();
    }
}

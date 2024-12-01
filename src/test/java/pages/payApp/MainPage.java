package pages.payApp;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static io.appium.java_client.AppiumBy.id;

public class MainPage {

    @Step("Перевіряю чи я на головному екрані")
    public boolean onMainPage() {
        return $(id("id/tv_status_connect")).isDisplayed();
    }

    @Step("Натискаю на кнопку Оплата")
    public void clickPurchaseButton() {
        $(id("id/btn_payment")).click();
    }

    @Step("Натискаю на логотип")
    public void clickLogo() {
        $(id("id/logo")).click();
    }

    @Step("Натискаю на кнопку Блокування")
    public void clickButtonLock() {
        $(id("id/btn_cancel")).click();
    }

    @Step("Натискаю на іконку '{service}'")
    public void clickIcon(String service) {
        $(byXpath("//android.widget.TextView[@text='" + service + "']")).click();
    }
}

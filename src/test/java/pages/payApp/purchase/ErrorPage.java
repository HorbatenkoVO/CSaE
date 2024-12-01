package pages.payApp.purchase;

import io.qameta.allure.Step;
import pages.payApp.BasePagePayApp;

import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static io.appium.java_client.AppiumBy.id;
import static org.openqa.selenium.By.xpath;

public class ErrorPage extends BasePagePayApp {
    String RED_ROUND_ICON = "id/iv_error";

    @Step("Очікую екран помилки")
    public ErrorPage isLoaded() {
        $(id(RED_ROUND_ICON)).shouldBe(visible);
        return this;
    }

    @Step("Очікую зникнення екрану помилки")
    public void goOff() {
        $(id(RED_ROUND_ICON)).shouldBe(disappear);
    }

    @Step("Перевіряю чи я на екрані помилки")
    public boolean isOnPage() {
        return $(xpath(RED_ROUND_ICON)).isDisplayed();
    }
}

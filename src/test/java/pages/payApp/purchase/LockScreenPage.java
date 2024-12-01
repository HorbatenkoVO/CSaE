package pages.payApp.purchase;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import pages.payApp.BasePagePayApp;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.actions;
import static io.appium.java_client.AppiumBy.id;

public class LockScreenPage extends BasePagePayApp {

    @Step("Перевіряю що я на екрані блокування")
    public LockScreenPage isLoaded() {
        $(id("id/tvTime")).shouldBe(visible);
        return this;
    }

    @Step("Перевіряю чи я на екрані блокування")
    public boolean isOnPage() {
        return $(id("id/tvTime")).isDisplayed();
    }

    @Step("Натискаю кнопку Оплата з чайовими")
    public void clickButtonPayWithTips() {
        $(id("id/btnTips")).click();
    }

    @Step("Натискаю кнопку Оплата")
    public void clickButtonPay() {
        $(id("id/btn_ok")).click();
    }

    @Step("Розблокування екрану по свайпу")
    public void swipeToUnlockScreen() {
        SelenideElement element1 = $(id("id/lock"));
        SelenideElement element2 = $(id("id/icon_swipe_unlock"));
        actions().dragAndDrop(element1, element2).perform();
    }

}

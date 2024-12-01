package pages.payApp.purchase;

import io.qameta.allure.Step;
import pages.payApp.BasePagePayApp;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static io.appium.java_client.AppiumBy.id;

public class WarningBatteryPage extends BasePagePayApp {

    @Step("Очікую екран попередження рівня заряда")
    public WarningBatteryPage isLoaded(){
        $(id("id/iv_warning"))
                .shouldBe(visible);
        return this;
    }

    @Step("Натискаю Продовжити")
    public WarningBatteryPage next() {
        $(id("id/btn_continue")).click();
        return this;
    }

    @Step("Натискаю Скасувати")
    public WarningBatteryPage cancel() {
        $(id("id/btn_cancel")).click();
        return this;
    }

    @Step("Перевіряю чи я на екрані попередження рівня заряда")
    public boolean isOnPage() {
        return $(id("id/tv_successful"))
                .is(text("Увага! Низький заряд акумулятора"));
    }
}

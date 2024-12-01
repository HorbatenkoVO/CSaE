package pages.payApp.purchase;

import io.qameta.allure.Step;
import pages.payApp.BasePagePayApp;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static io.appium.java_client.AppiumBy.id;

public class SuccessPage extends BasePagePayApp {

    @Step("Очікую екран успішної операції")
    public SuccessPage isLoaded(){
        $(id("id/iv_successful")).shouldBe(visible);
        return this;
    }
}

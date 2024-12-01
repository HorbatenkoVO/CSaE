package pages.payApp.allReport;

import pages.payApp.BasePagePayApp;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static io.appium.java_client.AppiumBy.id;

public class NoMerchantInformationPage extends BasePagePayApp {

    public NoMerchantInformationPage isLoaded() {
        $(id("id/iv_successful")).shouldBe(visible);
        return this;
    }
}

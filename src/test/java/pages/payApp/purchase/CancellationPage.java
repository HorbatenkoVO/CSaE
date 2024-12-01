package pages.payApp.purchase;

import pages.payApp.BasePagePayApp;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static io.appium.java_client.AppiumBy.id;

public class CancellationPage extends BasePagePayApp {

    public CancellationPage isLoaded() {
        $(id("id/iv_successful")).shouldBe(visible);
        return this;
    }
}

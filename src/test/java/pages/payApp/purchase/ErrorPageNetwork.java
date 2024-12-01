package pages.payApp.purchase;

import pages.payApp.BasePagePayApp;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static io.appium.java_client.AppiumBy.id;

public class ErrorPageNetwork extends BasePagePayApp {

    public ErrorPageNetwork() {
//        $(id("id/iv_successful")).shouldBe(visible);
        $(id("id/tv_successful")).shouldHave(text("Відсутній зв'язок, виконується скасування операції"));
    }
}

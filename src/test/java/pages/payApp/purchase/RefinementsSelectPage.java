package pages.payApp.purchase;

import io.qameta.allure.Step;
import pages.payApp.BasePagePayApp;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static io.appium.java_client.AppiumBy.id;

public class RefinementsSelectPage extends BasePagePayApp {

    @Step("Перевіряю що я на екрані вибору компанії")
    public boolean isOnPage() {
        return $(id("id/app_bar_description"))
                .is(text("Оберіть компанію"));
    }

    @Step("Перевіряю що я не екрані вибору компанії")
    public RefinementsSelectPage isLoaded() {
        $(id("id/app_bar_description")).shouldHave(text("Оберіть компанію"));
        return this;
    }

    @Step("Обираю компанію {name}")
    public RefinementsSelectPage selectCompany(String name) {
        $x("//android.widget.TextView[@resource-id=\"id/text_bnt\" " +
                "and @text=\"" + name + "\"]").click();
        return this;
    }

    @Step("Натискаю кнопку Обрати")
    public void choose() {
        $(id("id/btn_payment")).click();
    }

    @Step("Натискаю кнопку Скасувати")
    public void cancel() {
        $(id("id/btn_cancel")).click();
    }
}

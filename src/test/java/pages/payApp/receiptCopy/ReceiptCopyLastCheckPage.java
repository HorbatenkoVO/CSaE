package pages.payApp.receiptCopy;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import pages.payApp.BasePagePayApp;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.appium.SelenideAppium.$;

public class ReceiptCopyLastCheckPage extends BasePagePayApp {

    @Step("Перевіряю що успішно отриманно копію останього чека")
    public ReceiptCopyLastCheckPage isLoaded() {
        $(By.id("tv_successful")).shouldBe(visible).shouldHave(text("Чек знайдено"));
        return this;
    }

}

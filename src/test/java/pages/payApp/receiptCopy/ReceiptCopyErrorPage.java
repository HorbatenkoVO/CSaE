package pages.payApp.receiptCopy;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import pages.payApp.BasePagePayApp;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.appium.SelenideAppium.$;

public class ReceiptCopyErrorPage extends BasePagePayApp {

    @Step("Перевіряю що сторінка помилкит завантажена")
    public ReceiptCopyErrorPage isLoaded() {
        $(By.id("iv_successful")).shouldBe(visible);
        return this;
    }

}

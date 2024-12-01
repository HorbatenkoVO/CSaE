package pages.payApp.receiptCopy;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import pages.payApp.BasePagePayApp;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.appium.SelenideAppium.$;
import static io.appium.java_client.AppiumBy.id;

public class ReceiptCopyMainPage extends BasePagePayApp {

    @Step("Перевіряю що я на головному екрані послуги Копія чека")
    public ReceiptCopyMainPage isLoaded() {
        $(By.id("header")).shouldBe(visible).shouldHave(text("Копія чека"));
        return this;
    }

    @Step("Натискаю на поле пошуку копії чека")
    public void clickSearchField() {
        $(By.id("et_number")).click();
    }

    @Step("Натискаю на Оснтанній чек")
    public void clickLastCheck() {
        $(By.id("last_receipt")).click();
    }

    @Step("Натискаю на Десять останніх чеків")
    public void clickLastTen() {
        $(id("last_ten")).click();
        $(id("last_ten")).shouldBe(disappear);
        $(id("header")).shouldHave(text("Зв'язок з банком"));
        $(id("status")).shouldBe(disappear);
    }

    @Step("Ввожу номеру чека або номера rrn")
    public void enterReceiptOrRrnNumber(String receiptOrRrn) {
        for (int i = 0; i < receiptOrRrn.length(); i++) {
            click(receiptOrRrn.substring(i, i + 1));
        }
    }

    @Step("Натискаю кнопку Продовжити")
    public void clickEnter() {
        click("enter");
    }

    @Step("Натискаю на кнопку назад")
    public void goBack() {
        $(id("btn_backspace")).click();
    }

    @Step("Натискаю на кнопку {btn}")
    private void click(String btn) {
        $(By.id("btn_" + btn)).click();
    }



}

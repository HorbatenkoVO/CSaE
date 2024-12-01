package pages.payApp.cashBox;

import io.qameta.allure.Step;
import pages.payApp.BasePagePayApp;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static io.appium.java_client.AppiumBy.id;

public class CashBoxPhoneNumberPage extends BasePagePayApp {

    @Step("Перевіряю, що сторінка завантажена")
    public CashBoxPhoneNumberPage isLoaded(){
        $(id("id/app_bar_description"))
                .shouldBe(visible)
                .shouldHave(text("Номер телефону"));
        return this;
    }

    @Step("Ввожу телефон: {phone}")
    public CashBoxPhoneNumberPage enterPhone(String phone) {
        for (int i = 0; i < phone.length(); i++) {
            click(phone.substring(i, i + 1));
        }
        return this;
    }

    @Step("Натискаю Продовжити")
    public CashBoxPhoneNumberPage next() {
        $(id("id/btn_enter")).click();
        return this;
    }

    @Step("Натискаю Назад")
    public CashBoxPhoneNumberPage back() {
        $(id("id/btn_backspace")).click();
        return this;
    }

    private void click(String btn) {
        $(id("id/btn_" + btn)).click();
    }
}

package pages.payApp.cashBox;

import io.qameta.allure.Step;
import pages.payApp.BasePagePayApp;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static io.appium.java_client.AppiumBy.id;

public class CashBoxOtpPage extends BasePagePayApp {

    @Step("Перевіряю, що сторінка завантажена")
    public CashBoxOtpPage isLoaded(){
        $(id("id/app_bar_description"))
                .shouldBe(visible)
                .shouldHave(text("OTP пароль"));
        return this;
    }

    @Step("Ввожу OTP: {otp}")
    public CashBoxOtpPage enterOtp(String otp) {
        for (int i = 0; i < otp.length(); i++) {
            click(otp.substring(i, i + 1));
        }
        return this;
    }

    @Step("Натискаю Продовжити")
    public CashBoxOtpPage next() {
        $(id("id/btn_enter")).click();
        return this;
    }

    private void click(String btn) {
        $(id("id/btn_" + btn)).click();
    }
}

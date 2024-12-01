package pages.payApp.purchase;

import io.qameta.allure.Step;
import pages.payApp.BasePagePayApp;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static helpers.FormatHelper.formatDotDouble;
import static io.appium.java_client.AppiumBy.id;

public class EnterSumPage extends BasePagePayApp {

    @Step("Натискаю Оплата")
    public void clickPayment() {
        click("payment");
    }

    @Step("Ввожу сумму {number}")
    public EnterSumPage enterSum(String number) {
        Double aDouble = formatDotDouble(number);
        aDouble = aDouble * 100;
        int intValue = aDouble.intValue();
        String coins = String.valueOf(intValue);
        //System.out.println(coins);
        for (int i = 0; i < coins.length(); i++) {
            click(coins.substring(i, i + 1));
        }

        return this;
    }

    @Step("Очікую екран введення суми")
    public EnterSumPage isLoaded() {
        $(id("id/header")).shouldBe(visible);
        return this;
    }

    private void click(String btn) {
        $(id("id/btn_" + btn)).click();
    }
}
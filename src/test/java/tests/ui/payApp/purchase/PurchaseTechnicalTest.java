package tests.ui.payApp.purchase;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tests.ui.payApp.BaseTest;

import java.lang.reflect.Method;

import static conditions.UiConditions.text;
import static service.GeneratorService.generator;

@Epic("Еквайрінг")
@Feature("Оплата")
@Test(groups = {"payApp", "Purchase", "ui"})
public class PurchaseTechnicalTest extends BaseTest {

    @BeforeMethod
    void beforeMethod(Method method) {
        if (method.getName().equals("testCheckBatteryAlertNote")) {
            shellHelper.setBattery(15);
        }
    }

    @AfterMethod
    void afterMethod(Method method) {
        if (method.getName().equals("testCheckBatteryAlertNote")) {
            shellHelper.resetBattery();
        }
    }

    @Test(description = "Перевірка усіх елементів на усіх сторінках в послузі Оплата.", groups = {"qaArm"})
    public void testCheckBatteryAlertNote() {
        String sumPayment = generator().amountStr(10.00);

        pages.mainPage().clickPurchaseButton();
        pages.warningBatteryPage().isLoaded()
                .check().shouldHave(text("tv_successful", "Увага! Низький заряд акумулятора 15%. Якість друку чеків може бути знижена. Рекомендуємо зарядити термінал."));
        pages.warningBatteryPage().next();
        pages.enterSumPage().isLoaded()
                .enterSum(sumPayment)
                .clickPayment();
        pages.paymentPage().isLoaded()
                .readNFC();
        pages.successFinalPage().isLoaded();
        pages.receiptPage().isLoaded();
    }

}

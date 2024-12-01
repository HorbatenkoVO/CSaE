package tests.ui.payApp.purchase;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.annotations.Test;
import tests.ui.payApp.BaseTest;

import static conditions.UiConditions.*;
import static service.GeneratorService.generator;

@Epic("Еквайрінг")
@Feature("Оплата")
@Test(groups = {"payApp", "Purchase", "ui"})
public class PurchaseTest extends BaseTest {

    @Test(description = "Перевірка усіх елементів на усіх сторінках в послузі Оплата.", groups = {"qaArm"})
    public void testCheckAllElementsInPurchase() {
        String sumPayment = generator().amountStr(10.00);

        pages.mainPage().clickPurchaseButton();
        pages.enterSumPage().check()
                .shouldHave(text("header", "Оплата"))
                .shouldHave(text("tvDescription", "Сума"))
                .isDisplayed(idLocator("green_line"))
                .isDisplayed(idLocator("cl_numbers"))
                .isDisplayed(idLocator("et_enter_sum"))
                .shouldHave(text("btn_payment", "Оплата"));
        pages.enterSumPage().enterSum(sumPayment);
        pages.enterSumPage().clickPayment();
        pages.paymentPage().isLoaded().check()
                .shouldHave(text("header", "Оплата"))
                .isDisplayed(idLocator("btn_backspace"))
                .isDisplayed(idLocator("ll_payment"))
                .isDisplayed(idLocator("constraintLayout3"))
                .isDisplayed(idLocator("clssLight"))
                .isDisplayed(idLocator("led_panel"))
                .isDisplayed(idLocator("light1"))
                .isDisplayed(idLocator("light2"))
                .isDisplayed(idLocator("light3"))
                .isDisplayed(idLocator("container_sum"))
                .shouldHave(text("description_sum", "Сума до оплати"))
                .shouldHave(contains("sum", sumPayment))
                .isDisplayed(idLocator("iv_magnetic"))
                .isDisplayed(idLocator("iv_picc"))
                .isDisplayed(idLocator("iv_icc"))
                .shouldHave(text("tv_status", "Очікую спосіб проведення транзакції…"));
        pages.paymentPage().readNFC();
        pages.successFinalPage().isLoaded();
        pages.receiptPage().isLoaded();
    }

    @Test(description = "Перевірка оплати з екрану блокування", groups = {"qaArm"})
    public void testPurchaseFromTheLockScreen() {
        String sumPayment = generator().amountStr(10.00);

        pages.mainPage().clickButtonLock();
        pages.lockScreenPage().isLoaded().check()
                .shouldHave(text("btnTips", "Оплата з чайовими"))
                .shouldHave(text("btn_ok", "Оплата"))
                .isDisplayed(idLocator("lock"))
                .shouldHave(text("appCompatTextView2", "Проведіть, щоб розблокувати"))
                .isDisplayed(idLocator("icon_swipe_unlock"))
                .isDisplayed(idLocator("tvMerchant"))
                .isDisplayed(idLocator("tvTime"))
                .isDisplayed(idLocator("tvDate"));
        pages.lockScreenPage().clickButtonPay();
        pages.enterSumPage().enterSum(sumPayment);
        pages.enterSumPage().clickPayment();
        pages.paymentPage().isLoaded().check()
                .shouldHave(text("description_sum", "Сума до оплати"));
        pages.paymentPage().readNFC();
        pages.successFinalPage().isLoaded();
        pages.receiptPage().isLoaded();
        pages.lockScreenPage().isLoaded().check()
                .shouldHave(text("appCompatTextView2", "Проведіть, щоб розблокувати"));
        pages.lockScreenPage().swipeToUnlockScreen();
        pages.mainPage().onMainPage();
    }

    @Test(description = "Валідція поля сума, перевірка мінімального значеня")
    public void testValidateFieldSumMinimal() {
        String sumPayment = generator().amountStr(0.10);

        pages.mainPage().clickPurchaseButton();
        pages.enterSumPage().enterSum(sumPayment);
        pages.enterSumPage().clickPayment();
        pages.paymentPage().isLoaded().check()
                .shouldHave(contains("sum", sumPayment));
        pages.goBack();
        pages.cancellationPage().isLoaded();
    }

    @Test(description = "Валідція поля сума, перевірка максимального значеня")
    public void testValidateFieldSumMaximal() {
        String sumPayment = "111111.11";

        pages.mainPage().clickPurchaseButton();
        pages.enterSumPage().enterSum(sumPayment);
        pages.enterSumPage().clickPayment();
        pages.paymentPage().isLoaded().check()
                .shouldHave(contains("sum", sumPayment));
        pages.goBack();
        pages.cancellationPage().isLoaded();
    }
}

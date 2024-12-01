package tests.ui.payApp.cashbox;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import model.transaction.cashBox.CashBoxMessage;
import org.testng.annotations.Test;

import static conditions.Conditions.attr;

@Epic("Касове суміщення")
@Feature("Основні методи")
@Story("Purchase - оплата")
@Test(groups = {"payApp", "ecrMainMethod", "ui", "cashbox"})
public class EcrPurchaseTest extends EcrBaseTest {

    @Test(description = "Purchase. Помилка, якщо роздільний знак не кома і не крапка.")
    void testPurchaseErrorDelimiter() {
        CashBoxMessage body = getBody()
                .method("Purchase")
                .params(getParams()
                        .amount("0 06"));

        cashBox.write(body);

        cashBox.check()
                .shouldHave(attr("params.responseCode", "1000"))
                .shouldHave(attr("errorDescription", "Операція неможлива"))
                .shouldHave(attr("method", "Purchase"))
                .schemaValidation(SCHEMA_PATH_DEFAULT_ERROR);
    }

    @Test(description = "Purchase. Помилка, якщо не передано amount")
    void testPurchaseErrorWithoutAmount() {
        CashBoxMessage body = getBody()
                .method("Purchase")
                .params(getParams()
                        .amount(""));

        cashBox.write(body);

        cashBox.check()
                .shouldHave(attr("method", "Purchase"))
                .shouldHave(attr("errorDescription", "empty String"))
                .schemaValidation(SCHEMA_PATH_DEFAULT_ERROR);
    }

    @Test(description = "Purchase. Помилка, якщо amount від'ємний")
    void testPurchaseErrorWithMinusAmount() {
        CashBoxMessage body = getBody()
                .method("Purchase")
                .params(getParams()
                        .amount("-1"));

        cashBox.write(body);

        cashBox.check()
                .shouldHave(attr("method", "Purchase"))
                .shouldHave(attr("errorDescription", "amount має бути > 0"))
                .schemaValidation(SCHEMA_PATH_DEFAULT_ERROR);
    }


    @Test(description = "Purchase. Помилка, якщо нема discount")
    void testPurchaseWithoutDiscount() {
        CashBoxMessage body = getBody()
                .method("Purchase")
                .params(getParams()
                        .amount("0,1")
                        .merchantId(""));

        cashBox.write(body);

        cashBox.check()
                .shouldHave(attr("method", "Purchase"))
                .shouldHave(attr("errorDescription", "Введіть discount"))
                .schemaValidation(SCHEMA_PATH_DEFAULT_ERROR);
    }

    @Test(description = "Purchase. Працює, якщо нема merchantId", groups = {"qaArm"})
    void testPurchaseWithoutMerchantId() {
        CashBoxMessage body = getBody()
                .method("Purchase")
                .params(getParams()
                        .amount("0,1")
                        .discount(""));

        cashBox.write(body);
        pages.paymentPage().isLoaded()
                .readNFC();

        cashBox.check()
                .shouldHave(attr("method", "Purchase"))
                .shouldHave(attr("step", 0))
                .shouldHave(attr("error", false))
                .shouldHave(attr("errorDescription", ""));
    }
}

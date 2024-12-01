package tests.ui.payApp.cashbox.withdrawal;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import model.transaction.cashBox.CashBoxMessage;
import org.testng.annotations.Test;
import tests.ui.payApp.cashbox.EcrBaseTest;

import static conditions.Conditions.attr;
import static conditions.UiConditions.text;

@Epic("Касове суміщення")
@Feature("Основні методи")
@Story("Withdrawal - ануляція")
@Test(groups = {"payApp", "ecrMainMethod", "ui", "cashbox"})
public class EcrWithdrawalTest extends EcrBaseTest {

    @Test(description = "Ручний ввод, якщо не передано Params")
    void testWithdrawalErrorWithoutParams() {
        CashBoxMessage body = getBody()
                .method("Withdrawal");

        cashBox.write(body);
        pages.errorPage().isLoaded().check()
                .shouldHave(text("tv_successful", "Відміна операції"));

        cashBox.check()
                .shouldHave(attr("method", body.method()))
                .schemaValidation(SCHEMA_PATH_DEFAULT_ERROR);
    }

    @Test(description = "Помилка, якщо не передано invoiceNumber")
    void testWithdrawalErrorWithoutInvoiceNumber() {
        CashBoxMessage body = getBody()
                .method("Withdrawal")
                .params(getParams());

        cashBox.write(body);
        pages.errorPage().isLoaded().check()
                .shouldHave(text("tv_successful", "Введіть номер чека"));

        cashBox.check()
                .shouldHave(attr("method", body.method()))
                .shouldHave(attr("errorDescription", "Введіть номер чека"))
                .schemaValidation(SCHEMA_PATH_DEFAULT_ERROR);
    }

    @Test(description = "Помилка, якщо передано порожній invoiceNumber")
    void testWithdrawalErrorWithEmptyInvoiceNumber() {
        CashBoxMessage body = getBody()
                .method("Withdrawal")
                .params(getParams()
                        .invoiceNumber(""));

        cashBox.write(body);
        pages.errorPage().isLoaded().check()
                .shouldHave(text("tv_successful", "Введіть номер чека"));

        cashBox.check()
                .shouldHave(attr("method", body.method()))
                .shouldHave(attr("errorDescription", "Введіть номер чека"))
                .schemaValidation(SCHEMA_PATH_DEFAULT_ERROR);
    }

    @Test(description = "Помилка, якщо передано неіснуючий invoiceNumber")
    void testWithdrawalErrorWithRandomInvoiceNumber() {
        CashBoxMessage body = getBody()
                .method("Withdrawal")
                .params(getParams()
                        .invoiceNumber("999999999999"));
        String expectedText = "Транзакція продажу не знайдена";

        cashBox.write(body);
        pages.errorPage().isLoaded().check()
                .shouldHave(text("tv_successful", expectedText));

        cashBox.check()
                .shouldHave(attr("method", body.method()))
                .shouldHave(attr("params.responseCode", "1000"))
                .shouldHave(attr("errorDescription", expectedText))
                .schemaValidation(SCHEMA_PATH_DEFAULT_ERROR);
    }
}
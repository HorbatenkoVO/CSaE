package tests.ui.payApp.cashbox.withdrawal;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import model.transaction.cashBox.CashBoxMessage;
import org.testng.annotations.Test;
import tests.ui.payApp.cashbox.EcrBaseTest;

import static conditions.Conditions.attr;
import static service.GeneratorService.generator;

@Epic("Касове суміщення")
@Feature("Основні методи")
@Story("WithdrawalPartly - часткова ануляція")
@Test(groups = {"payApp", "ecrMainMethod", "ui", "cashbox"})
public class EcrWithdrawalPartlyTest extends EcrBaseTest {

    @Test(description = "Ручний ввод, якщо не передано Params")
    void testWithdrawalErrorWithoutParams() {
        CashBoxMessage bodyWithdrawal = getBody()
                .method("WithdrawalPartly");

        CashBoxMessage bodyServiceMessage = getBody()
                .method("ServiceMessage")
                .params(getParams().msgType("getLastStatMsgCode"));

        cashBox.write(bodyWithdrawal)
                .waite()
                .write(bodyServiceMessage);

        cashBox.check()
                .shouldHave(attr("params.LastStatMsgCode", "10"));
    }

    @Test(description = "Помилка, якщо не передано invoiceNumber")
    void testWithdrawalErrorWithoutInvoiceNumber() {
        CashBoxMessage bodyWithdrawal = getBody()
                .method("WithdrawalPartly")
                .params(getParams()
                        .amount(generator().amountStr(1.0)));

        cashBox.write(bodyWithdrawal)
                .check()
                .shouldHave(attr("method", "WithdrawalPartly"))
                .shouldHave(attr("errorDescription", "Введіть номер чека"))
                .schemaValidation(SCHEMA_PATH_DEFAULT_ERROR);
    }

    @Test(description = "Помилка, якщо передано порожній invoiceNumber")
    void testWithdrawalErrorWithEmptyInvoiceNumber() {
        CashBoxMessage body = getBody()
                .method("WithdrawalPartly")
                .params(getParams()
                        .invoiceNumber("")
                        .amount(generator().amountStr(1.0)));

        cashBox.write(body);

        cashBox.check()
                .shouldHave(attr("method", "WithdrawalPartly"))
                .shouldHave(attr("errorDescription", "Введіть номер чека"))
                .schemaValidation(SCHEMA_PATH_DEFAULT_ERROR);
    }

    @Test(description = "Помилка, якщо передано неіснуючий invoiceNumber")
    void testWithdrawalErrorWithRandomInvoiceNumber() {
        CashBoxMessage body = getBody()
                .method("WithdrawalPartly")
                .params(getParams()
                        .invoiceNumber("999999999999")
                        .amount(generator().amountStr(1.0)));

        cashBox.write(body);

        cashBox.check()
                .shouldHave(attr("method", "WithdrawalPartly"))
                .shouldHave(attr("params.responseCode", "1000"))
                .shouldHave(attr("errorDescription", "Транзакція продажу не знайдена, " +
                        "для подачі заявки на повернення торгова точка може самостійно оформити заявку " +
                        "в виписці з еквайрингу в Приват24 для бізнесу, або звернутися на 3700 " +
                        "для ручної обробки заявки на повернення"))
                .schemaValidation(SCHEMA_PATH_DEFAULT_ERROR);
    }
}
package tests.api.transaction.reversalProcess;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import model.transaction.reversalProcess.Reversal;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import tests.api.transaction.BaseTransactionTest;

import static conditions.Conditions.attr;
import static conditions.Conditions.notNull;

@Epic("Transaction")
@Feature("reversal-process")
@Story("reversal oper")
@Test(groups = {"Transaction", "reversal-process", "reversal", "api"})
public class ReversalTest extends BaseTransactionTest {

    @BeforeClass(description = "Обновление Settings")
    void beforeClass() {
        transactionSteps.updateSettingsHash();
    }

    @Test(description = "Успешный реверс.")
    void testSuccessReversal() {
        Reversal body = reversal()
                .sessionId("SESSION1170674240-202202041643976592275630");

        mainHost.given(body)
                .shouldHave(attr("result", "ok"))
                .shouldHave(notNull("receipt_id"))
                .shouldHave(notNull("date"));
    }

    private Reversal reversal() {
        return new Reversal();
    }
}

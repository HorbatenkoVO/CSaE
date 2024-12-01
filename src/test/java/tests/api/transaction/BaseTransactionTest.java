package tests.api.transaction;

import io.qameta.allure.Step;
import org.testng.annotations.BeforeTest;
import service.TransactionService;
import steps.TransactionSteps;
import steps.SettingsSteps;
import tests.BaseSuiteTest;

import java.util.ArrayList;
import java.util.List;

import static helpers.ConfigHelper.*;

public class BaseTransactionTest extends BaseSuiteTest {

    public static TransactionService mainHost;
    public static TransactionSteps transactionSteps;
    public static SettingsSteps settingsSteps;

    public static String SN;
    public static String MERCHANT;
    public static String MULTI_MERCHANT;
    public static String P_MERCHANT;
    public static String K1_MERCHANT;
    public static String K2_MERCHANT;
    public static String OCH_MERCHANT;
    public static String AOCH_MERCHANT;
    public static String MR_MERCHANT;

    public static String COMPANY;
    public static String MULTI_COMPANY;
    public static String PAN;

    @BeforeTest(description = "Інтеграція з TestManager", alwaysRun = true)
    public void beforeTest() {
        initialiseServices();
        initialiseMerchantsAndCompanies();
        initialiseOperations();
    }

    @Step("Ініціалізація тестових сервісів")
    private void initialiseServices() {
        mainHost = new TransactionService();
        transactionSteps = new TransactionSteps();
        settingsSteps = new SettingsSteps();
    }

    @Step("Ініціалізація мерчантів і компаній")
    private void initialiseMerchantsAndCompanies() {
        SN = terminalConfig().getSerialNumber();
        MERCHANT = terminalConfig().getMerchantMain();
        MULTI_MERCHANT = terminalConfig().getMerchantMulti();
        P_MERCHANT = terminalConfig().getMerchantPhone();
        K1_MERCHANT = terminalConfig().getMerchantK1();
        K2_MERCHANT = terminalConfig().getMerchantK2();
        OCH_MERCHANT = terminalConfig().getMerchantOch();
        AOCH_MERCHANT = terminalConfig().getMerchantAoch();
        MR_MERCHANT = terminalConfig().getMerchantMr();
        COMPANY = merchantConfig(MERCHANT).getIdExternal();
        MULTI_COMPANY = merchantConfig(MULTI_MERCHANT).getIdExternal();
        PAN = panConfig().getPan();

        merchantConfig(MERCHANT);
        companyConfig(COMPANY);
    }

    @Step("Ініціалізація операцій")
    private void initialiseOperations() {
        List<String> ochOperList = new ArrayList<>(List.of("partpay-create-1", "partpay-cancel-1"));
        List<String> mrOperList = new ArrayList<>(List.of("partpay-create-2", "partpay-cancel-2"));
        List<String> aochOperList = new ArrayList<>(List.of("partpay-create-4", "partpay-cancel-4"));
        settingsSteps
                .bindTermMerchComp(SN, MERCHANT, COMPANY)
                .bindTermMerchComp(SN, P_MERCHANT, COMPANY, "phone-refill")
                .bindTermMerchComp(SN, K1_MERCHANT, COMPANY, "cash-to-card-present")
                .bindTermMerchComp(SN, K2_MERCHANT, COMPANY, "cash-to-card-absent")
                .bindTermMerchComp(SN, OCH_MERCHANT, COMPANY, ochOperList)
                .bindTermMerchComp(SN, MR_MERCHANT, COMPANY, mrOperList)
                .bindTermMerchComp(SN, AOCH_MERCHANT, COMPANY, aochOperList)
                .updateOperSet(SN, COMPANY)
                .updateSettings(SN);
    }

}

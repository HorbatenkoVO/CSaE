package tests.ui.payApp.cashbox;

import com.codeborne.selenide.appium.SelenideAppium;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.DriverHelper;
import helpers.ShellHelper;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;
import model.transaction.cashBox.CashBoxMessage;
import model.transaction.cashBox.Params;
import model.settingsServices.ecr.settings.AddOrUpdateControlEcr;
import org.testng.annotations.*;
import service.TransactionService;
import service.SettingsService;
import steps.*;
import tests.BaseSuiteTest;

import static com.codeborne.selenide.Configuration.*;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;
import static helpers.ConfigHelper.*;
import static helpers.ConfigHelper.panConfig;

public class EcrBaseTest extends BaseSuiteTest {
    public static CashBoxSteps cashBox;
    public static SettingsService webService;
    public static SettingsSteps settingsSteps;
    public static TransactionSteps transactionSteps;
    public static TransactionService transactionService;
    public static PayAppSteps pages;
    public static ShellHelper shellHelper;

    public static String SN;
    public static String MERCHANT;
    public static String MULTI_MERCHANT;
    public static String P_MERCHANT;
    public static String OCH_MERCHANT;
    public static String AOCH_MERCHANT;
    public static String MR_MERCHANT;

    public static String COMPANY;
    public static String MULTI_COMPANY;
    public static String PAN;
    public static String SCHEMA_PATH_DEFAULT_ERROR;
    public static String SCHEMA_PATH_SERVICE_GENERIC;
    public static String SCHEMA_PATH_CURRENT;

    public static String interfaceEcr = "serial"; //"ethernet" "serial", для wifi оставляем "ethernet"

    @BeforeTest(description = "Подключение к кассе", alwaysRun = true)
    public void beforeTest() {
        initialiseMerchantsAndCompanies();
        initialiseServices();
        updateControlEcr(interfaceEcr);
        setUpDriver();
        updateTerminalSettings();
        initialiseEcrConnection();
    }

    @BeforeMethod(description = "Підключення Appium до додатку", alwaysRun = true)
    public void beforeMethod() {
        SelenideAppium.launchApp();
    }

    @AfterMethod(description = "interrupt & clear buffer", alwaysRun = true)
    public void afterMethod() {
        pages.goToMainPageEcr();
        cashBox.clearBuffer();
    }

    @AfterTest(description = "Закрытие порта USB", alwaysRun = true)
    public void afterTest() {
        cashBox.closePort();
        settingsSteps
                .removeControlEcr(SN, MERCHANT)
                .setModeParameter(SN, "main")
                .updateSettings(SN);
        pages.updateSettings();
        closeWebDriver();
    }

    public CashBoxMessage getBody() {
        return new CashBoxMessage()
                .step(0);
    }

    public Params getParams() {
        return new Params();
    }

    @Step("Ініціалізація мерчантів і компаній")
    private void initialiseMerchantsAndCompanies() {
        SN = terminalConfig().getSerialNumber();
        MERCHANT = terminalConfig().getMerchantMain();
        MULTI_MERCHANT = terminalConfig().getMerchantMulti();
        P_MERCHANT = terminalConfig().getMerchantPhone();
        OCH_MERCHANT = terminalConfig().getMerchantOch();
        AOCH_MERCHANT = terminalConfig().getMerchantAoch();
        MR_MERCHANT = terminalConfig().getMerchantMr();
        COMPANY = merchantConfig(MERCHANT).getIdExternal();
        MULTI_COMPANY = merchantConfig(MULTI_MERCHANT).getIdExternal();
        PAN = panConfig().getPan();

        merchantConfig(MERCHANT);
        companyConfig(COMPANY);

        SCHEMA_PATH_DEFAULT_ERROR = "cashbox/EcrDefaultError";
        SCHEMA_PATH_SERVICE_GENERIC = "cashbox/EcrServiceGeneric";
    }

    @Step("Ініціалізація тестових сервісів")
    private void initialiseServices() {
        transactionService = new TransactionService();
        transactionSteps = new TransactionSteps();
        settingsSteps = new SettingsSteps();
        webService = new SettingsService();
        shellHelper = new ShellHelper();
        pages = new PayAppSteps();
        cashBox = new CashBoxSteps(interfaceEcr);
    }

    @Step("Призначення налаштувань терміналу")
    private void updateControlEcr(String interfaceEcr) {
        AddOrUpdateControlEcr controlEcr = new AddOrUpdateControlEcr()
                .sn(SN)
                .merchant(MERCHANT)
                .adjustTimeout(10)
                .fillReceipt(true)
                .maskPan(true)
                .printReceipt(true)
                .sendF63(true)
                .interfaceAttr(interfaceEcr);
        settingsSteps
                .updateControlEcr(controlEcr)
                .setModeParameter(SN,"cash_box_not_strict")
                .updateSettings(SN);
    }

    @Step("Ініціалізація Appium driver")
    public void setUpDriver() {
        browser = DriverHelper.class.getName();
        browserSize = null;
        timeout = 30000;
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        open();
    }

    @Step("Оновлення налаштувань терміналом")
    private void updateTerminalSettings() {
        // Обновляю настройки на терминале
        pages.updateSettings();
        // Проверяю, что я нахожусь на экране кассового совмещения с верными настройками
        pages.goToMainPageEcr();
    }

    @Step("З'єднання каси з терміналом")
    private void initialiseEcrConnection() {
        cashBox.handShake()
                .closePort()
                .identify()
                .closePort()
                .openPort();
    }

    @Step("Оновлення налаштувань")
    public void updateAndConnectEcr() {
        pages.updateSettings()
                .goToMainPageEcr();
        cashBox.closePort();
        initialiseEcrConnection();
    }

}

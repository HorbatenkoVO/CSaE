package tests.ui.payApp;

import com.codeborne.selenide.appium.SelenideAppium;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.DriverHelper;
import helpers.ShellHelper;
import io.qameta.allure.selenide.AllureSelenide;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import service.TransactionService;
import steps.PayAppSteps;
import steps.QaArmSteps;
import steps.TransactionSteps;
import steps.SettingsSteps;
import tests.BaseSuiteTest;

import static com.codeborne.selenide.Configuration.*;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

public class BaseTest extends BaseSuiteTest {

    public static PayAppSteps pages;
    public static TransactionService transactionService;
    public static TransactionSteps transactionSteps;
    public static ShellHelper shellHelper;
    public static SettingsSteps settingsSteps;

    @BeforeTest(description = "Подготовка драйвера", alwaysRun = true)
    public static void setUp() {

        browser = DriverHelper.class.getName();
        browserSize = null;
        timeout = 30000;
        pages = new PayAppSteps();
        transactionService = new TransactionService();
        transactionSteps = new TransactionSteps();
        shellHelper = new ShellHelper();
        settingsSteps = new SettingsSteps();
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        open();
    }

    @BeforeMethod(alwaysRun = true)
    public void goMainPage() {
        SelenideAppium.launchApp();
        pages.goToMainPage();
    }

    private static QaArmSteps qaArm;

    public static QaArmSteps getQaArm() {
        if (qaArm == null)
            qaArm = new QaArmSteps();
        return qaArm;
    }

    @AfterTest(alwaysRun = true)
    public void tearDown() {
        closeWebDriver();
    }
}
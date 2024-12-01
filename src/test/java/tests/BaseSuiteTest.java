package tests;

import io.qameta.allure.Commands;
import io.qameta.allure.Step;
import io.qameta.allure.option.ConfigOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

import static helpers.ConfigHelper.*;

public class BaseSuiteTest {

    @BeforeSuite(description = "BeforeSuite")
    public void beforeSuite() {
        updateVariables();
    }

    @AfterSuite(description = "Генерація AllureReport", alwaysRun = true)
    public void afterSuite() {
        Path allureReportPath = Paths.get("target/allure-report");
        List<Path> resultsPathList = Collections.singletonList(Paths.get("target/allure-results"));

        Commands commands = new Commands(allureReportPath);
        commands.generate(allureReportPath, resultsPathList, true, true, new ConfigOptions());
    }

    @Step("Оновлення зміних")
    private void updateVariables() {
        envConfig(envConfig().getEnvType());
        terminalConfig(terminalConfig().getSerialNumber());
        merchantConfig(merchantConfig().getName());
        companyConfig(companyConfig().getExternalId());
    }

}

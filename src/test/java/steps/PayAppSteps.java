package steps;

import assertions.UiAssertion;
import io.qameta.allure.Step;
import lombok.SneakyThrows;
import pages.payApp.MainPage;
import pages.payApp.allReport.NoMerchantInformationPage;
import pages.payApp.cashBox.*;
import pages.payApp.purchase.*;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static helpers.ConfigHelper.terminalConfig;
import static io.appium.java_client.AppiumBy.id;

public class PayAppSteps {

    public MainPage mainPage() {
        return new MainPage();
    }

    public NoMerchantInformationPage noMerchantInformationPage() {
        return new NoMerchantInformationPage();
    }

    public EnterSumPage enterSumPage() {
        return new EnterSumPage();
    }

    public PaymentPage paymentPage() {
        return new PaymentPage();
    }

    public RefinementsSelectPage refinementsSelectPage() {
        return new RefinementsSelectPage();
    }

    public SuccessFinalPage successFinalPage() {
        return new SuccessFinalPage();
    }

    public SuccessFinalPageWithoutAnimation successFinalPageWithoutAnimation() {
        return new SuccessFinalPageWithoutAnimation();
    }

    public ReceiptPage receiptPage() {
        return new ReceiptPage();
    }

    public ErrorPage errorPage() {
        return new ErrorPage();
    }

    public ErrorPageNetwork errorPageNetwork() {
        return new ErrorPageNetwork();
    }

    public LockScreenPage lockScreenPage() {
        return new LockScreenPage();
    }

    public CancellationPage cancellationPage() {
        return new CancellationPage();
    }

    public SelectConnectionMethodPage selectConnectionMethodPage() {
        return new SelectConnectionMethodPage();
    }

    public CashBoxOtpPage cashBoxOtpPage() {
        return new CashBoxOtpPage();
    }

    public CashBoxPhoneNumberPage cashBoxPhoneNumberPage() {
        return new CashBoxPhoneNumberPage();
    }

    public void goToMainPage() {
        waite(1);
        if (mainPage().onMainPage()) {
            //я на главной странице, ничего не делаю.
        } else if (receiptPage().isOnPage()) {
            receiptPage().clickButtonNext();
        } else if (lockScreenPage().isOnPage()) {
            lockScreenPage().swipeToUnlockScreen();
        } else if ($(id("id/btn_backspace")).isDisplayed()) {
            goBack();
        } else if (warningBatteryPage().isOnPage()) {
            warningBatteryPage().cancel();
            errorPage().isLoaded();
        } else if (refinementsSelectPage().isOnPage()) {
            refinementsSelectPage().cancel();
            errorPage().isLoaded().goOff();
        }
    }

    public void goBack() {
        $(id("id/btn_backspace")).click();
    }

    public void onWiFi() {
        runShellScript("adb -P 5037 -s " + terminalConfig().getSerialNumber() +
                " shell am broadcast -a io.appium.settings.wifi -n io.appium.settings/.receivers.WiFiConnectionSettingReceiver --es setstatus enable");
    }

    public void offWiFi() {
        runShellScript("C:\\Program Files (x86)\\Minimal ADB and Fastboot\\adb shell am broadcast -a io.appium.settings.wifi -n io.appium.settings/.receivers.WiFiConnectionSettingReceiver --es setstatus disable");
    }

    public void onGsm() {
        runShellScript("adb -P 5037 -s " + terminalConfig().getSerialNumber() + " shell svc data enable");
    }

    public void offGsm() {
        runShellScript("adb -P 5037 -s " + terminalConfig().getSerialNumber() + " shell svc data disable");
    }

    public void getSerialNumber() {
        runShellScript("C:\\Program Files\\Git\\git-bash adb get-serialno >> serialDevices.txt");
    }

    private void runShellScript(String command) {

        try {
            Runtime.getRuntime().exec(command);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public UiAssertion check() {
        return new UiAssertion();
    }

    @Step("Оновлення налаштувань терміналом")
    public PayAppSteps updateSettings() {
        if (cashBoxMainPage().isOnPage()) {
            cashBoxMainPage().updateSettings();
        } else if (mainPage().onMainPage()) {
            mainPage().clickLogo();
        }

        return this;
    }

    private CashBoxMainPage cashBoxMainPage() {
        return new CashBoxMainPage();
    }

    private CashBoxPasswordPage cashBoxPasswordPage() {
        return new CashBoxPasswordPage();
    }

    private CashBoxMenuPage cashBoxMenuPage() {
        return new CashBoxMenuPage();
    }

    @Step("Вихід на головну сторінку ECR")
    public void goToMainPageEcr() {
        waite(2);
        // Проверяю, что я нахожусь на экране кассового совмещения с верными настройками
        if (cashBoxMainPage().isOnPage()) {
            //ничего не делать, всё готово
        } else if (cashBoxPasswordPage().isOnPage()) {
            cashBoxPasswordPage().exit();
        } else if (cashBoxMenuPage().isOnPage()) {
            cashBoxMenuPage().exit();
        } else if ($(id("id/btn_backspace")).isDisplayed()) {
            goBack();
        } else if (errorPage().isOnPage()) {
            errorPage().isLoaded().goOff();
        } else if (warningBatteryPage().isOnPage()) {
            warningBatteryPage().cancel();
            errorPage().isLoaded().goOff();
        } else if (refinementsSelectPage().isOnPage()) {
            refinementsSelectPage().cancel();
            errorPage().isLoaded().goOff();
        }

        if (mainPage().onMainPage()) {
            mainPage().clickIcon("Касове суміщення");
            waite(1);
        }

        cashBoxMainPage().isLoaded();
    }

    @SneakyThrows
    @Step("Жду {sec} сек")
    public PayAppSteps waite(Integer sec) {
        TimeUnit.SECONDS.sleep(sec);
        return this;
    }

    public WarningBatteryPage warningBatteryPage() {
        return new WarningBatteryPage();
    }

    public CashBoxCorrectionPage correctionPage() {
        return new CashBoxCorrectionPage();
    }
}
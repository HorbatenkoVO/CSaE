package pages.payApp.purchase;

import io.qameta.allure.Step;
import lombok.SneakyThrows;
import org.testng.asserts.Assertion;
import pages.payApp.BasePagePayApp;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static io.appium.java_client.AppiumBy.id;
import static tests.ui.payApp.BaseTest.getQaArm;

public class PaymentPage extends BasePagePayApp {
    private final String NFC_ICON = "id/iv_picc";
    private final String TEXT_STATUS = "id/tv_status";

    @SneakyThrows
    @Step("Читання картки NFC")
    public void readNFC() {
        int n = 6;

        for (int i = 0; i <= n; i++) {
            if (isOnPage()) {
                getQaArm().readNfcDown();
            } else {
                break;
            }

            if ($(id(TEXT_STATUS)).has(text("Помилка читання карти"))) {
                getQaArm().readNfcUp();
            } else if (!$(id(NFC_ICON)).isDisplayed()) {
                getQaArm().readNfcUp();
                break;
            } else {
                TimeUnit.MILLISECONDS.sleep(n * 500);
                getQaArm().readNfcUp();
            }

            if (i == n) {
                new Assertion().assertEquals("Card was not read", "Card read");
            }
        }
    }

    public void goBack() {
        $(id("id/btn_backspace")).click();
    }

    @Step("Очікую екран читання картки")
    public PaymentPage isLoaded() {
        $(id(NFC_ICON)).shouldBe(visible);
        return this;
    }

    @Step("Перевіряю чи я на екрані читання картки")
    public boolean isOnPage() {
        return $(id(NFC_ICON)).isDisplayed();
    }
}

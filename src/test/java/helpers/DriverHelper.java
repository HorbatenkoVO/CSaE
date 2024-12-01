package helpers;

import com.codeborne.selenide.WebDriverProvider;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;

import java.net.MalformedURLException;
import java.net.URL;

import static helpers.ConfigHelper.envConfig;
import static helpers.ConfigHelper.terminalConfig;
import static io.appium.java_client.remote.AutomationName.ANDROID_UIAUTOMATOR2;

public class DriverHelper implements WebDriverProvider {
    public static URL getAppiumServerUrl() {
        try {
            if (envConfig().isEnvLocal()) {
                return new URL(envConfig().urlAppiumLocal());
            } else {
                return new URL(envConfig().urlAppiumRemote());
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public WebDriver createDriver(Capabilities capabilities) {
        UiAutomator2Options options = new UiAutomator2Options();
        options.merge(capabilities);

        options.setAutomationName(ANDROID_UIAUTOMATOR2)
                .setPlatformName("android")
                .setAppPackage("udhtu.edu.ua.app")
                .setAppActivity("udhtu.edu.ua.app.presentation.splash.SplashActivity")
                .setDeviceName(terminalConfig().getSerialNumber())
                .setPlatformVersion(terminalConfig().getVersionAndroid())
                .setSkipServerInstallation(false)
                .setNoReset(true)
                .setAutomationName("UiAutomator2");

        return new AndroidDriver(getAppiumServerUrl(), options);
    }
}

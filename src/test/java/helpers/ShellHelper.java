package helpers;

import io.qameta.allure.Step;

import java.io.IOException;

public class ShellHelper {

    public String runScript(String script) {
        try {
            return Runtime.getRuntime().exec(script).toString();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    public String getSN() {
        return runScript("cat /sys/class/android_usb/android0/iSerial");
    }

    public String offGSM() {
        return runScript("svc data disable");
    }

    public String onGSM() {
        return runScript("svc data enable");
    }

    public String offWiFi() {
        return runScript("am broadcast -a io.appium.settings.wifi -n io.appium.settings/.receivers.WiFiConnectionSettingReceiver --es setstatus disable");
    }

    public String onWiFi() {
        return runScript("am broadcast -a io.appium.settings.wifi -n io.appium.settings/.receivers.WiFiConnectionSettingReceiver --es setstatus enable");
    }

    @Step("adb shell dumpsys battery set level {lvl}")
    public void setBattery(Integer lvl) {
        runScript("adb shell dumpsys battery set level " + lvl);
    }

    @Step("adb shell dumpsys battery reset")
    public void resetBattery() {
        runScript("adb shell dumpsys battery reset");
    }

}

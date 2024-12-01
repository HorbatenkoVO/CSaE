package config;

import org.aeonbits.owner.Config;

import java.util.List;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({"system:env",
        "system:properties",
        "classpath:config/terminal/${TERMINAL_SN}/term.properties",
        "classpath:config/terminal/${TERMINAL_SN}/${ENVIRONMENT}.properties",
        "classpath:config/terminal/2820086632/term.properties",
        "classpath:config/terminal/2820086632/test.properties"})
public interface TerminalConfig extends Config {
    @Key("serial.number")
    String getSerialNumber();

    @Key("certificate")
    String getCertificate();

    @Key("sim.ic.cid")
    @DefaultValue("8938001400107249991")
    String getSimIcCid();

    @Key("fw.version")
    @DefaultValue("A930_PayDroid_7.1.1_Virgo_V04.5.12_20240604")
    String getFw();

    @Key("settings.hash")
    @DefaultValue("665A11CA0D565BE5DDA71A46C874890F9DEA9142")
    String gethash();

    @Key("type")
    String getType();

    @DefaultValue("3.1.2.144")
    @Key("app.version")
    String getApplication();

    @DefaultValue("2.5.60")
    @Key("apl.version")
    String getUploader();

    @Separator(";")
    @Key("ecr.connect.ip")
    List<String> getEcrConnectIps();

    @Key("version.android")
    String getVersionAndroid();

    @Key("merchant.main")
    String getMerchantMain();

    @Key("merchant.multi")
    String getMerchantMulti();

    @Key("merchant.p")
    String getMerchantPhone();

    @Key("merchant.och")
    String getMerchantOch();

    @Key("merchant.aoch")
    String getMerchantAoch();

    @Key("merchant.mr")
    String getMerchantMr();

    @Key("merchant.k1")
    String getMerchantK1();

    @Key("merchant.k2")
    String getMerchantK2();

    @Key("vendor")
    String getVendor();
}

package config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({"system:env",
        "system:properties",
        "classpath:config/pan/${PAN}.properties",
        "classpath:config/pan/${ENVIRONMENT}.properties",
        "classpath:config/pan/test.properties"})
public interface PanConfig extends Config {
    @Config.Key("pan")
    String getPan();

    @Config.Key("track2")
    String getTrack2();

    @Config.Key("currency")
    String getCurrency();

    @Config.Key("expiry.date")
    String getExpiryDate();

    @Config.Key("cardholder")
    String getCardholder();

    @Config.Key("pos.entry.mode")
    String getPosEntryMode();

    @Config.Key("aid.mnemonic")
    String getAidMnemonic();

    @Config.Key("pan.masked.4.8.4")
    String getMasked484();

    @Config.Key("pan.masked.6.6.4")
    String getMasked664();

    @Config.Key("pan.masked.6.8.2")
    String getMasked682();

    @Config.Key("pan.masked.8.6.2")
    String getMasked862();

    @Config.Key("pan.masked.9.6.1")
    String getMasked961();
}

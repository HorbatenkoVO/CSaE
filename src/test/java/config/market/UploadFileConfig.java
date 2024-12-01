package config.market;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({"system:env",
        "system:properties",
        "classpath:market/application/${MARKET_UPLOAD_FILE}.properties",
        "classpath:market/application/ServicePos_9.9.9_sign.properties"})
public interface UploadFileConfig extends Config {
    @Config.Key("application.id")
    Integer getAppId();

    @Config.Key("application.version")
    String getAppVersion();

    @Config.Key("application.name")
    String getAppName();

    @Config.Key("terminal.type")
    Integer getTermType();

    @Config.Key("expected.file.name")
    String expectedFileName();

    @Config.Key("expected.size")
    Integer expectedSize();
}

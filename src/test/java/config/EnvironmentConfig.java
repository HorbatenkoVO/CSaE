package config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({"system:env",
        "system:properties",
        "classpath:config/environment/${ENVIRONMENT}.properties",
        "classpath:config/environment/environment.properties",
        "classpath:config/environment/test.properties"})
public interface EnvironmentConfig extends Config {

    @Key("url.settings.services")
    String getUrlSettingsServices();

    @Key("url.authorisation")
    String getUrlAuthorisation();

    @Key("env.type")
    String getEnvType();

    @Key("ip.host.transaction")
    String getIpHost();

    @Key("port.checkup.process")
    String getPortCheckupProcess();

    @Key("port.reversal.process")
    String getPortReversalProcess();

    @Key("port.payment.process")
    String getPortPaymentProcess();

    @Key("mainDataBase.host")
    String getMainDataBaseHost();

    @Key("mainDataBase.port")
    String getMainDataBasePort();

    @Key("mainDataBase.database")
    String getMainDataBase();

    @Key("url.appium.remote")
    String urlAppiumRemote();

    @Key("url.appium.local")
    String urlAppiumLocal();

    @Key("env.local")
    Boolean isEnvLocal();
}

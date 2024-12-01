package config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({"system:env",
        "system:properties",
        "classpath:config/secret.properties"})
public interface SecretConfig extends Config {

    @Key("tech.auth.login")
    String getAuthLogin();

    @Key("tech.password.prod")
    String getAuthPassword();

    @Key("tech.mainDataBase.login")
    String getPOSInterceptorLogin();

    @Key("tech.mainDataBase.password")
    String getPOSInterceptorPassword();
}
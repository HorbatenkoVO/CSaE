package config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({"system:env",
        "system:properties",
        "classpath:config/merchant/${MERCHANT}.properties",
        "classpath:config/merchant/SQA00BOT.properties"})
public interface MerchantConfig extends Config {
    @Config.Key("name")
    String getName();

    @Config.Key("id")
    String getId();

    @Config.Key("description")
    String getDescription();

    @Config.Key("id.external")
    String getIdExternal();

    @Config.Key("merchant.name")
    String getMerchantName();

    @Config.Key("merchant.display")
    String getMerchantDisplay();
}

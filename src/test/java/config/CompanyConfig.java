package config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({"system:env",
        "system:properties",
        "classpath:config/company/${COMPANY}.properties",
        "classpath:config/company/369632144.properties"})
public interface CompanyConfig extends Config {
    @Config.Key("name")
    String getName();

    @Config.Key("address")
    String getAddress();

    @Config.Key("receipt.count")
    Integer getReceiptCount();

    @Config.Key("external.id")
    String getExternalId();

    @Config.Key("city")
    String getCity();

    @Config.Key("company.id")
    Integer getCompanyId();

    @Config.Key("description")
    String getDescription();

    @Config.Key("company")
    String getCompany();

    @Config.Key("street")
    String getStreet();

    @Config.Key("okpo")
    String getOkpo();
}
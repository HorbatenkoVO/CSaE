package helpers;

import config.*;
import org.aeonbits.owner.ConfigFactory;

public class ConfigHelper {
    public static EnvironmentConfig envConfig() {
        return ConfigFactory.newInstance().create(EnvironmentConfig.class);
    }

    public static EnvironmentConfig envConfig(String env) {
        System.setProperty("ENVIRONMENT", env);
        return envConfig();
    }

    public static SecretConfig secretConfig() {
        return ConfigFactory.newInstance().create(SecretConfig.class);
    }

    public static TerminalConfig terminalConfig() {
        return ConfigFactory.newInstance().create(TerminalConfig.class);
    }

    public static TerminalConfig terminalConfig(String terminal) {
        System.setProperty("TERMINAL_SN", terminal);
        return terminalConfig();
    }

    public static PanConfig panConfig() {
        return ConfigFactory.newInstance().create(PanConfig.class);
    }

    public static PanConfig panConfig(String pan) {
        System.setProperty("PAN", pan);
        return panConfig();
    }

    public static MerchantConfig merchantConfig() {
        return ConfigFactory.newInstance().create(MerchantConfig.class);
    }

    public static MerchantConfig merchantConfig(String name) {
        System.setProperty("MERCHANT", name);
        return merchantConfig();
    }

    public static CompanyConfig companyConfig() {
        return ConfigFactory.newInstance().create(CompanyConfig.class);
    }

    public static CompanyConfig companyConfig(String name) {
        System.setProperty("COMPANY", name);
        return companyConfig();
    }
}

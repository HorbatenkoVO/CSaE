package steps;

import io.qameta.allure.Step;
import model.settingsServices.company.UpdateOperSet;
import model.settingsServices.ecr.settings.AddOrUpdateControlEcr;
import model.settingsServices.ecr.settings.RemoveControlEcr;
import model.settingsServices.mode.parameter.Set;
import model.settingsServices.operations.Bind;
import model.settingsServices.terminal.UpdateSettings;
import service.SettingsService;
import service.AuthorisationService;

import java.util.ArrayList;
import java.util.List;

import static helpers.ConfigHelper.envConfig;
import static service.GeneratorService.generator;

public class SettingsSteps {
    private final SettingsService webService = new SettingsService();
    private static String authAuthorisationTest;
    private static String authAuthorisationProd;

    @Step("Обновление настроек '{serialNumber}'")
    public SettingsSteps updateSettings(String serialNumber) {
        UpdateSettings body = new UpdateSettings()
                .serialNumber(serialNumber);

        webService.given(body, "terminal/update-settings");
        return this;
    }

    @Step("Связка терминала '{sn}', мерчанта '{merchant}', компании '{company}'")
    public SettingsSteps bindTermMerchComp(String sn, String merchant, String company, List<String> operList) {
        Bind body = new Bind()
                .merchant(merchant)
                .idExternal(company)
                .sn(sn)
                .setOper(operList);

        webService.given(body, "operations/bind");
        return this;
    }

    @Step("Связка терминала '{sn}', мерчанта '{merchant}', компании '{company}', опера '{oper}'")
    public SettingsSteps bindTermMerchComp(String sn, String merchant, String company, String oper) {
        List<String> operList = new ArrayList<>();
        operList.add(oper);
        return bindTermMerchComp(sn, merchant, company, operList);
    }

    public SettingsSteps bindTermMerchComp(String sn, String merchant, String company) {
        return bindTermMerchComp(sn, merchant, company, getOperList());
    }

    @Step("Обновление набора операций '{sn}'")
    public SettingsSteps updateOperSet(String sn, String idExternal) {
        UpdateOperSet body = new UpdateOperSet()
                .sn(sn)
                .idExternal(idExternal);

        webService.given(body, "company/update-oper-set");
        return this;
    }

    public List<String> getOperList() {
        List<String> oper = new ArrayList<>();
        oper.add("checkup");
        oper.add("purchase");
        oper.add("facial-payment");
        oper.add("pre-authorization");
        oper.add("preauth-log");
        oper.add("gratuity");
        oper.add("funds");
        oper.add("money-gathering");
        oper.add("currency-delivery");
        oper.add("register-service");
        oper.add("info-request");
        oper.add("table-signup");
        oper.add("table-log");
        oper.add("bank-transfer");
        oper.add("client-transfer");
        oper.add("support-call");
        oper.add("paper-request");
        oper.add("invoice-duplicate");
        oper.add("buy-and-cash");
        oper.add("reimbursement");
        oper.add("chargeback");
        oper.add("summary-report");
        oper.add("final-report");
        oper.add("data-report");
        oper.add("batch-log");
        oper.add("full-void");
        oper.add("partial-void");
        oper.add("cashier-login");
        oper.add("qr-bank");
        return oper;
    }

    public static String getAuthorisation() {
        if (envConfig().getEnvType().equals("prod") || envConfig().getEnvType().equals("stage")) {
            if (authAuthorisationProd == null) {
                authAuthorisationProd = new AuthorisationService().getAuthorisation();
            }
            return authAuthorisationProd;
        } else {
            if (authAuthorisationTest == null) {
                authAuthorisationTest = generator().getAuthorisation();
            }
            return authAuthorisationTest;
        }
    }

    @Step("Настройка кассового совмещения")
    public SettingsSteps updateControlEcr(AddOrUpdateControlEcr body) {
        webService.given(body, "ecr/settings/add-or-update-control-ecr");
        return this;
    }

    @Step("Удаление кассового совмещения")
    public SettingsSteps removeControlEcr(String sn, String merchant) {
        RemoveControlEcr body = new RemoveControlEcr()
                .sn(sn)
                .merchant(merchant);

        webService.given(body, "ecr/settings/remove-control-ecr");
        return this;
    }

    @Step("Завдання мода '{mode}'")
    public SettingsSteps setModeParameter(String sn, String mode) {
        Set body = new Set()
                .serialNumber(sn)
                .mode(mode);

        webService.given(body, "mode-parameter/set");
        return this;
    }

}
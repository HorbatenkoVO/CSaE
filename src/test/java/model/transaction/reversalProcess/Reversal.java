package model.transaction.reversalProcess;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import model.transaction.BaseTransactionRequest;

import static helpers.ConfigHelper.terminalConfig;
import static service.GeneratorService.generator;

@Data
@RequiredArgsConstructor
@Accessors(fluent = true)
public class Reversal implements BaseTransactionRequest {
    @SerializedName("operation") @Expose
    private String operation = "reversal";
    @SerializedName("pidoperation") @Expose
    private String pidoperation;
    @SerializedName("krok") @Expose
    private String krok;
    @SerializedName("date") @Expose
    private String date = generator().getDateFull();
    @SerializedName("settings_hash") @Expose
    private String settingsHash = terminalConfig().gethash();
    @SerializedName("app_version") @Expose
    private String appVersion = terminalConfig().getApplication();
    @SerializedName("trans_id") @Expose
    private String transId = generator().getTransaction();
    @SerializedName("session_id") @Expose
    private String sessionId = generator().getSessionId();
}

package model.transaction.checkupProcess;

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
public class Checkup implements BaseTransactionRequest {
    @Expose @SerializedName("uploader")
    private String uploader = terminalConfig().getUploader();
    @Expose @SerializedName("application")
    private String application = terminalConfig().getApplication();
    @Expose @SerializedName("comm")
    private String comm = "0";
    @Expose @SerializedName("date")
    private String date = generator().getDateFull();
    @Expose @SerializedName("fw")
    private String fw = terminalConfig().getFw();
    @Expose @SerializedName("operation")
    private String operation = "checkup";
    @SerializedName("pidoperation") @Expose
    private String pidoperation;
    @SerializedName("krok") @Expose
    private String krok;
    @Expose @SerializedName("hash")
    private String hash = terminalConfig().gethash();
    @Expose @SerializedName("sim-iccid")
    private String simIccid = terminalConfig().getSimIcCid();
    @Expose @SerializedName("terminalConfig")
    private String transaction = generator().getTransaction();
}
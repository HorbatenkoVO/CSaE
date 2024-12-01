package model;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import model.transaction.ecr.params.EcrParams;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Data
@RequiredArgsConstructor
@Accessors(fluent = true)
public class Request {
    @SerializedName("amount")
    @Expose
    private String amount;
    @SerializedName("app_version")
    @Expose
    private String appVersion;
    @SerializedName("company_id")
    @Expose
    private String companyId;
    @SerializedName("currency")
    @Expose
    private String currency;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("dukpt_ksn")
    @Expose
    private String dukptKsn;
    @SerializedName("ecr_params")
    @Expose
    private EcrParams ecrParams;
    @SerializedName("expiryDate")
    @Expose
    private String expiryDate;
    @SerializedName("emv_data")
    @Expose
    private String emvData;
    @SerializedName("cardholder")
    @Expose
    private String cardholder;
    @SerializedName("oper")
    @Expose
    private String oper;
    @SerializedName("pin_entered")
    @Expose
    private Boolean pinEntered;
    @SerializedName("pinblock")
    @Expose
    private String pinblock;
    @SerializedName("pos_entry_mode")
    @Expose
    private String posEntryMode;
    @SerializedName("session_id")
    @Expose
    private String sessionId;
    @SerializedName("settings_hash")
    @Expose
    private String settingsHash;
    @SerializedName("signature")
    @Expose
    private Boolean signature;
    @SerializedName("track2")
    @Expose
    private String track2;
    @SerializedName("trans_id")
    @Expose
    private String transId;

}
package model.transaction.cashBox;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

@Data
@RequiredArgsConstructor
@Accessors(fluent = true)
public class Params {
    @Expose @SerializedName("msgType")
    private String msgType;
    @Expose @SerializedName("code")
    private String code;
    @Expose @SerializedName("responseCode")
    private String responseCode;
    @Expose @SerializedName("amount")
    private String amount;
    @Expose @SerializedName("amountCash")
    private String amountCash;
    @Expose @SerializedName("discount")
    private String discount;
    @Expose @SerializedName("merchantId")
    private String merchantId;
    @Expose @SerializedName("facepay")
    private String facepay;
    @Expose @SerializedName("subMerchant")
    private String subMerchant;
    @Expose @SerializedName("invoiceNumber")
    private String invoiceNumber;
    @Expose @SerializedName("rrn")
    private String rrn;
    @Expose @SerializedName("prnFlag")
    private String prnFlag;
    @Expose @SerializedName("enterPIN")
    private String enterPin;
    @Expose @SerializedName("timeout")
    private String timeout;
    @Expose @SerializedName("attempt")
    private String attempt;
    @Expose @SerializedName("param")
    private String param;
    @Expose @SerializedName("srvNum")
    private String srvNum;
    @Expose @SerializedName("agreementNum")
    private String agreementNum;
    @Expose @SerializedName("amountOfParts")
    private String amountOfParts;
    @Expose @SerializedName("html")
    private String html;
    @Expose @SerializedName("deviceType")
    private String deviceType;
    @Expose @SerializedName("addamount")
    private String addamount;
    @Expose @SerializedName("approvalCode")
    private String approvalCode;
}

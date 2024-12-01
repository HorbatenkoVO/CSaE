package model.transaction.ecr.params;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

@Data
@RequiredArgsConstructor
@Accessors(fluent = true)
public class EcrParams {
    @SerializedName("amount") @Expose
    private String amount;
    @SerializedName("amount_cash") @Expose
    private String amountCash;
    @SerializedName("correction_discount_name") @Expose
    private String correctionDiscountName;
    @SerializedName("correction_pan") @Expose
    private String correctionPan;
    @SerializedName("correction_pos_entry_mode") @Expose
    private String correctionPosEntryMode;
    @SerializedName("discount") @Expose
    private String discount;
    @SerializedName("merchantId") @Expose
    private String merchantId;
    @SerializedName("merchant") @Expose
    private String merchant;
    @SerializedName("rrn") @Expose
    private String rrn;
    @SerializedName("invoiceNumber") @Expose
    private String invoiceNumber;
    @SerializedName("subMerchant") @Expose
    private String subMerchant;
    @SerializedName("srvNum") @Expose
    private String srvNum;
    @SerializedName("param") @Expose
    private String param;
}

package model.settingsServices.ecr.settings;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

import static steps.SettingsSteps.getAuthorisation;

@Data
@RequiredArgsConstructor
@Accessors(fluent = true)
public class AddOrUpdateControlEcr {
    @Expose @SerializedName("authorisation")
    private String authorisation = getAuthorisation();
    @Expose @SerializedName("merchant")
    private String merchant;
    @Expose @SerializedName("sn")
    private String sn;
    @Expose @SerializedName("adjust_timeout")
    private Integer adjustTimeout = 0; //0-10
    @Expose @SerializedName("ecr_only")
    private Boolean ecrOnly = false;
    @Expose @SerializedName("fill_receipt")
    private Boolean fillReceipt = true;
    @Expose @SerializedName("interface")
    private String interfaceAttr = "serial"; //"ethernet"
    @Expose @SerializedName("pass_cash_box")
    private String passCashBox;
    @Expose @SerializedName("mask_pan")
    private Boolean maskPan = false;
    @Expose @SerializedName("print_receipt")
    private Boolean printReceipt = true;
    @Expose @SerializedName("real_decline_code")
    private Boolean realDeclineCode = false;
    @Expose @SerializedName("send_f63")
    private Boolean sendF63 = false;
    @Expose @SerializedName("tcp_port")
    private Integer tcpPort = 2000;
}

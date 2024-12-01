package model.transaction.cashBox;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

@Data
@RequiredArgsConstructor
@Accessors(fluent = true)
public class CashBoxMessage {
    @Expose @SerializedName("method")
    private String method;
    @Expose @SerializedName("step")
    private Integer step;
    @Expose @SerializedName("params")
    private Params params;
    @Expose @SerializedName("error")
    private Boolean error;
    @Expose @SerializedName("errorDescription")
    private String errorDescription;
}

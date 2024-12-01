package model.settingsServices.operations;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

import static steps.SettingsSteps.getAuthorisation;

@Data
@RequiredArgsConstructor
@Accessors(fluent = true)
public class Bind {
    @SerializedName("authorisation")
    @Expose
    private String authorisation = getAuthorisation();
    @SerializedName("merchant")
    @Expose
    private String merchant;
    @Expose
    @SerializedName("sn")
    private String sn;
    @Expose
    @SerializedName("id_external")
    private String idExternal;
    @SerializedName("oper")
    private List<String> oper;

    public Bind setOper(String oper) {
        this.oper.add(oper);
        return this;
    }

    public Bind setOper(List<String> oper) {
        this.oper = oper;
        return this;
    }
}

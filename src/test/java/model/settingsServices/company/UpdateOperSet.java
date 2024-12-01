package model.settingsServices.company;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

import static steps.SettingsSteps.getAuthorisation;

@Data
@RequiredArgsConstructor
@Accessors(fluent = true)
public class UpdateOperSet {
    @SerializedName("authorisation")
    @Expose
    private String authorisation = getAuthorisation();;
    @SerializedName("id_external")
    @Expose
    private String idExternal;
    @SerializedName("sn")
    @Expose
    private String sn;
}

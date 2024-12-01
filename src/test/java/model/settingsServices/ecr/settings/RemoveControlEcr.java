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
public class RemoveControlEcr {
    @Expose @SerializedName("authorisation")
    private String authorisation = getAuthorisation();
    @Expose @SerializedName("merchant")
    private String merchant;
    @Expose @SerializedName("sn")
    private String sn;
}

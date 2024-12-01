package model.settingsServices.terminal;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

import static steps.SettingsSteps.getAuthorisation;

@Data
@RequiredArgsConstructor
@Accessors(fluent = true)
public class UpdateSettings {
    @Expose
    @SerializedName("authorisation")
    private String authorisation = getAuthorisation();
    @Expose
    @SerializedName("serial_number")
    private String serialNumber;
    @Expose
    @SerializedName("default_operation")
    private String defaultOperation;
}

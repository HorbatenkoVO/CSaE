package model.settingsServices.mode.parameter;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

import static steps.SettingsSteps.getAuthorisation;

@Data
@RequiredArgsConstructor
@Accessors(fluent = true)
public class Set {
        @SerializedName("authorisation")
        @Expose
        private String authorisation = getAuthorisation();
        @SerializedName("merchant")
        @Expose
        private String merchant;
        @SerializedName("serial_number")
        @Expose
        private String serialNumber ;
        @SerializedName("mode")
        @Expose
        private String mode;
}

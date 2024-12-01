package conditions;

import lombok.Builder;
import lombok.RequiredArgsConstructor;

import static com.codeborne.selenide.Selenide.$;
import static io.appium.java_client.AppiumBy.id;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@Builder
@RequiredArgsConstructor
public class UiIdAttrHasCondition implements UiCondition {
    private final String id;
    private final String attr;
    private final String value;

    @Override
    public void checkUi() {
        String actualResult = $(id("id/" + id)).attr(attr);
        assertThat(actualResult, equalTo(value));
    }
}

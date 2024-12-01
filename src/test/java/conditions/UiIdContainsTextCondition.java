package conditions;

import lombok.Builder;
import lombok.RequiredArgsConstructor;

import static com.codeborne.selenide.Selenide.$;
import static io.appium.java_client.AppiumBy.id;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

@Builder
@RequiredArgsConstructor
public class UiIdContainsTextCondition implements UiCondition{
    private final String locator;
    private final String text;
    @Override
    public void checkUi() {
        String actualResult = $(id("id/" + locator)).getText();
        assertThat(actualResult, containsString(text));
    }
}

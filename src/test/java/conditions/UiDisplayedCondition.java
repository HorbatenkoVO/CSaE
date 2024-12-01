package conditions;

import lombok.Builder;
import lombok.RequiredArgsConstructor;

import static com.codeborne.selenide.Selenide.$;
import static io.appium.java_client.AppiumBy.id;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@Builder
@RequiredArgsConstructor
public class UiDisplayedCondition implements UiCondition{
    private final String locator;
    @Override
    public void checkUi() {
        boolean displayed = $(id("id/"+ locator)).isDisplayed();
        String actualResult = locator + " is Displayed";
        if (!displayed) {
            actualResult = locator + " is Not Displayed";
        }
        assertThat(actualResult, equalTo(locator + " is Displayed"));
    }
}

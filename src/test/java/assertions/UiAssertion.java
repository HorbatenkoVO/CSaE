package assertions;

import conditions.UiCondition;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UiAssertion {
    public UiAssertion isDisplayed(UiCondition condition) {
        condition.checkUi();
        return this;
    }

    public UiAssertion shouldHave(UiCondition condition) {
        condition.checkUi();
        return this;
    }
}

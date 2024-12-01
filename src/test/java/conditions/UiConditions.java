package conditions;

import io.qameta.allure.Step;

public class UiConditions {

    @Step("Проверяю, что id '{id}' видно")
    public static UiDisplayedCondition idLocator(String id) {
        return new UiDisplayedCondition(id);
    }

    @Step("Проверяю, что id '{id}' с текстом '{text}'")
    public static UiIdHasTextCondition text(String id, String text) {
        return new UiIdHasTextCondition(id, text);
    }

    @Step("Проверяю, что id '{id}' содержит '{text}'")
    public static UiIdContainsTextCondition contains(String id, String text) {
        return new UiIdContainsTextCondition(id, text);
    }

    @Step("Проверяю, что id '{id}' содержит {attr} = {value}")
    public static UiIdAttrHasCondition hasAttrValue(String id, String attr, String value) {
        return new UiIdAttrHasCondition(id, attr, value);
    }
}

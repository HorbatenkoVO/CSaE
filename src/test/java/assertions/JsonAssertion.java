package assertions;

import conditions.Condition;
import conditions.SchemaCondition;
import io.qameta.allure.Step;
import io.restassured.path.json.JsonPath;
import lombok.RequiredArgsConstructor;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.nullValue;

@RequiredArgsConstructor
public class JsonAssertion {
    private final String request;

    public JsonAssertion shouldHave(Condition condition) {
        condition.checkUi(request);
        return this;
    }

    @Step("Проверяю, что нет \"{jsonPath}\"")
    public JsonAssertion shouldHaveNot(String jsonPath) {
        Object actualResult = JsonPath.from(request).get(jsonPath);
        assertThat(actualResult, nullValue());
        return this;
    }

    public JsonAssertion schemaValidation() {
        new SchemaCondition().schemaValidation(request);
        return this;
    }

    public JsonAssertion schemaValidation(String schema) {
        new SchemaCondition().schemaValidation(request, schema);
        return this;
    }

    @Step("Получаю значение \"{jsonPath}\"")
    public String getValue(String jsonPath) {
        try {
            return JsonPath.from(request).get(jsonPath).toString();
        } catch (NullPointerException e) {
            return "";
        }
    }
}


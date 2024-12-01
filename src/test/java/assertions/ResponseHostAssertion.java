package assertions;

import conditions.Condition;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import lombok.RequiredArgsConstructor;
import org.hamcrest.Matchers;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

@RequiredArgsConstructor
public class ResponseHostAssertion {
    private final Response response;

    public ResponseHostAssertion shouldHave(Condition condition) {
        condition.checkApi(response);
        return this;
    }

    @Step("Проверяю, что нет \"{jsonPath}\"")
    public ResponseHostAssertion shouldHaveNot(String jsonPath) {
        response.then().assertThat()
                .body(jsonPath, nullValue());
        return this;
    }

    public ResponseHostAssertion receiptHave(Condition condition) {
        condition.checkApi(response);
        return this;
    }

    @Step("Получаю значение \"{jsonPath}\"")
    public String getValue(String jsonPath) {
        try {
            return response.then().extract().body().jsonPath().get(jsonPath).toString();
        } catch (NullPointerException e) {
            return "";
        }
    }

    @Step("Проверяю, что ответ равен '{expected}'")
    public ResponseHostAssertion bodyEqualTo(String expected) {
        response.then().assertThat()
                .body(equalTo(expected));
        return this;
    }

    @Step("Проверяю схемой \"{schemaPath}/{schema}.json\"")
    public ResponseHostAssertion schemaValidation(String schemaPath, String schema) {
        response.then().body(matchesJsonSchemaInClasspath("schemas/" + schemaPath + "/" + schema + ".json"));
        return this;
    }

    @Step("Проверяю, что ответ между {min} и {max} байт")
    public ResponseHostAssertion lengthBetween(Integer min, Integer max) {
        byte[] dowloadedFile = response.then().extract().asByteArray();
        assertThat(dowloadedFile.length, Matchers.greaterThan(min));
        assertThat(dowloadedFile.length, Matchers.lessThan(max));
        return this;
    }

    @Step("Проверяю, что ответ равен {length} байт")
    public ResponseHostAssertion lengthEquals(Integer length) {
        byte[] dowloadedFile = response.then().extract().asByteArray();
        assertThat(dowloadedFile.length, equalTo(length));
        return this;
    }

    @Step("Получаю значение body")
    public String getBody() {
        return response.then().extract().body().asString();
    }
}

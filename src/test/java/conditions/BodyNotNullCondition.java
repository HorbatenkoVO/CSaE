package conditions;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;

@Builder
@RequiredArgsConstructor
public class BodyNotNullCondition implements Condition {
    private final String jsonPath;

    @Override
    public void checkApi(Response response) {
        response.then().assertThat()
                .body(jsonPath, notNullValue());
    }

    @Override
    public void checkUi(String innerJson) {
        Object actualResult = JsonPath.from(innerJson).get(jsonPath);

        assertThat(actualResult, notNullValue());
    }
}

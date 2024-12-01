package conditions;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@Builder
@RequiredArgsConstructor
public class BodyDoubleCondition implements Condition {
    private final String jsonPath;
    private final Double matcher;

    @Override
    public void checkApi(Response response) {
        String actual = response.then().extract().jsonPath().get(jsonPath).toString();
        assertThat(Double.parseDouble(actual), equalTo(matcher));
    }

    @Override
    public void checkUi(String innerJson) {
        Double actual = JsonPath.from(innerJson).getDouble(jsonPath);
        assertThat(actual, equalTo(matcher));
    }
}

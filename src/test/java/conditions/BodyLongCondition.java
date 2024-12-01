package conditions;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@Builder
@RequiredArgsConstructor
public class BodyLongCondition implements Condition {
    private final String jsonPath;
    private final long matcher;

    @Override
    public void checkApi(Response response) {
        response.then().assertThat()
                .body(jsonPath, equalTo(matcher));
    }

    @Override
    public void checkUi(String innerJson) {
        int actualResult = JsonPath.from(innerJson).getInt(jsonPath);
        assertThat(actualResult, equalTo(matcher));
    }
}

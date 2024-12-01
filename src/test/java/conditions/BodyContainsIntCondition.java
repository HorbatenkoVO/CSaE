package conditions;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsString;

@Builder
@RequiredArgsConstructor
public class BodyContainsIntCondition implements Condition{
    private final String jsonPath;
    private final Integer matcher;

    @Override
    public void checkApi(Response response) {
        response.then().assertThat()
                .body(jsonPath, contains(matcher));
    }

    @Override
    public void checkUi(String innerJson) {
        String actualResult = JsonPath.from(innerJson).getString(jsonPath);

        assertThat(actualResult, containsString(matcher.toString()));
    }
}

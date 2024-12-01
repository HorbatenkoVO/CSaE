package conditions;

import io.restassured.response.Response;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;

@Builder
@RequiredArgsConstructor
public class BodyNotContainsCondition implements Condition {
    private final String jsonPath;
    private final String matcher;

    @Override
    public void checkApi(Response response) {
        response.then().assertThat()
                .body(jsonPath, not(containsString(matcher.replace("\\",""))));
    }

    @Override
    public void checkUi(String request) {

    }
}

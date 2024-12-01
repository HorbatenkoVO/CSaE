package conditions;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import service.ConverterService;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@Builder
@RequiredArgsConstructor
public class BodyContainsStrCountCondition implements Condition {
    private final String jsonPath;
    private final String matcher;
    private final Integer count;

    @Override
    public void checkApi(Response response) {
        String json = response.then().extract().body().jsonPath().get(jsonPath).toString();
        int actual = (json.length() - json.replace(matcher.replace("\\", ""), "").length()) / matcher.replace("\\", "").length();
        assertThat(actual, equalTo(count));
    }

    @Override
    public void checkUi(String innerJson) {
        Object jsonObject = JsonPath.from(innerJson).getJsonObject(jsonPath);
        String json = new ConverterService().getJson(jsonObject);
        int actual = (json.length() - json.replace(matcher.replace("\\",""), "").length()) / matcher.replace("\\","").length();
        assertThat(actual, equalTo(count));
    }
}

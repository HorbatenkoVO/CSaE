package conditions;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import service.ConverterService;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

@Builder
@RequiredArgsConstructor
public class BodyContainsStrCondition implements Condition{
    private final String jsonPath;
    private final String matcher;

    @Override
    public void checkApi(Response response) {
        response.then().assertThat()
                .body(jsonPath, containsString(matcher.replace("\\","")));
    }

    @Override
    public void checkUi(String innerJson) {
        Object jsonObject = JsonPath.from(innerJson).getJsonObject(jsonPath);
        assertThat(new ConverterService().getJson(jsonObject), containsString(matcher));
    }
}

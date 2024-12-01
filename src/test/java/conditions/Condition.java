package conditions;

import io.restassured.response.Response;

public interface Condition {

    void checkApi(Response response);

    void checkUi(String request);
}
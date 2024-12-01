package service;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static helpers.ConfigHelper.envConfig;
import static helpers.ConfigHelper.secretConfig;

public class AuthorisationService {

    public String getAuthorisation() {
        RestAssured.defaultParser = Parser.JSON;
        RestAssured.registerParser("text/json", Parser.JSON);
        RequestSpecification reqSpec = new RequestSpecBuilder()
                .setBaseUri(envConfig().getUrlAuthorisation())
                .addHeader("Content-Type", "application/json")
                .addHeader("Charset", "UTF-8")
                .addHeader("Accept", "application/json")
                .addHeader("Accept", "UTF-8")
                .build();

        Response post = RestAssured
                .given()
                .spec(reqSpec)
                .body("{\"user\":{\"login\":\"" + secretConfig().getAuthLogin() + "\",\"password\":\"" + secretConfig().getAuthPassword() + "\"}}")
                .relaxedHTTPSValidation()
                .when()
                .post("/");

        post.then().log().body();

        return post.body().print();
    }
}

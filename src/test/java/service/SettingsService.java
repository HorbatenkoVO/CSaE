package service;

import assertions.ResponseHostAssertion;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static helpers.ConfigHelper.envConfig;
import static helpers.LogFilter.filters;
import static io.restassured.mapper.ObjectMapperType.GSON;

public class SettingsService {

    @Step(">>> Отправка запроса в SettingsServices")
    public ResponseHostAssertion given(Object object, String service) {
        RestAssured.defaultParser = Parser.JSON;
        RestAssured.registerParser("text/json", Parser.JSON);
        RequestSpecification reqSpec = new RequestSpecBuilder()
                .setBaseUri(envConfig().getUrlSettingsServices() + "/api/settings/services/")
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json")
                .addHeader("charset", "UTF-8")
                .build();

        Response post = RestAssured
                .given()
                .filter(filters().withCustomTemplates())
                .spec(reqSpec)
                .body(object, GSON)
                .relaxedHTTPSValidation()
                .when()
                .log().uri()
                .log().body()
                .post(service);

        post.then().log().body();

        return new ResponseHostAssertion(post);
    }
}

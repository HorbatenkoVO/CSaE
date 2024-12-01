package service;

import assertions.ResponseHostAssertion;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import model.transaction.BaseTransactionRequest;

import static helpers.ConfigHelper.*;
import static helpers.LogFilter.filters;
import static io.restassured.mapper.ObjectMapperType.GSON;

public class TransactionService {

    @Step(">>> Отправка запроса в Transaction")
    public ResponseHostAssertion given(BaseTransactionRequest object) {
        Response post = RestAssured
                .given()
                .filter(filters().withCustomTemplates())
                .spec(getReqSpec())
                .body(object, GSON)
                .urlEncodingEnabled(false)
                .when()
                .log().uri()
                .log().body()
                .post(getUrl(object.operation(), object.pidoperation(), object.krok()));

        post.then().log().body();

        return new ResponseHostAssertion(post);
    }

    public RequestSpecification getReqSpec() {
        RestAssured.defaultParser = Parser.JSON;
        RestAssured.registerParser("text/json", Parser.JSON);

        return new RequestSpecBuilder()
                .addHeader("terminal", terminalConfig().getSerialNumber())
                .addHeader("certificate", terminalConfig().getCertificate())
                .addHeader("Content-Type", "application/json; charset=UTF-8")
                .addHeader("Accept", "*/*")
                .addHeader("Connection", "keep-alive")
                .build();
    }

    /**
     * https://IP:port/{{environment}}/{{oper}}/{{suboper}}/{{step}}/term={{term_type}}
     **/
    private String getUrl(String oper, String suboper, String step) {
        String url;

        url = "http://" + envConfig().getIpHost() + ":" + getStagePort(oper) + "/";

        url = url + envConfig().getEnvType() + "/";
        url = url + oper + "/";

        if (suboper != null && !suboper.isEmpty()) {
            url = url + suboper + "/";
        }

        if (step != null && !step.isEmpty()) {
            url = url + step + "/";
        }

        url = url + "term=" + terminalConfig().getType();

        return url;
    }

    private String getStagePort(String oper) {
        return switch (oper) {
            case "checkup" -> envConfig().getPortCheckupProcess();
            case "reversal" -> envConfig().getPortReversalProcess();
            case "purchase", "preauth", "preauth-journal", "tips", "purchase-with-cashing-up", "facepay-purchase" ->
                    envConfig().getPortPaymentProcess();
            default -> "1234";
        };
    }
}

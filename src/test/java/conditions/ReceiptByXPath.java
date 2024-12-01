package conditions;

import io.restassured.response.Response;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.xml.sax.InputSource;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.StringReader;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@Builder
@RequiredArgsConstructor
public class ReceiptByXPath implements Condition {
    private final String xPath;
    private final String matcher;

    @Override
    public void checkApi(Response response) {
        String receipt = response.then().extract().body().jsonPath().get("receipt");

        try {
            InputSource inputXML = new InputSource(new StringReader(receipt));
            XPath xPathEvaluate = XPathFactory.newInstance().newXPath();
            String result = xPathEvaluate.evaluate(xPath, inputXML);

            assertThat(result, equalTo(matcher));
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void checkUi(String request) {
        //todo implement
    }

}

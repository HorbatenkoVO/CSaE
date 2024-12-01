package conditions;

import io.restassured.response.Response;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;

@Builder
@RequiredArgsConstructor
public class ReceiptById implements Condition {
    private final String id;
    private final String matcher;

    @Override
    public void checkApi(Response response) {
        String receipt = response.then().extract().body().jsonPath().get("receipt");

        try {
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            docBuilderFactory.setNamespaceAware(true);

            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            InputSource is = new InputSource(new StringReader(receipt));
            Document doc = docBuilder.parse(is);

            XPathFactory fact = XPathFactory.newInstance();
            XPath xpath = fact.newXPath();

            NodeList allElements = (NodeList) xpath.evaluate("//div[@id='" + id + "']", doc, XPathConstants.NODESET);

            ArrayList<String> elementValues = new ArrayList<>();

            for (int i = 0; i < allElements.getLength(); i++) {
                Node currentElement = allElements.item(i);
                elementValues.add(i, xpath.evaluate("*", currentElement, XPathConstants.NODE) != null ? null : currentElement.getTextContent());
            }

            assertThat(elementValues, hasItem(matcher));
        } catch (XPathExpressionException | ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void checkUi(String request) {
        //todo implement
    }
}

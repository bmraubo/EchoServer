import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;

public class XMLResponse implements RoutingInterface{
    String contentType = "application/xml;charset=utf-8";
    String responseBody = generateXMLResponseString();

    @Override
    public Response prepareResponse() {
        Response response = new Response();
        response.setStatusCode(200);
        response.setContentType(contentType);
        response.addResponseBody(responseBody);
        return response;
    }

    private String generateXMLResponseString() {
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();
            Element bodyElement = document.createElement("body");
            document.appendChild(bodyElement);
            Element responseElement = document.createElement("response");
            bodyElement.appendChild(responseElement);
            //bodyElement.appendChild(document.createTextNode("Hello World"));
            return convertXMLToString(document);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private String convertXMLToString(Document document) {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer;
        try {
            transformer = transformerFactory.newTransformer();
            StringWriter stringWriter = new StringWriter();
            transformer.transform(new DOMSource(document), new StreamResult(stringWriter));
            return stringWriter.getBuffer().toString();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

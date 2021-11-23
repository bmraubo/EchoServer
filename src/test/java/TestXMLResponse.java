import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestXMLResponse {

    @Test
    void XMLResponseTest() {
        XMLResponse testResponse = new XMLResponse();
        Response testResponseObject = testResponse.prepareResponse();

        String expectedXMLString = "<note><body>XML Response</body></note>";

        Assertions.assertEquals(200, testResponseObject.statusCode);
        Assertions.assertEquals("Content-Type: application/xml;charset=utf-8", testResponseObject.contentType);
        Assertions.assertEquals(expectedXMLString, testResponseObject.body);
    }
}

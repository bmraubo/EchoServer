import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestXMLResponse {

    @Test
    void XMLResponseTest() {
        XMLResponse testResponse = new XMLResponse();
        Response testResponseObject = testResponse.prepareResponse();

        String expectedXMLString = "<body><response>Hello World</response></body>";

        Assertions.assertEquals(200, testResponseObject.statusCode);
        Assertions.assertEquals("Content-Type: application/xml;charset=UTF-8", testResponseObject.contentType);
    }
}

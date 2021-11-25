import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestTextResponseBuilder {

    @Test
    void createTextResponseBuilder() {
        TextResponseBuilder testResponseBuilder = new TextResponseBuilder();

        Assertions.assertNotNull(testResponseBuilder.statusCodeMap);
        Assertions.assertEquals("OK", testResponseBuilder.statusCodeMap.get(200));
        Assertions.assertNotNull(testResponseBuilder.headers);
    }

    @Test
    void set200StatusCodeTest() {
        int statusCode = 200;

        TextResponseBuilder testResponseBuilder = new TextResponseBuilder();
        testResponseBuilder.setStatusCode(statusCode);

        Assertions.assertEquals(200, testResponseBuilder.statusCode);
        Assertions.assertEquals("OK", testResponseBuilder.reasonPhrase);
    }

    @Test
    void setHeaderTest() {
        String headerType = "Content-Type:";
        String headerValue = "application/json;charset=utf-8";

        TextResponseBuilder testResponseBuilder = new TextResponseBuilder();
        testResponseBuilder.setHeader(headerType, headerValue);

        Assertions.assertEquals(headerValue, testResponseBuilder.headers.get(headerType));
    }
}

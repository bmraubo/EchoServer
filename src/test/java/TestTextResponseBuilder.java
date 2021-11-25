import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;

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
        String headerType = "Content-Type: ";
        String headerValue = "application/json;charset=utf-8";
        String arrayHeaderType = "Allow: ";
        String[] arrayHeaderValue = {"GET", "OPTIONS"};

        TextResponseBuilder testResponseBuilder = new TextResponseBuilder();
        testResponseBuilder.setHeader(headerType, headerValue);
        testResponseBuilder.setHeader(arrayHeaderType,arrayHeaderValue);

        Assertions.assertEquals(headerValue, testResponseBuilder.headers.get(headerType));
        Assertions.assertEquals("GET, OPTIONS", testResponseBuilder.headers.get(arrayHeaderType));
    }

    @Test
    void setResponseTextBodyTest() {
        String testResponseBody = "Hello World";

        TextResponseBuilder testResponseBuilder = new TextResponseBuilder();
        testResponseBuilder.setResponseBody(testResponseBody);

        String expectedHeaderValue = String.valueOf(testResponseBody.getBytes(StandardCharsets.UTF_8).length);

        Assertions.assertArrayEquals(testResponseBody.getBytes(StandardCharsets.UTF_8), testResponseBuilder.responseBody);
        Assertions.assertEquals(expectedHeaderValue, testResponseBuilder.headers.get("Content-Length: "))
    }
}

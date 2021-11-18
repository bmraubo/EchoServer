import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestResponse {

    @Test
    void setStatusCodeTest() {
        int statusCode = 200;

        Response testResponse = new Response();
        testResponse.setStatusCode(statusCode);

        Assertions.assertEquals(200, testResponse.statusCode);
        Assertions.assertEquals("HTTP/1.1 200 OK\r\nConnection: close\r\n", testResponse.generateResponseString());
    }

    @Test
    void addResponseBodyTest() {
        int statusCode = 200;
        String responseBody = "Hello World";

        Response testResponse = new Response();
        testResponse.setStatusCode(statusCode);
        testResponse.addResponseBody(responseBody);

        Assertions.assertEquals("Content-Length: 11", testResponse.contentLength);
        Assertions.assertEquals("Hello World", testResponse.body);
        Assertions.assertTrue(testResponse.sendBody);

    }

    @Test
    void setAllowHeaderTest() {
        String[] allowedMethods = {"GET", "HEAD", "OPTIONS"};

        Response testResponse = new Response();
        testResponse.setAllowHeader(allowedMethods);

        String expectedHeader = "Allow: GET, HEAD, OPTIONS";

        Assertions.assertEquals(expectedHeader, testResponse.allowedMethods);
    }

    @Test
    void setContentLocationTest() {
        String contentLocation = "http://content.location";

        Response testResponse = new Response();
        testResponse.setLocationHeader(contentLocation);

        String expectedContentLocationHeader = "Content-Location: http://content.location";

        Assertions.assertEquals(expectedContentLocationHeader, testResponse.contentLocationHeader);
    }

    @Test
    void generateResponseLineTest() {
        Response testResponse = new Response();
        testResponse.setStatusCode(200);
        testResponse.addResponseBody("This is a test");

        String testResponseString = testResponse.generateResponseString();

        String expectedResponseString = "HTTP/1.1 200 OK\r\nConnection: close\r\nContent-Length: 14\r\n\r\nThis is a test";

        Assertions.assertEquals(expectedResponseString, testResponseString);
    }
}

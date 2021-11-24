import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestResponse {

    @Test
    void set200StatusCodeTest() {
        int statusCode = 200;

        Response testResponse = new Response();
        testResponse.setStatusCode(statusCode);

        Assertions.assertEquals(200, testResponse.statusCode);
        Assertions.assertEquals("HTTP/1.1 200 OK\r\n", testResponse.generateResponseLine());
    }

    @Test
    void set301StatusCodeTest() {
        int statusCode = 301;

        Response testResponse = new Response();
        testResponse.setStatusCode(statusCode);

        Assertions.assertEquals(301, testResponse.statusCode);
        Assertions.assertEquals("Moved Permanently", testResponse.reasonPhrase);
    }

    @Test
    void set404StatusCodeTest() {
        int statusCode = 404;

        Response testResponse = new Response();
        testResponse.setStatusCode(statusCode);

        Assertions.assertEquals(404, testResponse.statusCode);
        Assertions.assertEquals("Not Found", testResponse.reasonPhrase);
    }

    @Test
    void set500StatusCodeTest() {
        int statusCode = 500;

        Response testResponse = new Response();
        testResponse.setStatusCode(statusCode);

        Assertions.assertEquals(500, testResponse.statusCode);
        Assertions.assertEquals("Internal Server Error", testResponse.reasonPhrase);
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
    void addResponseBodyByteTest() {
        int statusCode = 200;
        byte[] responseBody = new byte[3];

        Response testResponse = new Response();
        testResponse.setStatusCode(statusCode);
        testResponse.addResponseBody(responseBody);

        Assertions.assertEquals("Content-Length: 3", testResponse.contentLength);
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
    void setLocationTest() {
        String location = "http://content.location";

        Response testResponse = new Response();
        testResponse.setLocationHeader(location);

        String expectedLocationHeader = "Location: http://content.location";

        Assertions.assertEquals(expectedLocationHeader, testResponse.locationHeader);
    }

    @Test
    void generateResponseLineTest() {
        Response testResponse = new Response();
        testResponse.setStatusCode(200);

        String testResponseString = testResponse.generateResponseLine();

        String expectedResponseString = "HTTP/1.1 200 OK\r\n";

        Assertions.assertEquals(expectedResponseString, testResponseString);
    }

    @Test
    void setContentTypeTest() {
        String contentType = "text/plain";

        Response testResponse = new Response();
        testResponse.setStatusCode(200);
        testResponse.setContentType(contentType);
        testResponse.addResponseBody("");

        String expectedContentTypeHeader = "Content-Type: text/plain";

        Assertions.assertEquals(expectedContentTypeHeader, testResponse.contentType);
    }
}

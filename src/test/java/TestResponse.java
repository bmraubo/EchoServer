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

}

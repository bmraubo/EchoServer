import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestRequest {
    String testRequest = "POST / HTTP/1.1\r\nContent-Length: 11\r\nContent-Type: text/html\r\n\r\nHello World";

    @Test
    void RequestLineExtractionTest() {
        Request testRequest = new Request(this.testRequest);
        Assertions.assertEquals("POST", testRequest.method);
        Assertions.assertEquals("/", testRequest.uri);
        Assertions.assertEquals("HTTP/1.1", testRequest.protocol);
    }

    @Test
    void RequestHeadersExtractionTest() {
        Request testRequest = new Request(this.testRequest);
        Assertions.assertEquals(11, testRequest.contentLength);
        Assertions.assertEquals("text/html", testRequest.contentType);
    }

    @Test
    void RequestBodyExtractionTest() {
        Request testRequest = new Request(this.testRequest);
        Assertions.assertEquals("Hello World", testRequest.body);
    }
}

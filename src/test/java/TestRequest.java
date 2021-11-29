import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestRequest {
    String testRequest = "GET /simple_get_with_body HTTP/1.1\r\nContent-Length: 11\r\n\r\nHello World";

    @Test
    void RequestLineExtractionTest() {
        RequestBuilder requestBuilder = new RequestBuilder();
        requestBuilder.extractRequest(testRequest);
        Assertions.assertEquals("GET /simple_get_with_body HTTP/1.1", requestBuilder.statusLine);
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

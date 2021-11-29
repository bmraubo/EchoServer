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
        RequestBuilder requestBuilder = new RequestBuilder();
        requestBuilder.extractRequest(testRequest);
        Assertions.assertEquals("11", requestBuilder.headers.get("Content-Length"));
    }

    @Test
    void RequestBodyExtractionTest() {
        RequestBuilder requestBuilder = new RequestBuilder();
        requestBuilder.extractRequest(testRequest);
        Assertions.assertEquals("Hello World", requestBuilder.body);
    }

    @Test
    void getMethodTest() {
        RequestBuilder requestBuilder = new RequestBuilder();
        requestBuilder.extractRequest(testRequest);

        Assertions.assertEquals("GET", requestBuilder.getMethod());
    }
}

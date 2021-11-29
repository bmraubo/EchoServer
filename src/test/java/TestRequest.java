import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;

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

    @Test
    void getURITest() {
        RequestBuilder requestBuilder = new RequestBuilder();
        requestBuilder.extractRequest(testRequest);

        Assertions.assertEquals("/simple_get_with_body", requestBuilder.getURI());
    }

    @Test
    void getProtocolTest() {
        RequestBuilder requestBuilder = new RequestBuilder();
        requestBuilder.extractRequest(testRequest);

        Assertions.assertEquals("HTTP/1.1", requestBuilder.getProtocol());
    }

    @Test
    void getHeadersTest() {
        RequestBuilder requestBuilder = new RequestBuilder();
        requestBuilder.extractRequest(testRequest);

        LinkedHashMap<String, String> headers = requestBuilder.getHeaders();

        Assertions.assertEquals("11", headers.get("Content-Length"));
    }
}

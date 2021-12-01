import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import site.bmraubo.http_server.Request;
import site.bmraubo.http_server.RequestBuilder;

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

    @Test
    void getBodyTest() {
        RequestBuilder requestBuilder = new RequestBuilder();
        requestBuilder.extractRequest(testRequest);

        Assertions.assertEquals("Hello World", requestBuilder.getBody());
    }

    @Test
    void parseRequestTest() {
        RequestBuilder requestBuilder = new RequestBuilder();
        Request request = new Request(requestBuilder);

        request.parseRequest(testRequest);

        Assertions.assertEquals("GET", request.method);
        Assertions.assertEquals("/simple_get_with_body", request.uri);
        Assertions.assertEquals("HTTP/1.1", request.protocol);
        Assertions.assertEquals("11", request.headers.get("Content-Length"));
        Assertions.assertEquals("Hello World", request.body);
    }
}

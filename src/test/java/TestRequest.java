import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestRequest {

    @Test
    void ConstructRequestObjectTest() {
        String input = "GET URI HTTP/1.1\r\n";
        Request testRequest = new Request(input);
        Assertions.assertEquals("GET", testRequest.method);
        Assertions.assertEquals("URI", testRequest.uri);
    }

    @Test
    void ExtractRequestBody() {
        String input = "GET URI HTTP/1.1\r\n\r\nHello World";
        Request testRequest = new Request(input);
        Assertions.assertEquals("Hello World", testRequest.body);
    }

    @Test
    void ExtractContentLength() {
        String input = "GET URI HTTP/1.1\r\nContent-Length: 11\r\n\r\nHello World";
        Request testRequest = new Request(input);
        Assertions.assertEquals(11, testRequest.contentLength);
    }
}

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;

public class TestResponse {

    @Test
    void generateResponseTest() {
        int statusCode = 200;
        String testBody = "Hello World";

        TextResponseBuilder textResponseBuilder = new TextResponseBuilder();
        Response response = new Response(textResponseBuilder);

        textResponseBuilder.setStatusCode(statusCode);
        textResponseBuilder.setResponseBody(testBody);

        response.generateResponse();

        String expectedResponseLine = "HTTP/1.1 200 OK\r\n";
        String expectedHeaders = "Content-Length: 11\r\n\r\n";
        byte[] expectedBody = testBody.getBytes(StandardCharsets.UTF_8);

        Assertions.assertEquals(expectedResponseLine, response.responseLine);
        Assertions.assertEquals(expectedHeaders, response.responseHeaders);
        Assertions.assertArrayEquals(expectedBody, response.responseBody);
    }
}

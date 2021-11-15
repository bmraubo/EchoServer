import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestResponse {

    @Test
    void GenerateBasicResponse() {
        int statusCode = 200;

        Response testResponse = new Response();
        testResponse.setStatusCode(statusCode);

        Assertions.assertEquals(200, testResponse.statusCode);
        Assertions.assertEquals("HTTP/1.1 200 OK\r\n", testResponse.generateResponseString());
    }
}

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import site.bmraubo.echoServerEndpoints.HealthCheck;
import site.bmraubo.HTTPServer.Request;
import site.bmraubo.HTTPServer.RequestBuilder;
import site.bmraubo.HTTPServer.Response;

import java.nio.charset.StandardCharsets;

public class TestHealthCheck {

    @Test
    void HealthCheckTest() {
        RequestBuilder requestBuilder = new RequestBuilder();
        Request request = new Request(requestBuilder);
        HealthCheck healthCheck = new HealthCheck();
        Response testResponse = healthCheck.prepareResponse(request);

        testResponse.generateResponse();

        String expectedResponseBody = "<html><body><p><strong>Status:</strong> pass</p></body></html>";

        Assertions.assertArrayEquals(expectedResponseBody.getBytes(StandardCharsets.UTF_8), testResponse.responseBody);
    }
}

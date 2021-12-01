import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import site.bmraubo.echo_server_endpoints.HealthCheck;
import site.bmraubo.http_server.Request;
import site.bmraubo.http_server.RequestBuilder;
import site.bmraubo.http_server.Response;

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

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;

public class TestHealthCheck {

    @Test
    void HealthCheckTest() {
        HealthCheck healthCheck = new HealthCheck();
        Response testResponse = healthCheck.prepareResponse();

        testResponse.generateResponse();

        String expectedResponseBody = "<html><body><p><strong>Status:</strong> pass</p></body></html>";

        Assertions.assertArrayEquals(expectedResponseBody.getBytes(StandardCharsets.UTF_8), testResponse.responseBody);
    }
}

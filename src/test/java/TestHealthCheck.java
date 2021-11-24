import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestHealthCheck {

    @Test
    void HealthCheckTest() {
        HealthCheck healthCheck = new HealthCheck();
        Response testResponse = healthCheck.prepareResponse();

        String expectedResponseBody = "<html><body><p>status is passing</p></body></html>";

        Assertions.assertEquals(expectedResponseBody, testResponse.body);
    }
}

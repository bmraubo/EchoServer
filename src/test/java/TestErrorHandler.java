import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Time;

public class TestErrorHandler {

    @Test
    void EmptyStringErrorTest() {
        String errorMessage = "Request read as empty, please try again.";

        ServerError serverError = new ServerError(errorMessage);
        Response response = serverError.prepareResponse();

        Assertions.assertEquals(400, response.responseBuilder.statusCode);
    }

    @Test
    void TimeoutErrorTest() {
        TimeoutError timeoutError = new TimeoutError();
        Response response = timeoutError.prepareResponse();

        Assertions.assertEquals(408, response.responseBuilder.statusCode);
    }
}

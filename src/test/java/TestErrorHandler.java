import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestErrorHandler {

    @Test
    void EmptyStringErrorTest() {
        String errorMessage = "Request read as empty, please try again.";

        ServerError serverError = new ServerError(errorMessage);
        Response response = serverError.prepareResponse();

        Assertions.assertEquals(400, response.responseBuilder.statusCode);
    }
}

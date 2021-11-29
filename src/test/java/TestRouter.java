import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestRouter {

    @Test
    void addRouteTest() {
        Router testRouter = new Router();
        TextResponse textResponse = new TextResponse();
        testRouter.addRoute("/text_response", textResponse);

        Assertions.assertEquals(textResponse, testRouter.routes.get("/text_response"));
    }
}

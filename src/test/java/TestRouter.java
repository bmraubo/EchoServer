import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import site.bmraubo.HTTPServer.Request;
import site.bmraubo.HTTPServer.RequestBuilder;
import site.bmraubo.HTTPServer.Router;

public class TestRouter {

    @Test
    void addRouteTest() {
        Router testRouter = new Router();
        TextResponse textResponse = new TextResponse();
        testRouter.addRoute("/text_response", textResponse);

        Assertions.assertEquals(textResponse, testRouter.routes.get("/text_response"));
    }

    @Test
    void connectTest() {
        String testRequestData = "GET /route_spy HTTP/1.1\r\n\r\n";
        RequestBuilder requestBuilder = new RequestBuilder();
        Request testRequest = new Request(requestBuilder);
        testRequest.parseRequest(testRequestData);

        Router testRouter = new Router();
        RouteSpy routeSpy = new RouteSpy();
        testRouter.addRoute("/route_spy", routeSpy);

        testRouter.connect(testRequest);

        Assertions.assertTrue(routeSpy.endpointReached);
    }
}

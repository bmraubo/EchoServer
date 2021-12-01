import site.bmraubo.HTTPServer.Endpoint;
import site.bmraubo.HTTPServer.Request;
import site.bmraubo.HTTPServer.Response;
import site.bmraubo.HTTPServer.ResponseBuilder;

public class RouteSpy implements Endpoint {
    boolean endpointReached;

    @Override
    public Response prepareResponse(Request request) {
        endpointReached = true;
        ResponseBuilder responseBuilder = new ResponseBuilder();
        Response response = new Response(responseBuilder);
        responseBuilder.setStatusCode(200);
        return response;
    }
}

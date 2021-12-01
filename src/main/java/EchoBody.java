import site.bmraubo.HTTPServer.Endpoint;
import site.bmraubo.HTTPServer.Request;
import site.bmraubo.HTTPServer.Response;
import site.bmraubo.HTTPServer.ResponseBuilder;

public class EchoBody implements Endpoint {
    String[] allowedMethods = {"POST", "OPTIONS"};

    public Response prepareResponse(Request request) {
        if (request.method.equals("POST")) {
            return simplePostRequest(request);
        } else {
            return MethodNotAllowed.prepareResponse(allowedMethods);
        }
    }

    private Response simplePostRequest(Request request) {
        System.out.println("Simple Post identified");
        ResponseBuilder responseBuilder = new ResponseBuilder();
        Response response = new Response(responseBuilder);
        responseBuilder.setStatusCode(200);
        responseBuilder.setResponseBody(request.body);
        return response;
    }
}

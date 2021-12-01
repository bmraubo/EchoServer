import site.bmraubo.HTTPServer.Endpoint;
import site.bmraubo.HTTPServer.Request;
import site.bmraubo.HTTPServer.Response;
import site.bmraubo.HTTPServer.ResponseBuilder;

public class SimpleGetWithBody implements Endpoint {
    String[] allowedMethods = {"GET", "OPTIONS"};

    @Override
    public Response prepareResponse(Request request) {
        if (request.method.equals("GET")) {
            return simpleGetWithBody();
        } else {
            return MethodNotAllowed.prepareResponse(allowedMethods);
        }
    }

    private Response simpleGetWithBody() {
        ResponseBuilder responseBuilder = new ResponseBuilder();
        Response response = new Response(responseBuilder);
        System.out.println("Simple Get with Body Identified");
        responseBuilder.setStatusCode(200);
        responseBuilder.setResponseBody("Hello world");
        return response;
    }
}

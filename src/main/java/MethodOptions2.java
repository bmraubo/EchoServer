import site.bmraubo.HTTPServer.Endpoint;
import site.bmraubo.HTTPServer.Request;
import site.bmraubo.HTTPServer.Response;
import site.bmraubo.HTTPServer.ResponseBuilder;

public class MethodOptions2 implements Endpoint {
    String[] allowedMethods = {"GET", "HEAD", "OPTIONS", "PUT", "POST"};

    public Response prepareResponse(Request request) {
        ResponseBuilder responseBuilder = new ResponseBuilder();
        Response response = new Response(responseBuilder);
        System.out.println("Simple Options to Method Options Identified");
        responseBuilder.setStatusCode(200);
        responseBuilder.setHeader("Allow", allowedMethods);
        responseBuilder.setResponseBody("");
        return response;
    }
}

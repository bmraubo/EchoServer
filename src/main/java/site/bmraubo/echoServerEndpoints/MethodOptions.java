package site.bmraubo.echoServerEndpoints;

import site.bmraubo.HTTPServer.Endpoint;
import site.bmraubo.HTTPServer.Request;
import site.bmraubo.HTTPServer.Response;
import site.bmraubo.HTTPServer.ResponseBuilder;

public class MethodOptions implements Endpoint {
    String[] allowedMethods = {"GET", "HEAD", "OPTIONS"};

    @Override
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

package site.bmraubo.echoServerEndpoints;

import site.bmraubo.HTTPServer.Endpoint;
import site.bmraubo.HTTPServer.Request;
import site.bmraubo.HTTPServer.Response;
import site.bmraubo.HTTPServer.ResponseBuilder;

public class SimpleGet implements Endpoint {
    String[] allowedMethods = {"GET", "HEAD", "OPTIONS"};

    @Override
    public Response prepareResponse(Request request) {
        if (request.method.equals("GET")) {
            return simpleGet();
        } else if (request.method.equals("HEAD")) {
            return simpleHead();
        } else {
            return MethodNotAllowed.prepareResponse(allowedMethods);
        }
    }

    private Response simpleGet() {
        ResponseBuilder responseBuilder = new ResponseBuilder();
        Response response = new Response(responseBuilder);
        System.out.println("Simple Get Identified");
        responseBuilder.setStatusCode(200);
        responseBuilder.setResponseBody("");
        return response;
    }

    private Response simpleHead() {
        ResponseBuilder responseBuilder = new ResponseBuilder();
        Response response = new Response(responseBuilder);
        System.out.println("Simple Head Identified");
        responseBuilder.setStatusCode(200);
        responseBuilder.setResponseBody("");
        return response;
    }
}

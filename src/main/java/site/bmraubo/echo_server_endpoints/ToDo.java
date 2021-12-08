package site.bmraubo.echo_server_endpoints;

import site.bmraubo.http_server.Endpoint;
import site.bmraubo.http_server.Request;
import site.bmraubo.http_server.Response;
import site.bmraubo.http_server.ResponseBuilder;

public class ToDo implements Endpoint {

    @Override
    public Response prepareResponse(Request request) {
        if (validateTask(request)) {
            ResponseBuilder responseBuilder = new ResponseBuilder();
            Response response = new Response(responseBuilder);
            responseBuilder.setStatusCode(201);
            responseBuilder.setHeader("Content-Type", "application/json;charset=utf-8");
            responseBuilder.setResponseBody(request.body);
            return response;
        } else {
            // create 400 response
        }
        return null;
    }

    private boolean validateTask(Request request) {
        return request.headers.get("Content-Type").equals("application/json");
    }
}

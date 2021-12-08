package site.bmraubo.echo_server_endpoints;

import site.bmraubo.http_server.Endpoint;
import site.bmraubo.http_server.Request;
import site.bmraubo.http_server.Response;

public class ToDo implements Endpoint {

    @Override
    public Response prepareResponse(Request request) {
        if (validateTask(request)) {
            // create 201 response
        } else {
            // create 400 response
        }
        return null;
    }

    private boolean validateTask(Request request) {
        return request.headers.get("Content-Type").equals("application/json");
    }
}

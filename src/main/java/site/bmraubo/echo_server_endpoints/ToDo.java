package site.bmraubo.echo_server_endpoints;

import site.bmraubo.http_server.Endpoint;
import site.bmraubo.http_server.Request;
import site.bmraubo.http_server.Response;
import site.bmraubo.http_server.ResponseBuilder;
import site.bmraubo.todo.TaskList;

public class ToDo implements Endpoint {
    TaskList taskList;

    public ToDo(TaskList taskList) {
        this.taskList = taskList;
    }

    @Override
    public Response prepareResponse(Request request) {
        if (validateContentType(request) && validateValues(request)) {
            ResponseBuilder responseBuilder = new ResponseBuilder();
            Response response = new Response(responseBuilder);
            responseBuilder.setStatusCode(201);
            responseBuilder.setHeader("Content-Type", "application/json;charset=utf-8");
            responseBuilder.setResponseBody(request.body);
            return response;
        } else if (validateContentType(request) && !validateValues(request)) {
            ResponseBuilder responseBuilder = new ResponseBuilder();
            Response response = new Response(responseBuilder);
            responseBuilder.setStatusCode(400);
            return response;
        } else {
            ResponseBuilder responseBuilder = new ResponseBuilder();
            Response response = new Response(responseBuilder);
            responseBuilder.setStatusCode(415);
            return response;
        }
    }

    private boolean validateContentType(Request request) {
        return request.headers.get("Content-Type").contains("application");
    }

    private boolean validateValues(Request request) {
        return request.body.contains(":") && request.body.contains("{") && request.body.contains("}");
    }
}

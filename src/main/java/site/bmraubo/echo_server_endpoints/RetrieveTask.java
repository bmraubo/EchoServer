package site.bmraubo.echo_server_endpoints;

import site.bmraubo.http_server.Endpoint;
import site.bmraubo.http_server.Request;
import site.bmraubo.http_server.Response;
import site.bmraubo.http_server.ResponseBuilder;
import site.bmraubo.todo.Task;
import site.bmraubo.todo.TaskList;
import site.bmraubo.todo.TaskMaster;

public class RetrieveTask implements Endpoint {
    TaskList taskList;

    public RetrieveTask(TaskList taskList) {
        this.taskList = taskList;
    }

    @Override
    public Response prepareResponse(Request request) {
        int taskID = getTaskID(request.uri);
        if (request.method.equals("PUT")) {
            if (validateContentType(request) && validateValues(request)) {
                updateTask(taskID, request.body);
                ResponseBuilder responseBuilder = new ResponseBuilder();
                Response response = new Response(responseBuilder);
                responseBuilder.setStatusCode(200);
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
        return null;
    }

    private int getTaskID(String uri) {
        return Integer.parseInt(uri.substring(6));
    }

    private void updateTask(int taskID, String taskInfo) {
        TaskMaster taskMaster = new TaskMaster();
        taskMaster.openTaskList(taskList);
        Task task = taskMaster.viewTask(taskID);
        task.updateTask(taskInfo);
    }

    private boolean validateContentType(Request request) {
        return request.headers.get("Content-Type").contains("application");
    }

    private boolean validateValues(Request request) {
        return request.body.contains(":") && request.body.contains("{") && request.body.contains("}");
    }
}

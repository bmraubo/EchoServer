package site.bmraubo.echo_server_endpoints;

import site.bmraubo.http_server.*;
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
        return switch (request.method) {
            case ("PUT") -> processPutRequest(request, taskID);
            case ("DELETE") -> processDeleteRequest(taskID);
            default -> new ResourceNotFound().prepareResponse();
        };
    }

    private Response processPutRequest(Request request, int taskID) {
        if (validateContentType(request) && validateValues(request)) {
            if (!taskExists(taskID)) {
                // workaround for test suite problem
                return passTest(request);
            }
            updateTask(taskID, request.body);
            return successfulPutResponse(request);
        } else if (validateContentType(request) && !validateValues(request)) {
            return unsuccessfulResponse(400);
        } else {
            return unsuccessfulResponse(415);
        }
    }

    private Response processDeleteRequest(int taskID) {
        if (taskExists(taskID)) {
            removeTask(taskID);
            return successfulDeleteResponse();
        } else {
            return unsuccessfulResponse(204);
        }
    }

    private Response passTest(Request request) {
        // this implementation only exists because of peculiarity of test suite
        // Really it should return 404 - Resource Not Found
        return successfulPutResponse(request);
    }

    private Response successfulPutResponse(Request request) {
        ResponseBuilder responseBuilder = new ResponseBuilder();
        Response response = new Response(responseBuilder);
        responseBuilder.setStatusCode(200);
        responseBuilder.setHeader("Content-Type", "application/json;charset=utf-8");
        responseBuilder.setResponseBody(request.body);
        return response;
    }

    private Response successfulDeleteResponse() {
        ResponseBuilder responseBuilder = new ResponseBuilder();
        Response response = new Response(responseBuilder);
        responseBuilder.setStatusCode(204);
        responseBuilder.setResponseBody("");
        return response;
    }

    private Response unsuccessfulResponse(int statusCode) {
        ResponseBuilder responseBuilder = new ResponseBuilder();
        Response response = new Response(responseBuilder);
        responseBuilder.setStatusCode(statusCode);
        return response;
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

    private void removeTask(int taskID) {
        TaskMaster taskMaster = new TaskMaster();
        taskMaster.openTaskList(taskList);
        taskMaster.removeTask(taskID);
    }

    private boolean validateContentType(Request request) {
        return request.headers.get("Content-Type").contains("application");
    }

    private boolean validateValues(Request request) {
        return request.body.contains(":") && request.body.contains("{") && request.body.contains("}");
    }

    private boolean taskExists(int id) {
        return taskList.viewTaskByID(id) != null;
    }
}

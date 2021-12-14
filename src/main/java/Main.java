import site.bmraubo.http_server.Router;
import site.bmraubo.http_server.Server;
import site.bmraubo.todo.PostgresTaskList;
import site.bmraubo.todo.Task;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
        int port = 5000;

        PostgresTaskList tasksTest = new PostgresTaskList();
        Task testTask = new Task("{\"task\":\"a task\"}");
        tasksTest.addTask(testTask);
        tasksTest.viewTaskByID(4);
        tasksTest.updateTask(4,"{\"task\":\"a new task\"}");
        tasksTest.removeTask(6);

        Router router = Routes.assignRoutes();
        Server server = new Server(router);
        System.out.println("Server Started");
        server.start(port);
    }
}

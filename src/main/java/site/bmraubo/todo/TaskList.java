package site.bmraubo.todo;

import org.json.JSONObject;

public interface TaskList {

    void addTask(Task task);
    Task viewTaskByID(int id);
    JSONObject getAllTasks();
    void updateTask(int id, String taskInfo);
    void removeTask(int id);
    boolean actionSuccessful();
}

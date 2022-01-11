package site.bmraubo.todo;

import org.json.JSONArray;

public interface TaskList {

    void addTask(Task task);
    Task viewTaskByID(int id);
    JSONArray getAllTasks();
    void updateTask(int id, String taskInfo);
    void removeTask(int id);
    boolean actionSuccessful();
}

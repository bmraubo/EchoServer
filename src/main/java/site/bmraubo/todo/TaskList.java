package site.bmraubo.todo;

public interface TaskList {

    void addTask(Task task);
    Task viewTaskByID(int id);
    void updateTask(int id, String taskInfo);
    void removeTask(int id);
}

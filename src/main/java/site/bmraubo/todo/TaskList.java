package site.bmraubo.todo;

public interface TaskList {

    void addTask(Task task);
    Task viewTaskByID(int id);
    void removeTask(int id);
}

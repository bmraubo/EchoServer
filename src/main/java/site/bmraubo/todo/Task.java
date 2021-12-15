package site.bmraubo.todo;

public class Task {
    int id;
    public String taskInfo;
    public String title;
    public String body;

    public Task(String taskInfo) {
        this.taskInfo = taskInfo;
    }

    public void updateTask(String taskInfo) {
        this.taskInfo = taskInfo;
    }

    public void setTaskID(int id) {
        this.id = id;
    }
}

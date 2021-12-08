package site.bmraubo.todo;

public class Task {
    int id;
    public String taskInfo;
    public String title;
    public String body;

    public Task(String taskInfo) {
        this.taskInfo = taskInfo;
        extractTaskInformation();
    }

    public void updateTask(String taskInfo) {
        this.taskInfo = taskInfo;
        extractTaskInformation();
    }

    public void setTaskID(int id) {
        this.id = id;
    }

    private void extractTaskInformation() {
        String[] taskInfoArray = taskInfo.split(":");
        title = taskInfoArray[0].substring(2,taskInfoArray[0].length()-1);
        body = taskInfoArray[1].substring(1, taskInfoArray[1].length()-2);
    }
}

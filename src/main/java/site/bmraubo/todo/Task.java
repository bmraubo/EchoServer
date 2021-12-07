package site.bmraubo.todo;

public class Task {
    int id;
    String title;
    String body;

    public Task(String taskInfo) {
        extractTaskInformation(taskInfo);
    }

    private void extractTaskInformation(String taskInfo) {
        String[] taskInfoArray = taskInfo.split(":");
        title = taskInfoArray[0].substring(2,taskInfoArray[0].length()-1);
        body = taskInfoArray[1].substring(1, taskInfoArray[1].length()-2);
    }
}

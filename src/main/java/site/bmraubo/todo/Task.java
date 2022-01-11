package site.bmraubo.todo;

import org.json.JSONObject;

public class Task {
    int id;
    public String taskInfo;
    public String body;
    JSONObject taskJSON;

    public Task(String taskInfo) {
        this.taskInfo = taskInfo;
        taskJSON = convertTaskInfoToJSON();
    }

    public void updateTask(String taskInfo) {
        this.taskInfo = taskInfo;
    }

    public void setTaskID(int id) {
        this.id = id;
    }

    private JSONObject convertTaskInfoToJSON() {
        return new JSONObject(taskInfo);
    }
}

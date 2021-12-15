package site.bmraubo.todo;

public class TaskMaster {
    TaskList taskList;

    public void openTaskList(TaskList taskList) {
        this.taskList = taskList;
    }

    public void addTask(String taskInfo) {
        Task task = new Task(taskInfo);
        taskList.addTask(task);
    }

    public Task viewTask(int id) {
        return taskList.viewTaskByID(id);
    }

    public void updateTask(int taskID, String taskInfo) {
        taskList.updateTask(taskID, taskInfo);
    }

    public void removeTask(int id) {
        taskList.removeTask(id);
    }
}

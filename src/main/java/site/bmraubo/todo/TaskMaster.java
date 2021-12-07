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
}

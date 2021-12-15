package site.bmraubo.todo;

public class PostgresSpy implements TaskList{
    boolean addedTask;
    int viewedTask;
    int updatedTask;
    int removedTask;


    @Override
    public void addTask(Task task) {
        addedTask = true;
    }

    @Override
    public Task viewTaskByID(int id) {
        Task testTask = new Task("{\"task\":\"test task\"}");
        testTask.setTaskID(id);
        viewedTask = id;
        return testTask;
    }

    @Override
    public void updateTask(int id, String taskInfo) {
        updatedTask = id;
    }

    @Override
    public void removeTask(int id) {
        removedTask = id;
    }
}

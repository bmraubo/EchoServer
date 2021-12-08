package site.bmraubo.todo;

import java.util.LinkedHashMap;

public class LocalMemoryTaskList implements TaskList{
    public LinkedHashMap<Integer, Task> taskList;

    public LocalMemoryTaskList() {
        generateTaskList();
    }

    @Override
    public void addTask(Task task) {
        task.setTaskID(calculateNextAvailableID());
        taskList.put(task.id, task);
    }

    @Override
    public Task viewTaskByID(int id) {
        return taskList.get(id);
    }

    @Override
    public void removeTask(int id) {
        taskList.remove(id);
    }

    private void generateTaskList() {
        taskList = new LinkedHashMap<>();
    }

    private int calculateNextAvailableID() {
        return taskList.size()+1;
    }
}

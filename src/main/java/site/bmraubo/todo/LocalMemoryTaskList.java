package site.bmraubo.todo;

import java.util.LinkedHashMap;

public class LocalMemoryTaskList implements TaskList{
    LinkedHashMap<Integer, Task> taskList;

    public LocalMemoryTaskList() {
        generateTaskList();
    }

    @Override
    public void addTask(Task task) {
        task.setTaskID(calculateNextAvailableID());
        taskList.put(task.id, task);
    }

    private void generateTaskList() {
        taskList = new LinkedHashMap<>();
    }

    private int calculateNextAvailableID() {
        return taskList.size()+1;
    }
}

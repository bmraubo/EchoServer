package site.bmraubo.todo;

import java.util.LinkedHashMap;

public class LocalMemoryTaskList implements TaskList{
    LinkedHashMap<String, String> taskList;

    public LocalMemoryTaskList() {
        generateTaskList();
    }

    private void generateTaskList() {
        taskList = new LinkedHashMap<>();
    }
}

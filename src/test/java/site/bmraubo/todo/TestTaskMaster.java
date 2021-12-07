package site.bmraubo.todo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestTaskMaster {

    @Test
    void openTaskListTest() {
        LocalMemoryTaskList localMemoryTaskList = new LocalMemoryTaskList();
        TaskMaster taskMaster = new TaskMaster();

        taskMaster.openTaskList(localMemoryTaskList);

        Assertions.assertEquals(localMemoryTaskList, taskMaster.taskList);
    }

    @Test
    void addTaskTest() {
        String todoRequest = "{\"task\":\"a new task\"}";

        LocalMemoryTaskList localMemoryTaskList = new LocalMemoryTaskList();
        TaskMaster taskMaster = new TaskMaster();

        taskMaster.openTaskList(localMemoryTaskList);

        taskMaster.addTask(todoRequest);

        Assertions.assertEquals(todoRequest, localMemoryTaskList.taskList.get(1).taskInfo);
    }
}


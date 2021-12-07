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
}


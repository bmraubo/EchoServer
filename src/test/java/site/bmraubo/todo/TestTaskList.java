package site.bmraubo.todo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestTaskList {

    @Test
    void generateTaskListTest() {
        LocalMemoryTaskList localMemoryTaskList = new LocalMemoryTaskList();

        Assertions.assertNotNull(localMemoryTaskList.taskList);
    }
}

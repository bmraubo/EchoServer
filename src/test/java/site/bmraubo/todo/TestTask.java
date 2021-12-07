package site.bmraubo.todo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestTask

{
    @Test
    void createTaskTest() {
        String todoRequest = "{\"task\":\"a new task\"}";

        Task task = new Task(todoRequest);

        Assertions.assertEquals("task", task.title);
        Assertions.assertEquals("a new task", task.body);
    }

    @Test
    void setTaskIDTest() {
        String todoRequest = "{\"task\":\"a new task\"}";

        Task task = new Task(todoRequest);
        task.setTaskID(233);

        Assertions.assertEquals(233, task.id);
    }
}

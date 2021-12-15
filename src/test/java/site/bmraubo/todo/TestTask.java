package site.bmraubo.todo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestTask

{
    @Test
    void createTaskTest() {
        String todoRequest = "{\"task\":\"a new task\"}";

        Task task = new Task(todoRequest);

        Assertions.assertEquals(todoRequest, task.taskInfo);
    }

    @Test
    void setTaskIDTest() {
        String todoRequest = "{\"task\":\"a new task\"}";

        Task task = new Task(todoRequest);
        task.setTaskID(233);

        Assertions.assertEquals(233, task.id);
    }

    @Test
    void updateTaskTest() {
        String initialRequest = "{\"task\":\"a new task\"}";
        Task task = new Task(initialRequest);

        String updatedTaskInfo = "{\"task\":\"an updated task\"}";

        task.updateTask(updatedTaskInfo);

        Assertions.assertEquals("{\"task\":\"an updated task\"}", task.taskInfo);
    }
}

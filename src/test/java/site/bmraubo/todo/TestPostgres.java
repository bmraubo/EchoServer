package site.bmraubo.todo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestPostgres {

    @Test
    void DatabaseConnectionTest() {
        PostgresSpy postgresSpy = new PostgresSpy();

        Assertions.assertTrue(postgresSpy.connectionEstablished);
    }

    @Test
    void addTaskToDatabaseTest() {
        Task testTask = new Task("{\"task\":\"test task info\"}");

        PostgresSpy postgresSpy = new PostgresSpy();
        seedDatabase(postgresSpy);
        postgresSpy.addTask(testTask);

        Assertions.assertTrue(postgresSpy.addedTask);
        tearDownDatabase(postgresSpy);
    }

    private void seedDatabase(PostgresSpy postgresSpy) {
        postgresSpy.seedDatabase();
    }

    private void tearDownDatabase(PostgresSpy postgresSpy) {
        postgresSpy.tearDownDatabase();
    }
}

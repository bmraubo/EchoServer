package site.bmraubo.todo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestPostgres {

    @Test
    void DatabaseConnectionTest() {
        PostgresTaskList postgresTaskList = new PostgresTaskList();

        Assertions.assertNotNull(postgresTaskList.conn);
    }
}

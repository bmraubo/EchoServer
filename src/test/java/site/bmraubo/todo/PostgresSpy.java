package site.bmraubo.todo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

public class PostgresSpy implements TaskList{
    Connection conn;
    boolean connectionEstablished;
    boolean addedTask;
    boolean viewedTask;
    boolean updatedTask;
    boolean removedTask;

    public PostgresSpy() {
        connectToDatabase();
    }

    public void connectToDatabase() {
        try {
            String url = "jdbc:postgresql://localhost:5432/testdb";
            Properties properties = new Properties();
            properties.setProperty("user", "postgres");
            properties.setProperty("password", "test1111");
            conn = DriverManager.getConnection(url, properties);
            connectionEstablished = true;
        } catch (Exception e) {
            connectionEstablished = false;
            e.printStackTrace();
        }

    }

    @Override
    public void addTask(Task task) {
        try {
            PreparedStatement addTaskStatement = conn.prepareStatement("INSERT INTO Tasks(taskinfo) VALUES(?)");
            addTaskStatement.setString(1, task.taskInfo);
            addTaskStatement.executeUpdate();
            addedTask = true;
        } catch (Exception e) {
            addedTask = false;
            e.printStackTrace();
        }
    }

    @Override
    public Task viewTaskByID(int id) {
        try {
            PreparedStatement addTaskStatement = conn.prepareStatement("SELECT * FROM Tasks WHERE taskid=?");
            addTaskStatement.setInt(1, id);
            ResultSet resultSet = addTaskStatement.executeQuery();
            resultSet.next();
            Task task = new Task(resultSet.getString("taskinfo"));
            task.setTaskID(resultSet.getInt("taskid"));
            viewedTask = true;
            return task;
        } catch (Exception e) {
            viewedTask = false;
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void updateTask(int id, String taskInfo) {
        updatedTask = true;
    }

    @Override
    public void removeTask(int id) {
        removedTask = true;
    }

    public void seedDatabase() {
        try {
            PreparedStatement createTable = conn.prepareStatement("CREATE TABLE Tasks (TaskID SERIAL PRIMARY KEY, TaskInfo varchar(255))");
            createTable.executeUpdate();
            Task seedTask = new Task("{\"task\":\"seed task info\"}");
            addTask(seedTask);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void tearDownDatabase() {
        try {
            PreparedStatement dropTable = conn.prepareStatement("DROP TABLE Tasks");
            dropTable.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

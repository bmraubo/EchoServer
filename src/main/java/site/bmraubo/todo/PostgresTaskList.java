package site.bmraubo.todo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Properties;

public class PostgresTaskList implements TaskList{
    Connection conn;

    public PostgresTaskList() {
        connectToDatabase();
    }


    public void connectToDatabase() {
        try {
            String url = "jdbc:postgresql://localhost:5432/tasklist";
            Properties properties = new Properties();
            properties.setProperty("user", "postgres");
            properties.setProperty("password", "WARna1444.");
            conn = DriverManager.getConnection(url, properties);
            System.out.println("Database Connection Successful");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addTask(Task task) {
        try {
            String sqlQuery = "INSERT INTO Tasks(taskinfo) VALUES('" + task.taskInfo + "')";
            PreparedStatement addTaskStatement = conn.prepareStatement("INSERT INTO Tasks(taskinfo) VALUES(?)");
            addTaskStatement.setString(1, task.taskInfo);
            addTaskStatement.executeUpdate();
            System.out.println("Task Added");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Task viewTaskByID(int id) {
        return null;
    }

    @Override
    public void removeTask(int id) {

    }





}

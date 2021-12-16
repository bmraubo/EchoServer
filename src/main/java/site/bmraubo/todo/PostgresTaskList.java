package site.bmraubo.todo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
            properties.setProperty("password", "test1111");
            conn = DriverManager.getConnection(url, properties);
            System.out.println("Database Connection Successful");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addTask(Task task) {
        try {
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
        try {
            PreparedStatement addTaskStatement = conn.prepareStatement("SELECT * FROM Tasks WHERE taskid=?");
            addTaskStatement.setInt(1, id);
            ResultSet resultSet = addTaskStatement.executeQuery();
            if (resultSet.next()) {
                Task task = new Task(resultSet.getString("taskinfo"));
                task.setTaskID(resultSet.getInt("taskid"));
                return task;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateTask(int id, String taskInfo) {
        try {
            PreparedStatement addTaskStatement = conn.prepareStatement("UPDATE Tasks SET taskinfo=? WHERE taskid=?");
            addTaskStatement.setString(1, taskInfo);
            addTaskStatement.setInt(2, id);
            addTaskStatement.executeUpdate();
            System.out.println("Task Updated");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeTask(int id) {
        try {
            PreparedStatement addTaskStatement = conn.prepareStatement("DELETE FROM Tasks WHERE taskid=?");
            addTaskStatement.setInt(1, id);
            addTaskStatement.executeUpdate();
            System.out.println("Task "+id+" Removed");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }





}

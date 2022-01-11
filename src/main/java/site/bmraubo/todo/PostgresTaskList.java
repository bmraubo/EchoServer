package site.bmraubo.todo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

public class PostgresTaskList implements TaskList{
    Connection conn;
    boolean success;

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
            success = true;
        } catch (Exception e) {
            success = false;
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
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public JSONArray getAllTasks() {
        JSONArray jsonArray = new JSONArray();
        try {
            PreparedStatement addTaskStatement = conn.prepareStatement("SELECT * FROM Tasks");
            ResultSet resultSet = addTaskStatement.executeQuery();
            if (resultSet.next()) {
                JSONObject taskData = new JSONObject();
                int taskID = resultSet.getInt("taskid");
                String taskInfo = resultSet.getString("taskinfo");
                boolean done = resultSet.getBoolean("done");
                taskData.put("id", taskID);
                taskData.put("task", taskInfo);
                taskData.put("done", done);
                jsonArray.put(taskData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonArray;
    }

    public void updateTask(int id, String taskInfo) {
        try {
            PreparedStatement addTaskStatement = conn.prepareStatement("UPDATE Tasks SET taskinfo=? WHERE taskid=?");
            addTaskStatement.setString(1, taskInfo);
            addTaskStatement.setInt(2, id);
            addTaskStatement.executeUpdate();
            System.out.println("Task Updated");
            success = true;
        } catch (Exception e) {
            success = false;
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
            success = true;
        } catch (Exception e) {
            success = false;
            e.printStackTrace();
        }
    }

    @Override
    public boolean actionSuccessful() {
        return success;
    }


}

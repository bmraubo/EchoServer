package site.bmraubo.todo;

import org.json.JSONObject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

public class PostgresSpy implements TaskList{
    Connection conn;
    boolean success;
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
            PreparedStatement addTaskStatement = conn.prepareStatement("INSERT INTO Tasks(taskinfo, done) VALUES(?, ?) RETURNING taskid");
            addTaskStatement.setString(1, task.taskJSON.getString("task"));
            addTaskStatement.setBoolean(2, false);
            ResultSet resultSet = addTaskStatement.executeQuery();
            if (resultSet.next()) {
                task.setTaskID(resultSet.getInt("taskid"));
                task.taskJSON.put("done", false);
            }
            success = true;
            addedTask = true;
        } catch (Exception e) {
            success = false;
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
            if (resultSet.next()) {
                Task task = new Task(resultSet.getString("taskinfo"));
                task.setTaskID(resultSet.getInt("taskid"));
                viewedTask = true;
                return task;
            } else {
                return null;
            }
        } catch (Exception e) {
            viewedTask = false;
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public JSONObject getAllTasks() {
        JSONObject taskListJSON = new JSONObject();
        try {
            PreparedStatement addTaskStatement = conn.prepareStatement("SELECT * FROM Tasks");
            ResultSet resultSet = addTaskStatement.executeQuery();
            if (resultSet.next()) {
                String taskID = resultSet.getString("taskid");
                String taskInfo = resultSet.getString("taskinfo");
                taskListJSON.put(taskID, taskInfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return taskListJSON;
    }

    @Override
    public void updateTask(int id, String taskInfo) {
        try {
            PreparedStatement addTaskStatement = conn.prepareStatement("UPDATE Tasks SET taskinfo=? WHERE taskid=?");
            addTaskStatement.setString(1, taskInfo);
            addTaskStatement.setInt(2, id);
            addTaskStatement.executeUpdate();
            success = true;
            updatedTask = true;
        } catch (Exception e) {
            success = false;
            updatedTask = false;
            e.printStackTrace();
        }
    }

    @Override
    public void removeTask(int id) {
        try {
            PreparedStatement addTaskStatement = conn.prepareStatement("DELETE FROM Tasks WHERE taskid=?");
            addTaskStatement.setInt(1, id);
            addTaskStatement.executeUpdate();
            success = true;
            removedTask = true;
        } catch (Exception e) {
            success = false;
            removedTask = false;
            e.printStackTrace();
        }

    }

    @Override
    public boolean actionSuccessful() {
        return success;
    }

    public void seedDatabase() {
        try {
            PreparedStatement createTable = conn.prepareStatement("CREATE TABLE Tasks (TaskID SERIAL PRIMARY KEY, TaskInfo varchar(255), done boolean)");
            createTable.executeUpdate();
            Task seedTask = new Task("{\"task\":\"seed task info\", \"done\": \"false\"}");
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

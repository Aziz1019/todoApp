package dao;

import models.Task;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class TaskDao {
    public static Connection con = null;
    public static Connection getConnection(){

        try{
            Class.forName("org.postgresql.Driver");
            con =  DriverManager.getConnection("jdbc:postgresql://localhost:5432/tasks","postgres","root123");
        }catch(Exception e){e.printStackTrace();}
        return con;
    }

    public static boolean make(Task task) {
        try {
            PreparedStatement statement = getConnection().prepareStatement("insert into task (task_name) VALUES (?)");
            statement.setString(1,task.getName());
            int i = statement.executeUpdate();
            return i > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean update(Task task) {
        try {
            PreparedStatement statement = getConnection().prepareStatement("update task set task_name = ?  where id = ?");
            statement.setString(1, task.getName());
            statement.setLong(2, task.getId());
            int i = statement.executeUpdate();
            return i>0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean delete(long id) {
        try {
            PreparedStatement statement = getConnection().prepareStatement("delete from task where  id = ?");
            statement.setLong(1,id);
            int i = statement.executeUpdate();
            return i>0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static List<Task> getTaskLists(){
        List<Task> list = new LinkedList<>();
        try {
            con = TaskDao.getConnection();
            PreparedStatement statement = con.prepareStatement("select * from task");
            allTasks(list, statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    private static void allTasks(List<Task> list, PreparedStatement statement) throws SQLException {
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()){
            Task task = new Task(resultSet.getLong(1), resultSet.getString(2));
            task.setId(resultSet.getLong(1));
            list.add(task);
        }
    }

    public static Task findById(Long id){
        Connection connection = getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("select * from task where id = ?");
            statement.setLong(1,id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Task task = new Task(resultSet.getString(2));
                task.setId(resultSet.getLong(1));
                return task;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


}

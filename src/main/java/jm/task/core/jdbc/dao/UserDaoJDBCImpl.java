package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        String s = "CREATE TABLE IF NOT EXISTS tablename(id BIGINT NOT NULL AUTO_INCREMENT, name varchar(255) NOT NULL, " +
                "lastName varchar(255) NOT NULL, age TINYINT, PRIMARY KEY(id))";
        try (Connection connection = Util.getConnection(); Statement statement = connection.createStatement()) {
            statement.executeUpdate(s);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println("Создана новая таблица");
    }

    public void dropUsersTable() {
        String s = "DROP TABLE tablename";
        try (Connection connection = Util.getConnection(); Statement statement = connection.createStatement()) {
            statement.executeUpdate(s);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println("удалена");
    }

    public void saveUser(String name, String lastName, byte age) {
        String s = "INSERT INTO tablename(name, lastName, age) VALUES ('" + name + "'," + "'" + lastName + "'," + age + ")";
        try (Connection connection = Util.getConnection(); Statement statement = connection.createStatement()) {
            statement.executeUpdate(s);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void removeUserById(long id) {
        String s = "DELETE FROM tablename WHERE id = " + id;
        try (Connection connection = Util.getConnection(); Statement statement = connection.createStatement()) {
            statement.executeUpdate(s);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        ResultSet resultSet = null;
        try (Connection connection = Util.getConnection(); Statement statement = connection.createStatement()) {
            resultSet = statement.executeQuery("SELECT * FROM tablename ");
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String lastName = resultSet.getString("lastName");
                int age = resultSet.getInt("age");
                User user = new User(name, lastName, (byte) age);
                list.add(user);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return list;
    }

    public void cleanUsersTable() {
        String s = "TRUNCATE TABLE tablename";
        try (Connection connection = Util.getConnection(); Statement statement = connection.createStatement()) {
            statement.executeUpdate(s);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}

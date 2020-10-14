package jm.task.core.jdbc.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/myschema?useUnicode=true&useSSL=true&useJDBCCompliantTimezoneShift=true" +
            "&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "127486";


    public static Connection getConnection(){
        Connection connection = null;
        try{
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Connection OK");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("Connection ERROR");
        }

        return connection ;
    }
    // реализуйте настройку соеденения с БД
}

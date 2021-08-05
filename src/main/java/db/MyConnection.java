package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class MyConnection {

    public static final String connectionUrl = "jdbc:mysql://localhost:3306/city_game?useUnicode=true&serverTimezone=UTC&user=cities_user&password=qwerty";
    static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return DriverManager.getConnection(connectionUrl);
    }

}

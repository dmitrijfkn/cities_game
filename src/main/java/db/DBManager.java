package db;

import entities.City;


import java.sql.*;

import java.util.logging.Level;
import java.util.logging.Logger;

public class DBManager {
    private static final Logger logger = Logger.getLogger(DBManager.class.getName());
    Connection connection;
    String connectionUrl = "jdbc:mysql://localhost:3306/servletsdb?useUnicode=true&serverTimezone=UTC&user=ServletsDbUser&password=qwerty";

    private DBManager(String connectionUrl) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            logger.log(Level.SEVERE, "Unable to load class", e);
        }
        try {
            this.connection = getConnection(connectionUrl);
            logger.log(Level.FINE, "New DBManager instance was created");
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Sql Exception in constructor", e);
        }
    }

    public static DBManager getInstance() {
        logger.log(Level.FINE, "DBManager constructor called");
        return new DBManager(ConnectionURL.connectionUrl);
    }

    private Connection getConnection(String connectionUrl) throws SQLException {
        return DriverManager.getConnection(connectionUrl);
    }

    public boolean insertUsedCity(int city_id) {
        String sqlQuery = "INSERT INTO used_cities (city_id) VALUES (?)";
        try (PreparedStatement stmt = connection.prepareStatement(sqlQuery, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
            stmt.setInt(1, city_id);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Sql Exception in insertUsedCity", e);
            return false;
        }
    }

    public boolean clearUsedCities() {
        String sqlQuery = "DELETE FROM used_cities";
        try (PreparedStatement stmt = connection.prepareStatement(sqlQuery)) {
            stmt.execute();
            return true;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Sql Exception in clearUsedCities ", e);
            return false;
        }
    }

    public City findCityByFirst(char firstCharacter) {
        ResultSet resultSet;

        String sqlQuery = "SELECT * FROM city WHERE name LIKE (?) ORDER BY RAND() LIMIT 1";
        try (PreparedStatement stmt = connection.prepareStatement(sqlQuery, ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_UPDATABLE)) {

            firstCharacter = Character.toUpperCase(firstCharacter);
            stmt.setString(1, firstCharacter + "%");
            resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                resultSet.first();
                City city = new City(resultSet.getInt("city_id"), resultSet.getString("name"));
                resultSet.close();
                return city;
            }
            return null;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Sql Exception in findCityByFirst", e);
            return null;
        }
    }

}

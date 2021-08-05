package db;

import entities.City;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CitiesDBManager  {
    private static final Logger logger = Logger.getLogger(CitiesDBManager.class.getName());

    public City findCityByFirst(char firstCharacter) {
        ResultSet resultSet;

        String sqlQuery = "SELECT c.city_id, c.name, u.city_id\n" +
                "FROM `city` c \n" +
                "LEFT OUTER JOIN `used_cities` u ON c.city_id = u.city_id \n" +
                "WHERE u.city_id IS NULL AND c.name LIKE (?) ORDER BY RAND() LIMIT 1 ";
        try (PreparedStatement stmt = MyConnection.getConnection().prepareStatement(sqlQuery, ResultSet.TYPE_SCROLL_SENSITIVE,
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

    public City findCity(String name) {
        ResultSet resultSet;

        String sqlQuery = "SELECT * FROM city WHERE name = (?) LIMIT 1";
        try (PreparedStatement stmt = MyConnection.getConnection().prepareStatement(sqlQuery, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)) {

            stmt.setString(1, name);
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

    public City findCity(int id) {
        ResultSet resultSet;

        String sqlQuery = "SELECT * FROM city WHERE city_id = (?) LIMIT 1";
        try (PreparedStatement stmt = MyConnection.getConnection().prepareStatement(sqlQuery, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)) {

            stmt.setInt(1, id);
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

    public City randomCity() {
        Random r = new Random();
        return findCityByFirst((char) (r.nextInt(26) + 'А'));
    }
}

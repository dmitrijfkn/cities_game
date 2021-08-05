package db;

import entities.City;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UsedCitiesDBManager {
    private static final Logger logger = Logger.getLogger(UsedCitiesDBManager.class.getName());

    public boolean insertUsedCity(int city_id) {
        String sqlQuery = "INSERT INTO used_cities (city_id) VALUES (?)";
        try (PreparedStatement stmt = MyConnection.getConnection().prepareStatement(sqlQuery, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
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
        try (PreparedStatement stmt = MyConnection.getConnection().prepareStatement(sqlQuery)) {
            stmt.execute();
            return true;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Sql Exception in clearUsedCities ", e);
            return false;
        }
    }

    public boolean isUsed(int id) {
        ResultSet resultSet;

        String sqlQuery = "SELECT * FROM used_cities WHERE city_id = (?) LIMIT 1";
        try (PreparedStatement stmt = MyConnection.getConnection().prepareStatement(sqlQuery, ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_UPDATABLE)) {

            stmt.setInt(1, id);
            resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                return true;
            } else
                return false;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Sql Exception in isUsed", e);
            return false;
        }
    }

    public int lastCityId() {
        ResultSet resultSet;

        String sqlQuery = "SELECT city_id FROM used_cities ORDER BY id DESC LIMIT 1";
        try (PreparedStatement stmt = MyConnection.getConnection().prepareStatement(sqlQuery, ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_UPDATABLE)) {
            resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                //resultSet.beforeFirst();
                return resultSet.getInt("city_id");
            } else
                return -1;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Sql Exception in isUsed", e);
            return -1;
        }
    }

    public List<City> getUsedCitiesList() {
        ResultSet resultSet;
        ArrayList<City> listOfCity = new ArrayList<>();
        String sqlQuery = "SELECT city_id FROM used_cities";
        CitiesDBManager citiesDBManager = new CitiesDBManager();


        try (PreparedStatement stmt = MyConnection.getConnection().prepareStatement(sqlQuery, ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_UPDATABLE)) {

            resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                resultSet.beforeFirst();
                while (resultSet.next()) {
                    listOfCity.add(citiesDBManager.findCity(resultSet.getInt("city_id")));
                }
            }
            resultSet.close();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Sql Exception in getUsedCitiesList", e);
            return listOfCity;
        }
        return listOfCity;
    }

}

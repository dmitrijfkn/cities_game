import db.DBManager;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

import nl.altindag.log.LogCaptor;
import org.junit.Test;


public class DBManagerTest {

    @Test
    public void DBManagerGetInstanceTest() {
        String expectedInfoMessage1 = "DBManager constructor called";
        String expectedInfoMessage2 = "New DBManager instance was created";

        LogCaptor logCaptor = LogCaptor.forClass(DBManager.class);
        DBManager dbManager = DBManager.getInstance();

        assertThat(logCaptor.getLogs())
                .hasSize(2)
                .containsExactly(expectedInfoMessage1, expectedInfoMessage2);
    }

    @Test
    public void findCityByLetterTest() {
        LogCaptor logCaptor = LogCaptor.forClass(DBManager.class);
        DBManager dbManager = DBManager.getInstance();

        assertNotNull(dbManager.findCityByFirst('п'));
    }

    @Test
    public void insertUsedCityTest() {
        DBManager dbManager = DBManager.getInstance();

        dbManager.insertUsedCity(10);
    }


    @Test
    public void clearUsedCitiesTest() {
        DBManager dbManager = DBManager.getInstance();

        dbManager.clearUsedCities();
    }

}



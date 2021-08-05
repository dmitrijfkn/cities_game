package servlets;

import db.CitiesDBManager;
import db.UsedCitiesDBManager;
import entities.City;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/begin")
public class NewGameServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf8");
        resp.setCharacterEncoding("utf8");

        CitiesDBManager citiesDBManager = new CitiesDBManager();
        UsedCitiesDBManager usedCitiesDBManager = new UsedCitiesDBManager();

        usedCitiesDBManager.clearUsedCities();

        City lastCityByBot = citiesDBManager.randomCity();
        usedCitiesDBManager.insertUsedCity(lastCityByBot.getId());

        req.setAttribute("lastCityByBot", lastCityByBot);
        getServletContext().getRequestDispatcher("/game.jsp").forward(req, resp);
    }
}

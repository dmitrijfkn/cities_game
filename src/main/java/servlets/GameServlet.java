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

@WebServlet("/next")
public class GameServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf8");
        resp.setCharacterEncoding("utf8");

        CitiesDBManager citiesDBManager = new CitiesDBManager();
        UsedCitiesDBManager usedCitiesDBManager = new UsedCitiesDBManager();

        String word = req.getParameter("word");
        if (word == null) {
            word = "";
        }else {
            req.setAttribute("word", word);
        }


        int lastId = usedCitiesDBManager.lastCityId();
        City lastCityByBot = citiesDBManager.findCity(lastId);


        if (word.length() == 0) {
            req.setAttribute("warning", "Введите город");
        } else if (Character.toUpperCase(word.charAt(0)) != Character.toUpperCase(lastCityByBot.getLastLetter())) {
            req.setAttribute("warning", "Первая буква вашего города не соответсвует последней");
        } else if (citiesDBManager.findCity(word) == null) {
            req.setAttribute("warning", "Мы не знаем такого города");
        } else if (usedCitiesDBManager.isUsed(citiesDBManager.findCity(word).getId())) {
            req.setAttribute("warning", "Такой город уже использовался");
        } else {
            City userCity = citiesDBManager.findCity(word);
            usedCitiesDBManager.insertUsedCity(userCity.getId());
            lastCityByBot = citiesDBManager.findCityByFirst(userCity.getLastLetter());
            usedCitiesDBManager.insertUsedCity(lastCityByBot.getId());
        }

        req.setAttribute("lastCityByBot",lastCityByBot);
        getServletContext().getRequestDispatcher("/game.jsp").forward(req, resp);
    }

}

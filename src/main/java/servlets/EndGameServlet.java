package servlets;

import db.UsedCitiesDBManager;
import entities.City;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/end")
public class EndGameServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UsedCitiesDBManager usedCitiesDBManager = new UsedCitiesDBManager();
        List<City> cityList = usedCitiesDBManager.getUsedCitiesList();
        req.setAttribute("cityList", cityList);

        getServletContext().getRequestDispatcher("/end.jsp").forward(req, resp);
    }
}

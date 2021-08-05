package console;

import db.CitiesDBManager;
import db.UsedCitiesDBManager;
import entities.City;

import java.util.Scanner;

public class ConsoleGame {
    public static void main(String[] args) {
        CitiesDBManager citiesDBManager = new CitiesDBManager();
        UsedCitiesDBManager usedCitiesDBManager = new UsedCitiesDBManager();

        Scanner sc = new Scanner(System.in);


        City lastCity = citiesDBManager.randomCity();
        System.out.println(lastCity);

        while (true) {
            String cityName = sc.nextLine();
            if (cityName.equals("break")) {
                usedCitiesDBManager.clearUsedCities();
                break;
            }else if (Character.toUpperCase(cityName.charAt(0)) != Character.toUpperCase(lastCity.getLastLetter())) {
                System.out.println("Первая буква вашего города не соответсвует последней");
            } else if (citiesDBManager.findCity(cityName) == null) {
                System.out.println("Мы не знаем такого города");
            } else if (usedCitiesDBManager.isUsed(citiesDBManager.findCity(cityName).getId()) == true) {
                System.out.println("Такой город уже использовался");
            }  else {
                City userCity = citiesDBManager.findCity(cityName);
                usedCitiesDBManager.insertUsedCity(userCity.getId());
                lastCity = citiesDBManager.findCityByFirst(userCity.getLastLetter());
                usedCitiesDBManager.insertUsedCity(lastCity.getId());
                System.out.println(lastCity);
            }

        }


    }
}

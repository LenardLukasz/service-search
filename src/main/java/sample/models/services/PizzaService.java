package sample.models.services;

import org.json.JSONObject;
import sample.PizzaData;
import sample.models.utils.Config;
import sample.models.utils.HttpConfig;

public class PizzaService {
    private static PizzaService ourInstance = new PizzaService();

    public static PizzaService getInstance() {
        return ourInstance;
    }

    public PizzaService() {
    }

    public void makeCall(String city) {
        parseJson(HttpConfig.makeHttpRequest(Config.APP_URL + "pizzerias+in+" + city + "&radius=500" + "&key=" + Config.APP_ID));
    }

    public void parseJson(String text) {
        JSONObject root = new JSONObject(text);
        JSONObject result = root.getJSONObject("results");

        String adress = result.getString("formatted_address");
        String name = result.getString("name");
        Float rating = result.getFloat("rating");

        PizzaData data = new PizzaData();
        data.setPizzeriaName(name);
        data.setPizzeriaAddress(adress);
        data.setPizzeriaRating(rating);
    }
}





// https://maps.googleapis.com/maps/api/place/textsearch/json?query=123+main+street&key=
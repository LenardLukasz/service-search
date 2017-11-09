package sample.models.services;

import org.json.JSONArray;
import org.json.JSONObject;
import sample.PizzaData;
import sample.models.utils.Config;
import sample.models.utils.HttpConfig;

import java.util.ArrayList;
import java.util.List;

public class PizzaService implements PizzaSubject {
    private static PizzaService ourInstance = new PizzaService();

    public static PizzaService getService() {
        return ourInstance;
    }

    private List<PizzaObserver> observerList;

    public PizzaService() {
        this.observerList = new ArrayList<>();
    }



    public void makeCall(String city) {
        parseJson(HttpConfig.makeHttpRequest(Config.APP_URL + "pizzerias+in+" + city + "&radius=500" + "&key=" + Config.APP_ID));
    }

    public void parseJson(String text) {
        JSONObject root = new JSONObject(text);
        JSONArray result = root.getJSONArray("results");

        String adress = null;
        String name = null;
        Float rating;
        Float maxRating = 0f;

        for (int i = 0; i < result.length(); i++) {
            JSONObject resultObject = result.getJSONObject(i);
            rating = resultObject.getFloat("rating");

             if (rating > maxRating){
                 maxRating = rating;
                 name = resultObject.getString("name");
                 adress = resultObject.getString("formatted_address");
             }
        }

        PizzaData data = new PizzaData();
        data.setPizzeriaName(name);
        data.setPizzeriaAddress(adress);
        data.setPizzeriaRating(maxRating);
        notifyObservers(data);
    }

    @Override
    public void register(PizzaObserver observer) {
        observerList.add(observer);
    }

    @Override
    public void notifyObservers(PizzaData data) {
        for (PizzaObserver pizzaObserver : observerList) {
            pizzaObserver.pizzaUpdate(data);
        }
    }
}





// https://maps.googleapis.com/maps/api/place/textsearch/json?query=123+main+street&key=
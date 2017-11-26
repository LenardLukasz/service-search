package sample.models.services;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import org.json.JSONArray;
import org.json.JSONObject;
import sample.PizzaData;
import sample.models.utils.Config;
import sample.models.utils.HttpConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PizzaService implements PizzaSubject {
    private static PizzaService ourInstance = new PizzaService();
    private ExecutorService executorService = Executors.newSingleThreadExecutor();

    public static PizzaService getService() {
        return ourInstance;
    }

    private List<PizzaObserver> observerList;

    public PizzaService() {
        this.observerList = new ArrayList<>();
    }



    public void makeCall(String city, String category) {
        executorService.execute(()->
                parseJson(HttpConfig.makeHttpRequest
                        (Config.APP_URL + category +"+in+" + city + "&radius=500" + "&key=" + Config.APP_ID)));

    }

    public void parseJson(String text) {
        JSONObject root = new JSONObject(text);
        JSONArray result = root.getJSONArray("results");

        String adress = null;
        String name = null;
        float rating;
        float maxRating = 0f;

        for (int i = 0; i < result.length(); i++) {
            JSONObject resultObject = result.getJSONObject(i);
            System.out.println(name = resultObject.getString("name"));
            rating = resultObject.optFloat("rating", 0F);
            
                if (rating > maxRating) {
                    maxRating = rating;
                    name = resultObject.getString("name");
                    adress = resultObject.getString("formatted_address");
                }



            PizzaData data = new PizzaData();
            data.setPizzeriaName(name);
            data.setPizzeriaAddress(adress);
            data.setPizzeriaRating(maxRating);
            notifyObservers(data);
        }
    }

    public ObservableList<String> categoryList(){
        ObservableList<String> categoryList = FXCollections.observableArrayList("bar","cafe","pizzerias","pubs","restaurants", "gasstation");
       return categoryList;
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
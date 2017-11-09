package sample.controllers;

import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import sample.PizzaData;
import sample.models.services.PizzaObserver;
import sample.models.services.PizzaService;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable, PizzaObserver{

    @FXML
    Label labelText;

    @FXML
    TextField inputCity;

    @FXML
    Button buttonShowPizzerias;

    @FXML
    ListView<String> chooseCategory;

    public static String value;

    private PizzaService pizzaService = PizzaService.getService();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        pizzaService.register(this);
        buttonShowPizzerias.setOnMouseClicked(e-> showPizzeria());
        chooseCategory.setItems(pizzaService.categoryList());

        chooseCategory.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                System.out.println(newValue);
                value = newValue.toString();
            }
        });
    }


    public void showPizzeria(){
        String city;
        city = inputCity.getText();
        pizzaService.makeCall(city.replace(" ","+"), value);

    }

    @Override
    public void pizzaUpdate(PizzaData data) {
        Platform.runLater(()->
        labelText.setText("Nazwa pizzeri: " + data.getPizzeriaName()
                         +"\n Adres: " + data.getPizzeriaAddress()
                         +"\n Ocena: " + data.getPizzeriaRating()));
    }


}

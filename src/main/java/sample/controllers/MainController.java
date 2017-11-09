package sample.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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

    private PizzaService pizzaService = PizzaService.getService();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        pizzaService.register(this);
        buttonShowPizzerias.setOnMouseClicked(e-> showPizzeria());

       // pizzaService.makeCall("szewska+Cracow");

    }

    public void showPizzeria(){
        pizzaService.makeCall(inputCity.getText());
    }

    @Override
    public void pizzaUpdate(PizzaData data) {
        Platform.runLater(()->
        labelText.setText("Nazwa pizzeri: " + data.getPizzeriaName()
                         +"\n Adres: " + data.getPizzeriaAddress()
                         +"\n Ocena: " + data.getPizzeriaRating()));
    }


}

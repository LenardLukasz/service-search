package sample.controllers;

import javafx.fxml.Initializable;
import sample.models.services.PizzaService;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable{
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        PizzaService makeCallProbe = new PizzaService();
        makeCallProbe.makeCall("szewska+Cracow");
    }
}

package sample.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import sample.models.services.PizzaService;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable{

    @FXML
    Label labelText;

    @FXML
    TextField inputCity;

    @FXML
    Button buttonShowPizzerias;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        PizzaService makeCallProbe = new PizzaService();
        makeCallProbe.makeCall("szewska+Cracow");

        buttonShowPizzerias.
    }
}

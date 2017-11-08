package sample.controllers;

import javafx.fxml.Initializable;
import sample.models.services.MakeCallProbe;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable{
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        MakeCallProbe makeCallProbe = new MakeCallProbe();
        makeCallProbe.makeCall("Sydney");
    }
}

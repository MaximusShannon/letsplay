package controllers.subsection.controllers;

import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class SubSectionHomeController {

    @FXML
    private Text changeableText;
    @FXML
    private Button changeText;

    @FXML
    private void changeText(){

        changeableText.setText("Here we are now.");
    }


}

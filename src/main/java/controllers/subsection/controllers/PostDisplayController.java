package controllers.subsection.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;


import java.net.URL;
import java.util.ResourceBundle;

public class PostDisplayController implements Initializable {

    public static String game;

    @FXML
    private Text setTextLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //setTextLabel.setText(game);
        System.out.println(game);
    }

}

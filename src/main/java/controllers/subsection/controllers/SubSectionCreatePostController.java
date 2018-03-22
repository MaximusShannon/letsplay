package controllers.subsection.controllers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SubSectionCreatePostController implements Initializable {

    private ArrayList<MenuItem> menuItems;

    @FXML
    private ChoiceBox<String> languageSpoken;
    @FXML
    private ChoiceBox<String> gamePlayed;
    @FXML
    private ChoiceBox<String> timeZone;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        instantiateLanguageSpokenChoiceBox();
        instantiateGamePlayedChoiceBox();
        instantiateTimeZoneChoiceBox();

    }

    private void instantiateLanguageSpokenChoiceBox(){

        languageSpoken.setItems(FXCollections.observableArrayList("English", "Irish",
                "French", "German",
                "Norwegian", "Dutch",
                "Russian"));
        languageSpoken.getSelectionModel().selectFirst();
    }

    private void instantiateGamePlayedChoiceBox(){

        gamePlayed.setItems(FXCollections.observableArrayList("League Of Legends", "FORTNITE",
                "PUBG", "Counter-Strike: Global Offensive",
                "Rust", "DayZ",
                "Rainbow SIX: Siege", "Escape From Tarkov"));
        gamePlayed.getSelectionModel().selectFirst();
    }

    private void instantiateTimeZoneChoiceBox(){

        timeZone.setItems(FXCollections.observableArrayList("UTC-1", "UTC",
                "UTC+1", "UTC+2",
                "UTC+3", "UTC+4",
                "UTC+5"));
        timeZone.getSelectionModel().selectFirst();
    }

}

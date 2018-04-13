package controllers.subsection.controllers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class GroupCreationController implements Initializable {

    @FXML private TextField groupName;
    @FXML private TextField comsAddress;
    @FXML private TextArea groupDescription;
    @FXML private ChoiceBox<String> groupVisability;
    @FXML private ChoiceBox<String> gamePlayed;
    @FXML private ChoiceBox<String> comsChannel;
    @FXML private ChoiceBox<String> activityLevel;
    @FXML private ChoiceBox<String> languageSpoken;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        instantiateGroupVisabilityChoiceBox();
        instantiateGroupGamePlayedChoiceBox();
        instantiateGroupComsChannelChoiceBox();
        instantiateGroupActivityLevelChoiceBox();
        instantiateGroupMainLanguageChoiceBox();
    }

    private void instantiateGroupVisabilityChoiceBox(){

        groupVisability.setItems(FXCollections.observableArrayList("Public", "Private"
        ));
        groupVisability.getSelectionModel().selectFirst();
    }

    private void instantiateGroupGamePlayedChoiceBox(){

        gamePlayed.setItems(FXCollections.observableArrayList("League Of Legends", "FORTNITE",
                "PUBG", "Counter-Strike: Global Offensive",
                "Rust", "DayZ",
                "Rainbow SIX: Siege", "Escape From Tarkov"));
        gamePlayed.getSelectionModel().selectFirst();

    }

    private void instantiateGroupComsChannelChoiceBox(){

        comsChannel.setItems(FXCollections.observableArrayList("TeamSpeak 3", "Discord",
                "Skype", "Other"
        ));
    }

    private void instantiateGroupActivityLevelChoiceBox(){

        activityLevel.setItems(FXCollections.observableArrayList("At least 5 hours weekly",
                "At least 10 hours weekly", "At least 15 hours weekly",
                "At least 20 hours weekly", "25+ hours weekly"));
    }

    private void instantiateGroupMainLanguageChoiceBox(){

        languageSpoken.setItems(FXCollections.observableArrayList("English", "Irish",
                "French", "German",
                "Norwegian", "Dutch",
                "Russian"));
    }

}

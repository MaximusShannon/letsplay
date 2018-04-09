package controllers.subsection.controllers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import models.Session;

import java.net.URL;
import java.util.ResourceBundle;


public class ProfilePostUpdateController implements Initializable {

    @FXML private TextField postTitle;
    @FXML private TextField ageRange;
    @FXML private TextArea postDescription;
    @FXML private TextField postTags;
    @FXML private CheckBox micRequired;
    @FXML private CheckBox competitivePlayersAccepted;
    @FXML private CheckBox casualPlayersAccepted;
    @FXML private CheckBox acceptFemales;
    @FXML private CheckBox acceptMales;
    @FXML private ChoiceBox<String> languageSpoken;
    @FXML private ChoiceBox<String> gamePlayed;
    @FXML private ChoiceBox<String> timeZone;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        setInitialValues();
    }

    private void setInitialValues(){

        postTitle.setText(Session.updateablePost.getPostTitle());
        ageRange.setText(Session.updateablePost.getAgeRange());
        postDescription.setText(Session.updateablePost.getPostDescription());
        postTags.setText(Session.updateablePost.getPostTags());
        micRequired.setSelected(Session.updateablePost.isMicrophoneRequired());
        competitivePlayersAccepted.setSelected(Session.updateablePost.isAcceptingCompetitivePlayers());
        casualPlayersAccepted.setSelected(Session.updateablePost.isAcceptingCasualPlayers());
        acceptFemales.setSelected(Session.updateablePost.isAcceptFemales());
        acceptMales.setSelected(Session.updateablePost.isAcceptMales());

        instantiateLanguageSpokenChoiceBox();
        instantiateGamePlayedChoiceBox();
        instantiateTimeZoneChoiceBox();

        languageSpoken.getSelectionModel().select(Session.updateablePost.getLanguageSpoken());
        gamePlayed.getSelectionModel().select(Session.updateablePost.getGamePlayed());
        timeZone.getSelectionModel().select(Session.updateablePost.getTimeZone());
    }

    private void instantiateLanguageSpokenChoiceBox(){

        languageSpoken.setItems(FXCollections.observableArrayList("English", "Irish",
                "French", "German",
                "Norwegian", "Dutch",
                "Russian"));
    }

    private void instantiateGamePlayedChoiceBox(){

        gamePlayed.setItems(FXCollections.observableArrayList("League Of Legends", "FORTNITE",
                "PUBG", "Counter-Strike: Global Offensive",
                "Rust", "DayZ",
                "Rainbow SIX: Siege", "Escape From Tarkov"));
    }

    private void instantiateTimeZoneChoiceBox(){

        timeZone.setItems(FXCollections.observableArrayList("UTC-1", "UTC",
                "UTC+1", "UTC+2",
                "UTC+3", "UTC+4",
                "UTC+5"));
    }
}

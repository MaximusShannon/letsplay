package controllers.innerviewcontrollers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Slider;

import java.net.URL;
import java.util.ResourceBundle;

public class SubSectionSettingsController implements Initializable {

    @FXML private Slider ageRangeSlider;
    @FXML private ChoiceBox<String> languageSpoken;
    @FXML private ChoiceBox<String> gamePlayed;

    @Override
    public void initialize(URL url, ResourceBundle rb){

        setSliders();
        instantiateGamePlayedChoiceBox();
        instantiateGroupMainLanguageChoiceBox();
    }

    private void setSliders(){

        ageRangeSlider.setMin(1);
        ageRangeSlider.setMax(7);
        ageRangeSlider.setBlockIncrement(1);
        ageRangeSlider.setMinorTickCount(0);
        ageRangeSlider.setMajorTickUnit(1);
        ageRangeSlider.setSnapToTicks(true);
        ageRangeSlider.setValue(7);
    }

    private void instantiateGamePlayedChoiceBox(){

        gamePlayed.setItems(FXCollections.observableArrayList("League Of Legends", "FORTNITE",
                "PUBG", "Counter-Strike: Global Offensive",
                "Rust", "DayZ",
                "Rainbow SIX: Siege", "Escape From Tarkov"));
    }

    private void instantiateGroupMainLanguageChoiceBox(){

        languageSpoken.setItems(FXCollections.observableArrayList("English", "Irish",
                "French", "German",
                "Norwegian", "Dutch",
                "Russian"));
    }
}

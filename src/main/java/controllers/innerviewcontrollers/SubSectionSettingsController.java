package controllers.innerviewcontrollers;

import functionality.MatchmakerPersistenceService;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Slider;
import javafx.scene.text.Text;
import javafx.util.Duration;
import models.MatchmakerRequirement;
import models.Session;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.net.URL;
import java.util.ResourceBundle;

public class SubSectionSettingsController implements Initializable {

    private boolean hasRequirement = false;
    private ClassPathXmlApplicationContext context;
    private MatchmakerPersistenceService mmPersistenceService;
    private MatchmakerRequirement mmRequirement;

    @FXML private Text noRequirementsFound;
    @FXML private Text saved;
    @FXML private Text failed;
    @FXML private Text requirementCreationFailure;
    @FXML private CheckBox atpCb;
    @FXML private CheckBox activeMatchmakingCb;
    @FXML private CheckBox micRequiredCb;
    @FXML private CheckBox acceptMalesCb;
    @FXML private CheckBox acceptFemalesCb;
    @FXML private CheckBox competitivePlayersCb;
    @FXML private CheckBox casualPlayersCb;
    @FXML private ChoiceBox<String> languageSpoken;
    @FXML private ChoiceBox<String> gamePlayed;
    @FXML private Slider ageRangeSlider;
    @FXML private Button saveRequirementToDb;

    @Override
    public void initialize(URL url, ResourceBundle rb){

        setSliders();
        initResources();

        mmPersistenceService.fetchAllRequirementObjects();
        mmRequirement = mmPersistenceService.checkDoesUserHaveRequirement(Session.gamerSession.getId());

        instantiateGamePlayedChoiceBox();
        instantiateGroupMainLanguageChoiceBox();

        if(mmRequirement != null){

            initFields();
        }
        if(mmRequirement == null){

            noRequirementsFound.setVisible(true);
        }

        closeResources();

    }

    @FXML
    private void saveRequirement(){

        if(mmRequirement == null){

            mmRequirement = new MatchmakerRequirement();

            mmRequirement.setAvailableToPlayFlag(atpCb.isSelected());
            mmRequirement.setMatchmakerActiviationFlag(activeMatchmakingCb.isSelected());
            mmRequirement.setDoesGamerRequireMicrophone(micRequiredCb.isSelected());
            mmRequirement.setDoesGamerRequireMales(acceptMalesCb.isSelected());
            mmRequirement.setDoesGamerRequireFemales(acceptFemalesCb.isSelected());
            mmRequirement.setDoesGamerRequireCompetitiveGamers(competitivePlayersCb.isSelected());
            mmRequirement.setDoesGamerRequireCasualPlayers(casualPlayersCb.isSelected());
            mmRequirement.setRequiredLanguage(languageSpoken.getValue());
            mmRequirement.setRequiredGame(gamePlayed.getValue());
            mmRequirement.setGamersMinimumAgeRequirement(sliderValue(ageRangeSlider.getValue()));
            mmRequirement.setGamer(Session.gamerSession);

            if(trueCount(mmRequirement) >= 3){

                mmPersistenceService.persistNewRequirement(mmRequirement);

                saved.setVisible(true);
                saveRequirementToDb.setDisable(true);
                fadeText(noRequirementsFound);

            }else {

                mmRequirement = null;

                requirementCreationFailure.setVisible(true);
                fadeText(requirementCreationFailure);
            }

        }else {

            if(hasRequirementChanged()){

                mmPersistenceService.updateUsersMatchmakerRequirement(mmPersistenceService.fetchRequirementForUpdate(mmRequirement.getMatchmakerRequirementId()), atpCb.isSelected(),
                        activeMatchmakingCb.isSelected(), micRequiredCb.isSelected(),
                        acceptMalesCb.isSelected(), acceptFemalesCb.isSelected(),
                        competitivePlayersCb.isSelected(), casualPlayersCb.isSelected(),
                        languageSpoken.getValue(),gamePlayed.getValue(), sliderValue(ageRangeSlider.getValue()));

                saved.setVisible(true);
                saveRequirementToDb.setDisable(true);

            }else {

                failed.setVisible(true);
                fadeText(failed);
            }
        }
    }

    private int trueCount(MatchmakerRequirement mmRequirement){

        int count = 0;

        if(mmRequirement.isAvailableToPlayFlag()){
            count++;
        }
        if(mmRequirement.isMatchmakerActiviationFlag()){
            count++;
        }
        if(mmRequirement.isDoesGamerRequireCompetitiveGamers()){
            count++;
        }
        if(mmRequirement.isDoesGamerRequireCasualPlayers()){
            count++;
        }
        if(mmRequirement.isDoesGamerRequireFemales()){
            count++;
        }
        if(mmRequirement.isDoesGamerRequireMales()){
            count++;
        }
        if(mmRequirement.isDoesGamerRequireMicrophone()){
            count++;
        }

        return count;
    }

    private void fadeText(Text toFade){

        FadeTransition ft = new FadeTransition(Duration.millis(5000), toFade);
        ft.setFromValue(1.0);
        ft.setToValue(0.0);

        ft.play();

    }

    private boolean hasRequirementChanged(){

        if(mmRequirement.isAvailableToPlayFlag() == atpCb.isSelected()
                && mmRequirement.isMatchmakerActiviationFlag() == activeMatchmakingCb.isSelected()
                && mmRequirement.isDoesGamerRequireMicrophone() == micRequiredCb.isSelected()
                && mmRequirement.isDoesGamerRequireCompetitiveGamers() == competitivePlayersCb.isSelected()
                && mmRequirement.isDoesGamerRequireCasualPlayers() == casualPlayersCb.isSelected()
                && mmRequirement.isDoesGamerRequireFemales() == acceptFemalesCb.isSelected()
                && mmRequirement.isDoesGamerRequireMales() == acceptMalesCb.isSelected()
                && mmRequirement.getGamersMinimumAgeRequirement() == ageRangeSlider.getValue()
                && mmRequirement.getRequiredLanguage().equals(languageSpoken.getValue())
                && mmRequirement.getRequiredGame().equals(gamePlayed.getValue())){

            return false;
        }
        return true;
    }

    private int sliderValue(double value){

        if(value == 1.0){

            return 1;
        }
        if(value == 2.0){

            return 2;
        }
        if(value == 3.0){

            return 3;
        }
        if(value == 4.0){

            return 4;
        }
        if(value == 5.0){

            return 5;
        }
        if(value == 6.0){

            return 6;
        }
        if(value == 7.0){

            return 7;
        }

        return 0;
    }

    private void initFields(){

        atpCb.setSelected(mmRequirement.isAvailableToPlayFlag());
        activeMatchmakingCb.setSelected(mmRequirement.isMatchmakerActiviationFlag());
        micRequiredCb.setSelected(mmRequirement.isDoesGamerRequireMicrophone());
        acceptMalesCb.setSelected(mmRequirement.isDoesGamerRequireMales());
        acceptFemalesCb.setSelected(mmRequirement.isDoesGamerRequireFemales());
        competitivePlayersCb.setSelected(mmRequirement.isDoesGamerRequireCompetitiveGamers());
        casualPlayersCb.setSelected(mmRequirement.isDoesGamerRequireCasualPlayers());

        double value = 0.0;
        switch (mmRequirement.getGamersMinimumAgeRequirement()){
            case 1:
                value = 1.0;
                break;
            case 2:
                value = 2.0;
                break;
            case 3:
                value = 3.0;
                break;
            case 4:
                value = 4.0;
                break;
            case 5:
                value = 5.0;
                break;
            case 6:
                value = 6.0;
                break;
            case 7:
                value = 7.0;
                break;
        }
        ageRangeSlider.setValue(value);
    }

    private void initResources(){

        context = new ClassPathXmlApplicationContext("ApplicationContext.xml");
        mmPersistenceService = (MatchmakerPersistenceService) context.getBean("matchmakerpersistenceservice");
        mmRequirement = (MatchmakerRequirement) context.getBean("matchmakerrequirement");

    }

    private void closeResources(){

        context.close();
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

        if(mmRequirement == null){
            gamePlayed.getSelectionModel().selectFirst();
        }else{
            gamePlayed.getSelectionModel().select(mmRequirement.getRequiredGame());
        }

    }

    private void instantiateGroupMainLanguageChoiceBox(){

        languageSpoken.setItems(FXCollections.observableArrayList("English", "Irish",
                "French", "German",
                "Norwegian", "Dutch",
                "Russian"));

        if(mmRequirement == null){
            languageSpoken.getSelectionModel().selectFirst();
        }else {

        }languageSpoken.getSelectionModel().select(mmRequirement.getRequiredLanguage());
    }
}

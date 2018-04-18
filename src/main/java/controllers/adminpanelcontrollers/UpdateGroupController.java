package controllers.adminpanelcontrollers;

import functionality.DatabaseInteractionService;
import functionality.Validator;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.util.Duration;
import models.Session;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.net.URL;
import java.util.ResourceBundle;

public class UpdateGroupController implements Initializable {

    private ClassPathXmlApplicationContext context;
    private DatabaseInteractionService dbService;
    private Validator validator;

    @FXML private TextField groupName;
    @FXML private TextField comsAddress;
    @FXML private ChoiceBox<String> groupVisability;
    @FXML private ChoiceBox<String> gamePlayed;
    @FXML private ChoiceBox<String> comsChannel;
    @FXML private ChoiceBox<String> activityLevel;
    @FXML private ChoiceBox<String> languageSpoken;
    @FXML private TextArea groupDescription;
    @FXML private Button updateGroup;
    @FXML private Text updateFailure;
    @FXML private Text updateSuccess;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        initChoices();
        initTextFields();
    }

    @FXML
    private void updateGroupFunctionality(){

        initResources();

        if(checkIfGroupInformationHasChanged()){

            if(validator.validateTextFieldNotEmpty(groupName.getText())
                    && validator.validateTextFieldNotEmpty(groupDescription.getText())){

                Session.adminGroup = dbService.updateGroup(dbService.fetchGamerGroupForUpdate(), groupName.getText(),
                        groupDescription.getText(), groupVisability.getValue(),
                        gamePlayed.getValue(), comsChannel.getValue(),
                        activityLevel.getValue(), languageSpoken.getValue(),
                        comsAddress.getText());

                updateGroup.setDisable(true);
                updateSuccess.setVisible(true);

                closeResources();
            }

        }else {

            updateFailure.setVisible(true);
            fadeFailureText(updateFailure);
            closeResources();
        }
    }

    private void fadeFailureText(Text toFade){

        FadeTransition ft = new FadeTransition(Duration.millis(5000), toFade);
        ft.setFromValue(1.0);
        ft.setToValue(0.0);

        ft.play();
    }

    private boolean checkIfGroupInformationHasChanged(){

        if(groupName.getText().equals(Session.adminGroup.getGroupName())
                && groupDescription.getText().equals(Session.adminGroup.getGroupDescription())
                && comsAddress.getText().equals(Session.adminGroup.getGroupComsAddress())
                && groupVisability.getValue().equals(Session.adminGroup.getGroupVisability())
                && gamePlayed.getValue().equals(Session.adminGroup.getMainGame())
                && comsChannel.getValue().equals(Session.adminGroup.getComsChannel())
                && activityLevel.getValue().equals(Session.adminGroup.getActivityLevel())
                && languageSpoken.getValue().equals(Session.adminGroup.getGroupLanguageSpoken())){

            return false;
        }
        else {

            return true;
        }
    }

    private void initTextFields(){

        groupName.setText(Session.adminGroup.getGroupName());
        comsAddress.setText(Session.adminGroup.getGroupComsAddress());
        groupDescription.setText(Session.adminGroup.getGroupDescription());
    }

    private void initChoices(){
        instantiateGroupVisabilityChoiceBox();
        instantiateGroupGamePlayedChoiceBox();
        instantiateGroupComsChannelChoiceBox();
        instantiateGroupActivityLevelChoiceBox();
        instantiateGroupMainLanguageChoiceBox();

        groupVisability.getSelectionModel().select(Session.adminGroup.getGroupVisability());
        gamePlayed.getSelectionModel().select(Session.adminGroup.getMainGame());
        comsChannel.getSelectionModel().select(Session.adminGroup.getComsChannel());
        activityLevel.getSelectionModel().select(Session.adminGroup.getActivityLevel());
        languageSpoken.getSelectionModel().select(Session.adminGroup.getGroupLanguageSpoken());
    }

    private void instantiateGroupVisabilityChoiceBox(){

        groupVisability.setItems(FXCollections.observableArrayList("Public", "Private"
        ));
    }

    private void instantiateGroupGamePlayedChoiceBox(){

        gamePlayed.setItems(FXCollections.observableArrayList("League Of Legends", "FORTNITE",
                "PUBG", "Counter-Strike: Global Offensive",
                "Rust", "DayZ",
                "Rainbow SIX: Siege", "Escape From Tarkov"));

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

    private void initResources(){

        context = new ClassPathXmlApplicationContext("ApplicationContext.xml");
        dbService = (DatabaseInteractionService) context.getBean("databaseinteractionservice");
        validator = (Validator) context.getBean("validator");
    }

    private void closeResources(){

        context.close();
    }
}

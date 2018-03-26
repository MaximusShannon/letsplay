package controllers.subsection.controllers;

import functionality.PostFunctionalityService;
import functionality.Validator;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.util.Duration;
import models.Post;
import org.hibernate.annotations.Check;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SubSectionCreatePostController implements Initializable {

    private Validator validator;
    private PostFunctionalityService postCreationService;
    private ClassPathXmlApplicationContext context;
    private Post post;

    @FXML
    private Text postCreationFailed;
    @FXML
    private TextField postTitle;
    @FXML
    private TextField ageRange;
    @FXML
    private TextArea postDescription;
    @FXML
    private TextField postTags;
    @FXML
    private CheckBox micRequired;
    @FXML
    private CheckBox competitivePlayersAccepted;
    @FXML
    private CheckBox casualPlayersAccepted;
    @FXML
    private CheckBox acceptFemales;
    @FXML
    private CheckBox acceptMales;
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

    /*
    * Only the post title, description, age range and at least one requirement is required.
    * */
    @FXML
    private void preparePostForCreation(){

        validator = new Validator();

        if(validator.validateTextFieldNotEmpty(postTitle.getText())
                && validator.validateTextFieldNotEmpty(postDescription.getText())
                && validator.validateTextFieldNotEmpty(ageRange.getText())){

            /*
            * If it's validated
            * - init context
            * - Build the post object
            * - persist the post
            * - Bring to a notification view.
            * - close the context
            * */


        }else {
            fadeFailureText();
        }
    }

    private void initContext(){

        context = new ClassPathXmlApplicationContext("ApplicationContext.xml");
    }

    private void closeContext(){

        context.close();
    }

    private void buildThePostBean(){

        post = (Post) context.getBean("post");

    }

    private void fadeFailureText(){

        postCreationFailed.setVisible(true);

        FadeTransition fadeTransition = new FadeTransition(Duration.millis(5000), postCreationFailed);
        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0.0);

        fadeTransition.play();
    }

}

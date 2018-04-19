package controllers.innerviewcontrollers;

import functionality.DatabaseInteractionService;
import functionality.PostFunctionalityService;
import functionality.Validator;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.util.Duration;
import models.Post;
import models.Session;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SubSectionCreatePostController implements Initializable {

    private Validator validator;
    private PostFunctionalityService postCreationService;
    private DatabaseInteractionService dbService;
    private ClassPathXmlApplicationContext context;
    private Post post;
    private Integer idReturned;

    @FXML private AnchorPane settingsView;
    @FXML private Text postCreationFailed;
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
        dbService = new DatabaseInteractionService();

        if(validator.validateTextFieldNotEmpty(postTitle.getText())
                && validator.validateTextFieldNotEmpty(postDescription.getText())
                && validator.validateTextFieldNotEmpty(ageRange.getText())){

            initContext();
            buildThePostBean();
            idReturned = postCreationService.persistNewPost(post);

            if(idReturned > 0){

                closeContext();
                postCreationService.closeFactory();
                dbService.updateGamerPostCount(dbService.fetchUserForUpdate());

                loadSuccessView();
            }else {
                fadeFailureText();
            }

        }else {
            fadeFailureText();
        }
    }

    private void loadSuccessView(){

        try{

            AnchorPane postCreation_sucess = FXMLLoader.load(getClass().getResource("/view/feedbackviews/postcreation_success.fxml"));
            settingsView.getChildren().setAll(postCreation_sucess);
        }catch (IOException e){

            e.printStackTrace();
        }

    }

    private void initContext(){

        context = new ClassPathXmlApplicationContext("ApplicationContext.xml");
    }

    private void closeContext(){

        context.close();
    }

    private void buildThePostBean(){

        /*instantiate beans*/
        post = (Post) context.getBean("post");
        postCreationService = (PostFunctionalityService) context.getBean("postfunctionalityservice");

        post.setPostTitle(postTitle.getText());
        post.setAgeRange(ageRange.getText());
        post.setPostDescription(postDescription.getText());
        post.setPostTags(postTags.getText());
        post.setLanguageSpoken(languageSpoken.getValue());
        post.setGamePlayed(gamePlayed.getValue());
        post.setTimeZone(timeZone.getValue());
        /*Requirements*/
        post.setMicrophoneRequired(micRequired.isSelected());
        post.setCompetitivePlayers(competitivePlayersAccepted.isSelected());
        post.setAcceptFemales(acceptFemales.isSelected());
        post.setAcceptMales(acceptMales.isSelected());
        post.setCasualPlayers(casualPlayersAccepted.isSelected());

        /*
        * Set the oneToOne relationship between post and gamer
        * one post has one gamer (that created it)
        * */
        post.setGamer(Session.gamerSession);
    }

    private void fadeFailureText(){

        postCreationFailed.setVisible(true);

        FadeTransition fadeTransition = new FadeTransition(Duration.millis(5000), postCreationFailed);
        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0.0);

        fadeTransition.play();
    }

}

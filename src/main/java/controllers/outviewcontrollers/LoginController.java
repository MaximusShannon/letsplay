package controllers.outviewcontrollers;

import functionality.Authentication;
import functionality.DatabaseInteractionService;
import functionality.Validator;
import javafx.animation.FadeTransition;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import models.Gamer;
import models.Session;
import org.apache.log4j.Logger;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    private static final Logger logger = Logger.getLogger(LoginController.class);

    private Validator validator;
    private Authentication authenticator;
    private Gamer gamer;
    private Gamer authenticatedGamer;
    private DatabaseInteractionService dbService;

    @FXML private AnchorPane loginStage;
    @FXML private TextField userNameField;
    @FXML private PasswordField passwordField;
    @FXML private Text loginFailureText;
    @FXML private Text serverDownMessage;

    @FXML
    private void loadRegisterView(){

        if(logger.isDebugEnabled()){
            logger.debug("private void loadRegisterView() called");
        }

        try{

            Stage registerStage = new Stage();

            Parent root = FXMLLoader.load(getClass().getResource("/view/frameviews/registerview.fxml"));
            registerStage.setTitle("Register here");
            registerStage.setScene(new Scene(root, 900, 500));
            registerStage.show();

            closeStage();

        }catch (Exception e){

            e.printStackTrace();
        }
    }

    @FXML
    private void handleLoginRequest(){

        try{

            validator = new Validator();
            authenticator = new Authentication();

            if(validator.validateLogin(userNameField.getText(), passwordField.getText())){


                if(authenticator.checkDoesUsernameExist(userNameField.getText())){

                    gamer = authenticator.fetchExistingGamer(userNameField.getText());

                    if(authenticator.checkPasswordsMatch(passwordField.getText(), gamer.getPassword())){

                        Session.gamerSession = gamer;
                        dbService.setGamerOnline(dbService.fetchUserForUpdate());
                        loadHomeView();

                    }else{
                        showFailedLoginAttemptMessage();
                    }

                }else{
                    showFailedLoginAttemptMessage();
                }
            }

        }catch (NullPointerException e){

            e.printStackTrace();
            displayServerDownMessage();
            logger.error("This is a null-pointer-error");

        }
    }

    private void displayServerDownMessage(){

        serverDownMessage.setVisible(true);
    }

    private void closeStage(){

        Stage stage = (Stage) loginStage.getScene().getWindow();
        stage.close();
    }

    private void loadHomeView(){

        try{
            Stage homeViewStage = new Stage();

            Parent root = FXMLLoader.load(getClass().getResource("/view/frameviews/mainview.fxml"));
            homeViewStage.setTitle("Welcome, " + Session.gamerSession.getUserName());
            homeViewStage.getIcons().add(new Image("/images/letsplay_icon.png"));
            homeViewStage.setScene(new Scene(root, 1200, 900));
            homeViewStage.setResizable(false);
            homeViewStage.sizeToScene();
            homeViewStage.show();

            Session.thisStage = homeViewStage;

            homeViewStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {

                    dbService.initFactory();
                    dbService.setGamerOffline(dbService.fetchUserForUpdate());
                    System.exit(0);
                }
            });

            closeStage();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void showFailedLoginAttemptMessage(){

        loginFailureText.setVisible(true);
        fadeFailureText(loginFailureText);
    }

    private void fadeFailureText(Text textToFade){

        FadeTransition ft = new FadeTransition(Duration.millis(5000), textToFade);
        ft.setFromValue(1.0);
        ft.setToValue(0.0);

        ft.play();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        dbService = new DatabaseInteractionService();
    }
}

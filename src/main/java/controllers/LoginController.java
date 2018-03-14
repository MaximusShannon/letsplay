package controllers;

import functionality.Authentication;
import functionality.DatabaseInteractionService;
import functionality.Validator;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import models.Gamer;
import models.Session;

public class LoginController {

    private Validator validator;
    private Authentication authenticator;
    private Gamer gamer;

    @FXML
    private AnchorPane loginStage;
    @FXML
    private TextField userNameField;
    @FXML
    private PasswordField passwordField;

    @FXML
    private void loadRegisterView(){

        try{

            Stage registerStage = new Stage();

            Parent root = FXMLLoader.load(getClass().getResource("/view/registerview.fxml"));
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

        validator = new Validator();
        authenticator = new Authentication();

        if(validator.validateLogin(userNameField.getText(), passwordField.getText())){

            if(authenticator.checkDoesUsernameExist(userNameField.getText())){

                gamer = authenticator.fetchExistingGamer(userNameField.getText());

                if(authenticator.checkPasswordsMatch(passwordField.getText(), gamer.getPassword())){

                    Session.gamerSession = gamer;
                    loadHomeView();


                    /**
                     * Insta session
                     * loadView
                     * Close Login
                     * Close factory;
                     */
                }else{
                    /**
                     * Display messages to user if passwords dont match
                     */
                }

            }else{
                /**
                 * Display messages to user if username doesn't exits
                 */

            }
        }
    }

    private void closeStage(){

        Stage stage =(Stage) loginStage.getScene().getWindow();
        stage.close();
    }

    private void loadHomeView(){

        try{
            Stage homeViewStage = new Stage();

            Parent root = FXMLLoader.load(getClass().getResource("/view/mainview.fxml"));
            homeViewStage.setTitle("Weclome, " + Session.gamerSession.getUserName());
            homeViewStage.setScene(new Scene(root, 1200, 900));
            homeViewStage.show();

            closeStage();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

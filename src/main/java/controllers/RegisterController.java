package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class RegisterController {

    @FXML
    private AnchorPane registerStage;
    @FXML
    private TextField firstNameText;
    @FXML
    private TextField secondNameText;
    @FXML
    private TextField emailText;
    @FXML
    private TextField userNameText;
    @FXML
    private PasswordField passwordOne;
    @FXML
    private PasswordField passwordTwo;

    @FXML
    private void loadLoginView(){

        try{

            Stage registerStage = new Stage();

            Parent root = FXMLLoader.load(getClass().getResource("/view/login.fxml"));
            registerStage.setTitle("Login");
            registerStage.setScene(new Scene(root, 900, 500));
            registerStage.show();

            closeStage();

        }catch (Exception e){

            e.printStackTrace();
        }
    }

    private void closeStage(){
        Stage stage =(Stage) registerStage.getScene().getWindow();
        stage.close();
    }

}

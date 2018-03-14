package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import models.Session;

import java.awt.*;

public class LogoutConfirmationBoxController {

    @FXML
    private AnchorPane confirmBoxStage;

    @FXML
    private void logout(){

        Session.resetSession();
        closeThisStage();
        closeMainView();
        loadLoginView();
    }

    @FXML
    private void cancelLogout(){

        closeThisStage();
    }

    private void closeThisStage(){

        Stage stage = (Stage) confirmBoxStage.getScene().getWindow();
        stage.close();

    }

    private void closeMainView(){

        MainViewController.mainStage.getWindow().hide();
    }

    private void loadLoginView(){

        try{

            Stage registerStage = new Stage();

            Parent root = FXMLLoader.load(getClass().getResource("/view/login.fxml"));
            registerStage.setTitle("Login");
            registerStage.setScene(new Scene(root, 900, 500));
            registerStage.show();


        }catch (Exception e){

            e.printStackTrace();
        }
    }
}

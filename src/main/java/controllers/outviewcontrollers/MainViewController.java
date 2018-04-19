package controllers.outviewcontrollers;

import functionality.DatabaseInteractionService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import models.Session;


import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class MainViewController implements Initializable {

    private DatabaseInteractionService dbService;

    @FXML private AnchorPane mainViewPane;
    @FXML private AnchorPane fillerPane;
    @FXML private Text userNameText;
    @FXML public  Text navbarText;

    @Override
    public void initialize(URL url, ResourceBundle rb){

        setUserNameText();

    }

    private void setUserNameText(){

        userNameText.setText(Session.gamerSession.getUserName());
    }

    @FXML
    private void handleLogoutRequest(){

        Alert logoutAlert = new Alert(Alert.AlertType.CONFIRMATION);
        logoutAlert.setTitle("Logout Confirmation");
        logoutAlert.setHeaderText("Are you sure you want to logout?");
        logoutAlert.setContentText("Please choose. . .");

        Stage logoutAlertStage = (Stage) logoutAlert.getDialogPane().getScene().getWindow();
        logoutAlertStage.getIcons().add(new Image("/images/letsplay_icon.png"));

        Optional<ButtonType> result = logoutAlert.showAndWait();

        if(result.get() == ButtonType.OK){

            dbService = new DatabaseInteractionService();
            dbService.setGamerOffline(dbService.fetchUserForUpdate());

            Session.resetSession();
            closeStage();
            loadLoginView();
        }
        else {

            logoutAlert.close();
        }

    }
    
    private void loadLoginView(){

        try{

            Stage loginStage = new Stage();

            Parent root = FXMLLoader.load(getClass().getResource("/view/frameviews/login.fxml"));
            loginStage.setTitle("Login");
            loginStage.setScene(new Scene(root, 900, 500));
            loginStage.getIcons().add(new Image("/images/letsplay_icon.png"));
            loginStage.setResizable(false);
            loginStage.sizeToScene();
            loginStage.show();

        }catch (Exception e){

            e.printStackTrace();
        }
    }

    private void closeStage(){

        Stage stage = (Stage) mainViewPane.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void loadHomePane() throws IOException {

        AnchorPane homePane = FXMLLoader.load(getClass().getResource("/view/innerviews/homeview.fxml"));
        fillerPane.getChildren().setAll(homePane);
        navbarText.setText("Home");
    }

    @FXML
    private void loadGamersSearchingLanding() throws IOException{

        AnchorPane gamersSearching = FXMLLoader.load(getClass().getResource("/view/innerviews/players_searching_landing.fxml"));
        fillerPane.getChildren().setAll(gamersSearching);
        navbarText.setText("Search. . .");

    }
    @FXML
    private void loadProfilePane(ActionEvent event) throws IOException{

        AnchorPane profilePane = FXMLLoader.load(getClass().getResource("/view/innerviews/profile.fxml"));
        fillerPane.getChildren().setAll(profilePane);
        navbarText.setText("Profile");
    }

    @FXML
    private void loadCreatePostPane(ActionEvent event) throws IOException{

        AnchorPane createPostPane = FXMLLoader.load(getClass().getResource("/view/innerviews/createpostview.fxml"));
        fillerPane.getChildren().setAll(createPostPane);
        navbarText.setText("Create a tailored post");

    }

    @FXML
    private void loadHelpPane(ActionEvent event) throws IOException{

        AnchorPane helpPane = FXMLLoader.load(getClass().getResource("/view/innerviews/helpview.fxml"));
        fillerPane.getChildren().setAll(helpPane);
        navbarText.setText("Help");

    }

    @FXML
    private void loadSettingsPane(ActionEvent event) throws IOException{
        AnchorPane settingsPane = FXMLLoader.load(getClass().getResource("/view/innerviews/settingsview.fxml"));
        fillerPane.getChildren().setAll(settingsPane);
        navbarText.setText("Settings");
    }

    @FXML
    private void loadGroupsPane(ActionEvent event) throws IOException{
        AnchorPane groupPane = FXMLLoader.load(getClass().getResource("/view/innerviews/groupsmainview.fxml"));
        fillerPane.getChildren().setAll(groupPane);
        navbarText.setText("Groups");
    }

}
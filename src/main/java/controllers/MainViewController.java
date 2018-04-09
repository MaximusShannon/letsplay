package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import models.Session;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainViewController implements Initializable {

    @FXML
    private AnchorPane mainViewPane;
    @FXML
    private AnchorPane fillerPane;
    @FXML
    private ScrollPane scrollPaneMatchedGamers;
    @FXML
    private Button homePane;
    @FXML
    private Button profileButton;
    @FXML
    private Button createPostButton;
    @FXML
    private Button helpButton;
    @FXML
    private Button settingsButton;
    @FXML
    private Button openGroupsBtn;
    @FXML
    private Button logoutButton;
    @FXML
    private Text userNameText;
    @FXML
    public  Text navbarText;


    @Override
    public void initialize(URL url, ResourceBundle rb){

        setUserNameText();

    }

    private void setUserNameText(){

        userNameText.setText(Session.gamerSession.getUserName());
    }

    @FXML
    private void handleLogoutRequest(){
        
        Session.resetSession();
        closeStage();
        loadLoginView();
    }
    
    private void loadLoginView(){

        try{

            Stage loginStage = new Stage();

            Parent root = FXMLLoader.load(getClass().getResource("/view/login.fxml"));
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

        AnchorPane homePane = FXMLLoader.load(getClass().getResource("/view/homeview.fxml"));
        fillerPane.getChildren().setAll(homePane);
        navbarText.setText("Home");
    }

    @FXML
    private void loadGamersSearchingLanding() throws IOException{

        AnchorPane gamersSearching = FXMLLoader.load(getClass().getResource("/view/players_searching_landing.fxml"));
        fillerPane.getChildren().setAll(gamersSearching);
        navbarText.setText("Search. . .");

    }
    @FXML
    private void loadProfilePane(ActionEvent event) throws IOException{

        AnchorPane profilePane = FXMLLoader.load(getClass().getResource("/view/profile.fxml"));
        fillerPane.getChildren().setAll(profilePane);
        navbarText.setText("Profile");
    }

    @FXML
    private void loadCreatePostPane(ActionEvent event) throws IOException{

        AnchorPane createPostPane = FXMLLoader.load(getClass().getResource("/view/createpostview.fxml"));
        fillerPane.getChildren().setAll(createPostPane);
        navbarText.setText("Create a tailored post");

    }

    @FXML
    private void loadHelpPane(ActionEvent event) throws IOException{

        AnchorPane helpPane = FXMLLoader.load(getClass().getResource("/view/helpview.fxml"));
        fillerPane.getChildren().setAll(helpPane);
        navbarText.setText("Help");

    }

    @FXML
    private void loadSettingsPane(ActionEvent event) throws IOException{
        AnchorPane settingsPane = FXMLLoader.load(getClass().getResource("/view/settingsview.fxml"));
        fillerPane.getChildren().setAll(settingsPane);
        navbarText.setText("Settings");
    }

    @FXML
    private void loadGroupsPane(ActionEvent event) throws IOException{
        AnchorPane groupPane = FXMLLoader.load(getClass().getResource("/view/groupsmainview.fxml"));
        fillerPane.getChildren().setAll(groupPane);
        navbarText.setText("Groups");
    }

}
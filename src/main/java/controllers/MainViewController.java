package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainViewController implements Initializable {

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


    @Override
    public void initialize(URL url, ResourceBundle rb){


    }

    @FXML
    private void loadHomePane() throws IOException {

        AnchorPane homePane = FXMLLoader.load(getClass().getResource("/view/homeview.fxml"));
        fillerPane.getChildren().setAll(homePane);
    }

    @FXML
    private void loadProfilePane(ActionEvent event) throws IOException{

        AnchorPane profilePane = FXMLLoader.load(getClass().getResource("/view/profileview.fxml"));
        fillerPane.getChildren().setAll(profilePane);

    }

    @FXML
    private void loadCreatePostPane(ActionEvent event) throws IOException{

        AnchorPane createPostPane = FXMLLoader.load(getClass().getResource("/view/createpostview.fxml"));
        fillerPane.getChildren().setAll(createPostPane);

    }

    @FXML
    private void loadHelpPane(ActionEvent event) throws IOException{

        AnchorPane helpPane = FXMLLoader.load(getClass().getResource("/view/helpview.fxml"));
        fillerPane.getChildren().setAll(helpPane);

    }

    @FXML
    private void loadSettingsPane(ActionEvent event) throws IOException{
        AnchorPane settingsPane = FXMLLoader.load(getClass().getResource("/view/settingsview.fxml"));
        fillerPane.getChildren().setAll(settingsPane);
    }

    @FXML
    private void loadGroupsPane(ActionEvent event) throws IOException{
        AnchorPane groupPane = FXMLLoader.load(getClass().getResource("/view/groupsmainview.fxml"));
        fillerPane.getChildren().setAll(groupPane);
    }

}
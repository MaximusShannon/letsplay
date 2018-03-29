package controllers;

import functionality.DatabaseInteractionService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import models.Post;

import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.ResourceBundle;

public class ProfileController implements Initializable {

    @FXML
    private AnchorPane injectablePane;
    @FXML
    private Pane profileBlueBar;
    @FXML
    private Pane postsBlueBar;
    @FXML
    private Pane groupsBlueBar;
    @FXML
    private Pane matchmakingBlueBar;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try{

            loadProfileInformationView();
        }catch (Exception e){

            e.printStackTrace();
        }
    }

    @FXML
    private void loadProfileInformationView() throws IOException{

        AnchorPane profileInfoPane = FXMLLoader.load(getClass().getResource("/view/profile_profileinfoview.fxml"));
        injectablePane.getChildren().setAll(profileInfoPane);

        hideBlueBars();
        profileBlueBar.setVisible(true);

    }

    @FXML
    private void loadUserPostsView() throws IOException{

        AnchorPane userPostsView = FXMLLoader.load(getClass().getResource("/view/profile_usersposts.fxml"));
        injectablePane.getChildren().setAll(userPostsView);

        hideBlueBars();
        postsBlueBar.setVisible(true);
    }

    @FXML
    private void loadUserGroupsView() throws IOException{

        AnchorPane userGroupsView = FXMLLoader.load(getClass().getResource("/view/profile_usergroups.fxml"));
        injectablePane.getChildren().setAll(userGroupsView);

        hideBlueBars();
        groupsBlueBar.setVisible(true);
    }

    @FXML
    private void loadUserMatchmakerSettingsView() throws IOException{

        AnchorPane userMatchmakerSettings = FXMLLoader.load(getClass().getResource("/view/profile_usermatchmaker_settings.fxml"));
        injectablePane.getChildren().setAll(userMatchmakerSettings);

        hideBlueBars();
        matchmakingBlueBar.setVisible(true);
    }

    private void hideBlueBars(){

        profileBlueBar.setVisible(false);
        postsBlueBar.setVisible(false);
        groupsBlueBar.setVisible(false);
        matchmakingBlueBar.setVisible(false);
    }


}

package controllers.adminpanelcontrollers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import models.Session;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainAdminPanelController implements Initializable {

    @FXML private Button invitePlayers;
    @FXML private Button removePlayers;
    @FXML private Button messaging;
    @FXML private Button applications;
    @FXML private Button updateGroup;
    @FXML private Button deleteGroup;
    @FXML private AnchorPane injectablePane;
    @FXML private Text groupName;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        groupName.setText("Welcome to, " + Session.adminGroup.getGroupName() + "'s Admin Panel");
    }

    @FXML
    private void loadInvitePlayersPanel() throws IOException{

        AnchorPane invitePlayers = FXMLLoader.load(
                getClass().getResource("/view/innerviews/inner_innerviews/group_admin_panel_inviteplayers.fxml"));
        injectablePane.getChildren().setAll(invitePlayers);
    }

    @FXML
    private void loadRemovePlayersPanel() throws IOException{

        AnchorPane removePlayers = FXMLLoader.load(
                getClass().getResource("/view/innerviews/inner_innerviews/group_admin_panel_removeplayers.fxml"));
       injectablePane.getChildren().setAll(removePlayers);
    }

    @FXML
    private void loadMessagingPanel() throws IOException{

        AnchorPane messaging = FXMLLoader.load(
                getClass().getResource("/view/innerviews/inner_innerviews/group_admin_panel_messaging.fxml"));
        injectablePane.getChildren().setAll(messaging);
    }

    @FXML
    private void loadApplicationsPanel() throws IOException{

        AnchorPane applicationPane = FXMLLoader.load(
                getClass().getResource("/view/innerviews/inner_innerviews/group_admin_panel_applications.fxml"));
        injectablePane.getChildren().setAll(applicationPane);
    }

    @FXML
    private void loadUpdateGroupPanel() throws IOException{

        AnchorPane updatePanel = FXMLLoader.load(
                getClass().getResource("/view/innerviews/inner_innerviews/group_admin_panel_updategroup.fxml"));
        injectablePane.getChildren().setAll(updatePanel);
    }

    @FXML
    private void loadDeleteGroupPanel() throws IOException{

        AnchorPane deleteGroup = FXMLLoader.load(
                getClass().getResource("/view/innerviews/inner_innerviews/group_admin_panel_deletegroup.fxml"));
        injectablePane.getChildren().setAll(deleteGroup);
    }



}

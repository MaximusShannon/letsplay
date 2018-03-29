package controllers;

import functionality.DatabaseInteractionService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import models.Post;

import java.net.URL;
import java.util.Collections;
import java.util.ResourceBundle;

public class ProfileController implements Initializable {

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

        //make other 3 bars invis
        //load the profile view as default
        postsBlueBar.setVisible(false);
        groupsBlueBar.setVisible(false);
        matchmakingBlueBar.setVisible(false);
    }


}

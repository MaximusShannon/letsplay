package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class GroupMainViewController {

    @FXML private AnchorPane injectablePane;
    @FXML private Button createGroupBtn;


    @FXML
    private void openCreateViewView() throws IOException{

        AnchorPane createGroupPane = FXMLLoader.load(getClass().getResource("/view/creategroupview.fxml"));
        injectablePane.getChildren().setAll(createGroupPane);
    }
}

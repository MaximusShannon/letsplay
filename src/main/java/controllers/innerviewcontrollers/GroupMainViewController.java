package controllers.innerviewcontrollers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GroupMainViewController implements Initializable {

    @FXML private AnchorPane injectablePane;
    @FXML private Button createGroupBtn;
    @FXML private VBox groupsVbox;

    @FXML
    private void openCreateViewView() throws IOException{

        AnchorPane createGroupPane = FXMLLoader.load(getClass().getResource("/view/innerviews/creategroupview.fxml"));
        injectablePane.getChildren().setAll(createGroupPane);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}

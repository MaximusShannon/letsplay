package controllers.subsection.controllers;

import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import sun.plugin.javascript.navig.Anchor;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SubSectionHomeController implements Initializable {

    @FXML
    private Text changeableText;
    @FXML
    private Button changeText;
    @FXML
    private HBox matchedGamersList;

    @Override
    public void initialize(URL url, ResourceBundle rb){

        try{
            addItemsToHbox();
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    @FXML
    private void changeText(){

        changeableText.setText("Here we are now.");
    }

    @FXML
    private void addItemsToHbox() throws IOException{

        Node gamerProfileView = FXMLLoader.load(getClass().getResource("/view/matched-gamer-card.fxml"));
        Node gamerProfileView2 = FXMLLoader.load(getClass().getResource("/view/matched-gamer-card.fxml"));
        Pane gamerProfileView3 = FXMLLoader.load(getClass().getResource("/view/matched-gamer-card.fxml"));
        matchedGamersList.getChildren().addAll(gamerProfileView, gamerProfileView2, gamerProfileView3);
    }


}

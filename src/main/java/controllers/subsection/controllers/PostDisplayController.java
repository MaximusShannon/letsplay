package controllers.subsection.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


import java.net.URL;
import java.util.ResourceBundle;

public class PostDisplayController implements Initializable {

    public static String game;

    @FXML
    private VBox postsVbox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        display2Posts();
        display2Posts();
        display2Posts();
        display2Posts();
        display2Posts();
        display2Posts();

        //display2Posts();
    }

     private void display2Posts(){

        try{
            AnchorPane node = FXMLLoader.load(getClass().getResource("/view/post_view.fxml"));
            postsVbox.getChildren().add(node);

        }catch (Exception e){

            e.printStackTrace();
        }

     }

}

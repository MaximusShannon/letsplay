package controllers.innerviewcontrollers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import models.Session;

import java.net.URL;
import java.util.ResourceBundle;


public class ProfileMatchedGamerController implements Initializable {

    @FXML private Text userName;
    @FXML private Text interests;
    @FXML private Text postCount;
    @FXML private Text commentCount;
    @FXML private Text applicationCount;
    @FXML private Text firstName;
    @FXML private Text surname;
    @FXML private Text email;
    @FXML private Text location;
    @FXML private Text bio;
    @FXML private ImageView profileImage;
    @FXML private Button backToHome;
    @FXML private AnchorPane injectablePane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        initView();
    }
    @FXML
    private void backToHome(){

        try{

            AnchorPane homePane = FXMLLoader.load(getClass().getResource("/view/innerviews/homeview.fxml"));
            injectablePane.getChildren().setAll(homePane);
        }catch (Exception e){

            e.printStackTrace();
        }

    }

    private void initView(){

        userName.setText(Session.gamersProfile.getUserName());
        interests.setText(Session.gamersProfile.getInterest());
        postCount.setText(Integer.toString(Session.gamersProfile.getPostCount()));
        commentCount.setText(Integer.toString(Session.gamersProfile.getCommentsCount()));
        applicationCount.setText(Integer.toString(Session.gamersProfile.getApplicationsCount()));

        firstName.setText(Session.gamersProfile.getFirstName());
        surname.setText(Session.gamersProfile.getSecondName());
        email.setText(Session.gamersProfile.getEmail());
        location.setText(Session.gamersProfile.getLocation());
        bio.setText(Session.gamersProfile.getBio());
        profileImage.setImage(Session.profileImage);
    }
}

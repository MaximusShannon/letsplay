package controllers.innerviewcontrollers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import models.Session;
import org.springframework.security.core.parameters.P;

import java.net.URL;
import java.util.ResourceBundle;

public class InnerPostViewController implements Initializable {

    @FXML private Text postersUsername;
    @FXML private Text postTitle;
    @FXML private Text postDescription;
    @FXML private Text postAgeRange;
    @FXML private Text postLangSpoken;
    @FXML private Text postGamePlayed;
    @FXML private Text postTimeZone;
    @FXML private Text postTags;
    @FXML private VBox commentsVbox;
    @FXML private ImageView mic_tick;
    @FXML private ImageView comp_tick;
    @FXML private ImageView casual_tick;
    @FXML private ImageView acceptFemales_tick;
    @FXML private ImageView acceptMales_tick;
    @FXML private ImageView mic_x;
    @FXML private ImageView comp_x;
    @FXML private ImageView casual_x;
    @FXML private ImageView acceptFemales_x;
    @FXML private ImageView acceptMales_x;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        initTexts();
        initRequirements();
    }

    private void initTexts(){

        postersUsername.setText(Session.innerViewPost.getGamer().getUserName());
        postTitle.setText(Session.innerViewPost.getPostTitle());
        postDescription.setText(Session.innerViewPost.getPostDescription());
        postAgeRange.setText(Session.innerViewPost.getAgeRange());
        postLangSpoken.setText(Session.innerViewPost.getLanguageSpoken());
        postGamePlayed.setText(Session.innerViewPost.getGamePlayed());
        postTimeZone.setText(Session.innerViewPost.getTimeZone());
        postTags.setText(Session.innerViewPost.getPostTags());
    }

    private void initRequirements(){

        if(Session.innerViewPost.isMicrophoneRequired()){

            mic_tick.setVisible(true);
        }else{

            mic_x.setVisible(true);
        }

        if(Session.innerViewPost.isAcceptingCompetitivePlayers()){

            comp_tick.setVisible(true);
        }else {

            comp_x.setVisible(true);
        }

        if(Session.innerViewPost.isAcceptingCasualPlayers()) {

            casual_tick.setVisible(true);
        }else{

            casual_x.setVisible(true);
        }

        if(Session.innerViewPost.isAcceptFemales()){

            acceptFemales_tick.setVisible(true);
        }else {

            acceptFemales_x.setVisible(true);
        }

        if(Session.innerViewPost.isAcceptMales()){

            acceptMales_tick.setVisible(true);
        }else {

            acceptMales_x.setVisible(true);
        }
    }
}

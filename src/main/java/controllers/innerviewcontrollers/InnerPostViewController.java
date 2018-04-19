package controllers.innerviewcontrollers;

import controllers.dynamicviewcontrollers.UniquePostCommentController;
import functionality.DatabaseInteractionService;
import functionality.Validator;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Duration;
import models.PostComment;
import models.Session;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.core.parameters.P;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class InnerPostViewController implements Initializable {

    private ClassPathXmlApplicationContext context;
    private DatabaseInteractionService dbService;
    private Validator validator;
    private PostComment comment;
    private List<PostComment> allComments;
    private List<PostComment> filteredComments;

    @FXML private Text postersUsername;
    @FXML private Text postTitle;
    @FXML private Text postDescription;
    @FXML private Text postAgeRange;
    @FXML private Text postLangSpoken;
    @FXML private Text postGamePlayed;
    @FXML private Text postTimeZone;
    @FXML private Text postTags;
    @FXML private Text commentPosted;
    @FXML private Text commentFailed;
    @FXML private TextField commentBox;
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
    @FXML private AnchorPane injectablePane;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        initTexts();
        initRequirements();
        getPostComments();

        if(filteredComments.size() > 0){

            for(int i = 0; i < filteredComments.size(); i++){

                try{

                    addCommentToVbox(filteredComments.get(i));
                }catch (Exception e){

                    e.printStackTrace();
                }
            }
        }
    }

    private void getPostComments(){

        initResources();

        allComments = dbService.fetchPostCommentList();
        filteredComments = new ArrayList<>();

        for(int i = 0; i < allComments.size(); i++){

            if(allComments.get(i).getPostId() == Session.innerViewPost.getId()){

                filteredComments.add(allComments.get(i));
            }
        }

        closeResources();
    }

    private void initTexts(){

        postersUsername.setText(Session.innerViewPost.getGamer().getUserName() + "'s Post");
        postTitle.setText(Session.innerViewPost.getPostTitle());
        postDescription.setText(Session.innerViewPost.getPostDescription());
        postAgeRange.setText(Session.innerViewPost.getAgeRange());
        postLangSpoken.setText(Session.innerViewPost.getLanguageSpoken());
        postGamePlayed.setText(Session.innerViewPost.getGamePlayed());
        postTimeZone.setText(Session.innerViewPost.getTimeZone());
        postTags.setText(Session.innerViewPost.getPostTags());
    }

    @FXML
    private void postComment(){

        initResources();

        if(validator.validateTextFieldNotEmpty(commentBox.getText())){

            comment.setCommentor(Session.gamerSession);
            comment.setCommentText(commentBox.getText());
            comment.setPostId(Session.innerViewPost.getId());

            int id = dbService.persistPostComment(comment);

            if(id > 0){

                commentPosted.setVisible(true);
                fadeText(commentPosted);
                dbService.updateGamerCommentCount(dbService.fetchUserForUpdate());

                try{

                    addCommentToVbox(comment);
                }catch (Exception e){

                    e.printStackTrace();
                }
            }else {

                commentFailed.setVisible(true);
                fadeText(commentFailed);
            }
        }else{

            commentFailed.setVisible(true);
            fadeText(commentFailed);
        }

        closeResources();
    }

    private void addCommentToVbox(PostComment comment) throws IOException{

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/dynamiclycreatedviews/post_comment.fxml"));
        AnchorPane node = loader.load();

        UniquePostCommentController uniquePostCommentController = loader.getController();

        uniquePostCommentController.commentorName.setText(comment.getCommentor().getUserName());
        uniquePostCommentController.commentText.setText(comment.getCommentText());

        if(comment.getCommentor().getId() == Session.gamerSession.getId()){

            uniquePostCommentController.deleteImage.setVisible(true);
            uniquePostCommentController.deleteText.setVisible(true);
            uniquePostCommentController.deleteCommentButton.setVisible(true);
            uniquePostCommentController.deleteCommentButton.setOnMouseClicked(e ->{


                if(dbService != null){

                    dbService.deleteComment(comment.getCommentId());
                    commentsVbox.getChildren().remove(node);
                }else {

                    initResources();

                    dbService.deleteComment(comment.getCommentId());
                    commentsVbox.getChildren().remove(node);

                    closeResources();
                }
            });
        }
        commentsVbox.getChildren().add(node);
    }

    private void fadeText(Text toFade){

        FadeTransition ft = new FadeTransition(Duration.millis(5000), toFade);
        ft.setFromValue(1.0);
        ft.setToValue(0.0);

        ft.play();
    }

    @FXML
    private void backToPostsButtonFunctionality(){

        try{

            AnchorPane postsView = FXMLLoader.load(getClass().getResource("/view/innerviews/postsdisplay.fxml"));
            injectablePane.getChildren().setAll(postsView);

        }catch (Exception e){

            e.printStackTrace();
        }
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

    private void initResources(){

        context = new ClassPathXmlApplicationContext("ApplicationContext.xml");
        dbService = (DatabaseInteractionService) context.getBean("databaseinteractionservice");
        validator = (Validator) context.getBean("validator");
        comment = (PostComment) context.getBean("postcomment");
    }

    private void closeResources(){

        context.close();
    }
}

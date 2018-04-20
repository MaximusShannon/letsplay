package controllers.innerviewcontrollers;

import controllers.dynamicviewcontrollers.UniquePostController;
import functionality.DatabaseInteractionService;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Duration;
import models.Filter;
import models.GamerAvatar;
import models.Post;
import models.Session;
import org.springframework.context.support.ClassPathXmlApplicationContext;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.net.URL;
import java.util.*;

public class PostDisplayController implements Initializable {

    private DatabaseInteractionService dbService;
    private ClassPathXmlApplicationContext context;
    private List<Post> postList;
    private List<GamerAvatar> avatars;
    private GamerAvatar avatar;
    public static String game;

    @FXML private VBox postsVbox;
    @FXML private Text postCount;
    @FXML private Text gameName;
    @FXML private AnchorPane injectablePane;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        gameName.setText("These are the posts for the game: " + game);

        dbService = new DatabaseInteractionService();
        avatars = dbService.fetchGamerAvatars();

        postList = dbService.fetchPostList();

        if(postList != null){

            /*Reverse the list so the new stuff is at the top.*/
            Collections.reverse(postList);

            displayPostCount();

            for (Post initalPost : postList) {

                displayUniquePostsInView(initalPost);
            }
        }

    }

    private void displayPostCount(){

        postCount.setText(Integer.toString(postList.size()));
    }

    private GamerAvatar checkDoesPosterHaveAnAvatar(int posterId){

        for(int i = 0; i < avatars.size(); i++ ){

            if(avatars.get(i).getGamer().getId() == posterId){

                return avatars.get(i);
            }
        }

        return null;
    }

    private Image convertToJavaFXImage(byte[] raw, final int width, final int height){

        WritableImage image = new WritableImage(width, height);

        try{

            ByteArrayInputStream bis = new ByteArrayInputStream(raw);
            BufferedImage read = ImageIO.read(bis);

            image = SwingFXUtils.toFXImage(read, null);

        }catch (Exception e){

            e.printStackTrace();

        }

        return image;
    }

    private void displayUniquePostsInView(Post post){

        try{

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/dynamiclycreatedviews/post_view.fxml"));
            AnchorPane node = loader.load();

            UniquePostController uniquePostController = loader.getController();

            uniquePostController.postTitle.setText(post.getPostTitle());
            uniquePostController.postDescription.setText(post.getPostDescription());
            uniquePostController.postTags.setText(post.getPostTags());
            uniquePostController.postLanguage.setText(post.getLanguageSpoken());

            uniquePostController.username.setText(post.getGamer().getUserName());
            uniquePostController.commentCount.setText(Integer.toString(post.getGamer().getCommentsCount()));
            uniquePostController.postCount.setText(Integer.toString(post.getGamer().getPostCount()));
            uniquePostController.applicationCount.setText(Integer.toString(post.getGamer().getApplicationsCount()));

            avatar = checkDoesPosterHaveAnAvatar(post.getGamer().getId());

            if(avatar != null){

                uniquePostController.userProfileImage.setImage(convertToJavaFXImage(avatar.getAvatarImage(), 126, 121));
            }

            if(post.getGamer().getPlayerOnlineStatus()){

                uniquePostController.isOnline_green.setVisible(true);
            }else {

                uniquePostController.isOnline_red.setVisible(true);
            }

            uniquePostController.viewPost.setOnMouseClicked(e ->{

                Session.resetInnerViewPost();
                Session.innerViewPost = post;

                try{
                    AnchorPane postView = FXMLLoader.load(getClass().getResource("/view/innerviews/inner_innerviews/post_view_inside_with_comments.fxml"));
                    injectablePane.getChildren().setAll(postView);

                }catch (Exception ei){

                    ei.printStackTrace();
                }
            });

            /*Requirements display*/
            if(post.isMicrophoneRequired()){
                uniquePostController.mr_tick.setVisible(true);
            }else{
                uniquePostController.mr_x.setVisible(true);
            }
            if(post.isAcceptingCasualPlayers()){
                uniquePostController.cp_tick.setVisible(true);
            }else{
                uniquePostController.cp_x.setVisible(true);
            }
            if(post.isAcceptingCompetitivePlayers()){
                uniquePostController.comp_tick.setVisible(true);
            }else{
                uniquePostController.comp_x.setVisible(true);
            }
            if(post.isAcceptFemales()){
                uniquePostController.acf_tick.setVisible(true);
            }else{
                uniquePostController.acf_x.setVisible(true);
            }
            if(post.isAcceptMales()){
                uniquePostController.acm_tick.setVisible(true);
            }else{
                uniquePostController.acm_x.setVisible(true);
            }

            postsVbox.getChildren().add(node);

        }catch (Exception e){

            e.printStackTrace();
        }
    }

}

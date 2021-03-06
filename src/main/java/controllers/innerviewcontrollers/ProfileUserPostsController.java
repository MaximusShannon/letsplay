package controllers.innerviewcontrollers;

import controllers.dynamicviewcontrollers.UniqueUsersPostController;
import functionality.DatabaseInteractionService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import models.Post;
import models.Session;

import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class ProfileUserPostsController implements Initializable {

    private DatabaseInteractionService dbService;
    private List<Post> usersPostList;

    @FXML private AnchorPane injectablePane;
    @FXML private VBox postsVbox;
    @FXML private Text postCount;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        getUserLoggedInPostList();
        reversePostList();
        displayAllPosts();
        setPostCountText();
    }

    /**
     * We reverse it so the newest posts can be displayed at the top.
     */
    private void reversePostList(){

        Collections.reverse(usersPostList);
    }

    private void getUserLoggedInPostList(){

        dbService = new DatabaseInteractionService();
        dbService.fetchPostList();
        usersPostList = dbService.filterPostsByUserId();
        //dbService.closeFactory();
    }

    private void displayAllPosts(){

        for(int i = 0; i < usersPostList.size(); i++){

            displayUsersPostInVbox(usersPostList.get(i));
        }
    }

    private void displayUsersPostInVbox(Post post){

        try{

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/innerviews/inner_innerviews/post_user_view.fxml"));
            AnchorPane node = loader.load();

            UniqueUsersPostController uniqueUsersPostController = loader.getController();
            uniqueUsersPostController.postTitle.setText(post.getPostTitle());
            uniqueUsersPostController.age.setText(post.getAgeRange());
            uniqueUsersPostController.language.setText(post.getLanguageSpoken());
            uniqueUsersPostController.timeZone.setText(post.getTimeZone());
            uniqueUsersPostController.tags.setText(post.getPostTags());
            uniqueUsersPostController.postId.setText(Integer.toString(post.getId()));

            uniqueUsersPostController.deletePostButton.setOnMouseClicked(e ->{

                Alert deleteAlert = new Alert(Alert.AlertType.CONFIRMATION);
                deleteAlert.setTitle("Confirmation Dialog");
                deleteAlert.setHeaderText("Are you sure you want to delete this post?");
                deleteAlert.setContentText("Please choose. . .");

                Stage alertStage = (Stage) deleteAlert.getDialogPane().getScene().getWindow();
                alertStage.getIcons().add(new Image("/images/letsplay_icon.png"));

                Optional<ButtonType> result = deleteAlert.showAndWait();

                if(result.get() == ButtonType.OK){

                    Session.notifcations.add(dbService.deletePost(post.getId()));

                    usersPostList.remove(post);
                    postsVbox.getChildren().remove(node);

                    setPostCountText();
                }else {

                    deleteAlert.close();
                }
            });

            uniqueUsersPostController.updatePostButton.setOnMouseClicked(e ->{

                try{

                    Session.updateablePost = post;
                    displayUpdatePostView();


                }catch (Exception i){

                    i.printStackTrace();
                }
            });

            postsVbox.getChildren().add(node);

        }catch (Exception e){

            e.printStackTrace();
        }
    }

    private void setPostCountText(){

        if(usersPostList != null){

            postCount.setText(Integer.toString(usersPostList.size()));
        }
    }

    @FXML
    private void displayUpdatePostView() throws IOException{

        AnchorPane updatePostView = FXMLLoader
                .load(getClass().getResource("/view/innerviews/inner_innerviews/post_update_view.fxml"));

        injectablePane.getChildren().setAll(updatePostView);
    }
}

package controllers.subsection.controllers;

import controllers.cardcontrollers.UniquePostController;
import functionality.DatabaseInteractionService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import models.Post;


import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class PostDisplayController implements Initializable {

    private DatabaseInteractionService dbService;
    private List<Post> postList;
    private List<Post> filteredPosts;
    public static String game;

    @FXML
    private VBox postsVbox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        dbService = new DatabaseInteractionService();

        postList = dbService.fetchPostList();

        if(postList != null){
            filterByGame();

            for (Post filteredPost : filteredPosts) {

                displayUniquePostsInView(filteredPost);
            }
        }
    }

    private void filterByGame(){

        filteredPosts = new ArrayList<>();

        for(int i = 0; i < postList.size(); i++){

            if(postList.get(i).getGamePlayed().toLowerCase().equals(game)){

                filteredPosts.add(postList.get(i));
            }
        }
    }

    private void displayUniquePostsInView(Post post){

        try{

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/post_view.fxml"));
            AnchorPane node = loader.load();

            UniquePostController uniquePostController = loader.getController();

            uniquePostController.postTitle.setText(post.getPostTitle());
            uniquePostController.postDescription.setText(post.getPostDescription());
            uniquePostController.username.setText(post.getGamer().getUserName());

            postsVbox.getChildren().add(node);

        }catch (Exception e){

            e.printStackTrace();
        }
    }
}

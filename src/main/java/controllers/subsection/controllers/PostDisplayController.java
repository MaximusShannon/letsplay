package controllers.subsection.controllers;

import controllers.cardcontrollers.UniquePostController;
import functionality.DatabaseInteractionService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import models.Post;


import java.net.URL;
import java.util.*;

public class PostDisplayController implements Initializable {

    private DatabaseInteractionService dbService;
    private List<Post> postList;
    private List<Post> filteredPosts;
    public static String game;

    @FXML
    private VBox postsVbox;
    @FXML
    private Text postCount;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        dbService = new DatabaseInteractionService();

        postList = dbService.fetchPostList();

        if(postList != null){
            filterByGame();

            /*Reverse the list so the new stuff is at the top.*/
            Collections.reverse(filteredPosts);

            for (Post filteredPost : filteredPosts) {

                displayUniquePostsInView(filteredPost);
            }
        }

        displayPostCount();
    }

    private void filterByGame(){

        filteredPosts = new ArrayList<>();

        for(int i = 0; i < postList.size(); i++){

            if(postList.get(i).getGamePlayed().toLowerCase().equals(game)){

                filteredPosts.add(postList.get(i));
            }
        }
    }

    private void displayPostCount(){

        postCount.setText(Integer.toString(filteredPosts.size()));
    }

    private void displayUniquePostsInView(Post post){

        try{

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/post_view.fxml"));
            AnchorPane node = loader.load();

            UniquePostController uniquePostController = loader.getController();

            uniquePostController.postTitle.setText(post.getPostTitle());
            uniquePostController.postDescription.setText(post.getPostDescription());
            uniquePostController.username.setText(post.getGamer().getUserName());
            uniquePostController.postTags.setText(post.getPostTags());

            /*Requirements display*/
            if(post.isMicrophoneRequired()){
                uniquePostController.mr_tick.setVisible(true);
            }else{
                uniquePostController.mr_x.setVisible(true);
            }
            if(post.isCasualPlayers()){
                uniquePostController.cp_tick.setVisible(true);
            }else{
                uniquePostController.cp_x.setVisible(true);
            }
            if(post.isCompetitivePlayers()){
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

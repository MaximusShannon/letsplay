package controllers.innerviewcontrollers;

import controllers.dynamicviewcontrollers.UniquePostController;
import functionality.DatabaseInteractionService;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Duration;
import models.Filter;
import models.Post;
import models.Session;
import org.springframework.context.support.ClassPathXmlApplicationContext;


import java.net.URL;
import java.util.*;

public class PostDisplayController implements Initializable {

    private DatabaseInteractionService dbService;
    private ClassPathXmlApplicationContext context;
    private Filter postFilter;
    private List<Post> postList;
    private List<Post> filteredPosts;
    private List<Post> filteredByRequirements;
    public static String game;

    @FXML private VBox postsVbox;
    @FXML private Text postCount;
    @FXML private Text refreshText;
    @FXML private ChoiceBox<String> languageFilter;
    @FXML private ChoiceBox<String> timeZoneFilter;
    @FXML private CheckBox casualAcceptedFilter;
    @FXML private CheckBox compAcceptedFilter;
    @FXML private CheckBox acceptMalesFilter;
    @FXML private CheckBox acceptFemalesFilter;
    @FXML private CheckBox micRequired;
    @FXML private AnchorPane injectablePane;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        dbService = new DatabaseInteractionService();

        initializeChoiceBoxes();

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
        initFilters();
    }

    private void initFilters(){

        languageFilter.getSelectionModel().selectFirst();
        timeZoneFilter.getSelectionModel().selectFirst();
        micRequired.setSelected(true);
        casualAcceptedFilter.setSelected(true);
        compAcceptedFilter.setSelected(true);
        acceptMalesFilter.setSelected(true);
        acceptFemalesFilter.setSelected(true);
    }

    private void filterByGame(){

        filteredPosts = new ArrayList<>();
        filteredPosts.clear();

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

    private void initializeChoiceBoxes(){

        languageFilter.setItems(FXCollections.observableArrayList("English", "Irish",
                "French", "German",
                "Norwegian", "Dutch",
                "Russian"));

        timeZoneFilter.setItems(FXCollections.observableArrayList("UTC-1", "UTC",
                "UTC+1", "UTC+2",
                "UTC+3", "UTC+4",
                "UTC+5"));
    }

    @FXML
    private void refreshPosts(){

        postsVbox.getChildren().clear();
        getMostRecentPostList();
        filterByGame();

        if(languageFilter.getValue().toLowerCase().equals("english")
                && timeZoneFilter.getValue().toLowerCase().equals("utc-1")
                && casualAcceptedFilter.isSelected()
                && compAcceptedFilter.isSelected()
                && acceptMalesFilter.isSelected()
                && acceptFemalesFilter.isSelected()){

            fadeRefreshingText();

            for(int i = 0; i < filteredPosts.size(); i++){

                displayUniquePostsInView(filteredPosts.get(i));
            }
        }else{

            createFilter();
            filterPostsByRequirements();
            fadeRefreshingText();

            for(int i = 0; i < filteredPosts.size(); i++){

                displayUniquePostsInView(filteredPosts.get(i));
            }
        }
    }

    private void filterPostsByRequirements(){

        filteredByRequirements = new ArrayList<>();

        removeAllLanguagesThatDontMatch();
        removeAllTimeZonesThatDontMatch();
        removeAllPostsThatRequireMicrophone();
        removeAllPostsThatAreLookingForCasualPlayers();
        removeAllPostsThatAreLookingForCompetitivePlayers();
        removeAllPostsThatAreLookingForMales();
        removeAllPostsThatAreLookingForFemales();

    }

    private void removeAllLanguagesThatDontMatch(){

        for(int i = 0; i < filteredPosts.size(); i++){

            if(!filteredPosts.get(i).getLanguageSpoken().toLowerCase().equals(postFilter.getLanguageRequired().toLowerCase())){

                filteredPosts.remove(i);
            }
        }
    }

    private void removeAllTimeZonesThatDontMatch(){

        for(int i = 0; i < filteredPosts.size(); i++){

            if(!filteredPosts.get(i).getTimeZone().equals(postFilter.getTimeZoneRequired())){

                filteredPosts.remove(i);
            }
        }
    }

    private void removeAllPostsThatRequireMicrophone(){

        for(int i = 0; i < filteredPosts.size(); i++){

            if(filteredPosts.get(i).isMicrophoneRequired()){

                filteredPosts.remove(i);
            }
        }
    }

    private void removeAllPostsThatAreLookingForCasualPlayers(){

        for(int i = 0; i < filteredPosts.size(); i++){

            if(filteredPosts.get(i).isAcceptMales()){

                filteredPosts.remove(i);
            }
        }
    }

    private void removeAllPostsThatAreLookingForCompetitivePlayers(){

        for(int i = 0; i < filteredPosts.size(); i++){

            if(filteredPosts.get(i).isAcceptingCompetitivePlayers()){

                filteredPosts.remove(i);
            }
        }
    }

    private void removeAllPostsThatAreLookingForMales(){

        for(int i = 0; i < filteredPosts.size(); i++){

            if(filteredPosts.get(i).isAcceptMales()){

                filteredPosts.remove(i);
            }
        }
    }

    private void removeAllPostsThatAreLookingForFemales(){

        for(int i = 0; i < filteredPosts.size(); i++){

            if(filteredPosts.get(i).isAcceptFemales()){

                filteredPosts.remove(i);
            }
        }
    }

    private void createFilter(){

        context = new ClassPathXmlApplicationContext("ApplicationContext.xml");

        postFilter = (Filter) context.getBean("filter");
        postFilter.setLanguageRequired(languageFilter.getValue());
        postFilter.setTimeZoneRequired(timeZoneFilter.getValue());
        postFilter.setMicRequired(micRequired.isSelected());
        postFilter.setAcceptCasualPlayers(casualAcceptedFilter.isSelected());
        postFilter.setAcceptCompetitivePlayers(compAcceptedFilter.isSelected());
        postFilter.setAcceptFemales(acceptFemalesFilter.isSelected());
        postFilter.setAcceptMales(acceptMalesFilter.isSelected());
    }

    private void closeContext(){

        context.close();
    }

    private void getMostRecentPostList(){

        postList.clear();
        postList.addAll(dbService.fetchPostList());
    }

    private void fadeRefreshingText(){

        refreshText.setVisible(true);

        FadeTransition ft = new FadeTransition(Duration.millis(5000), refreshText);
        ft.setFromValue(1.0);
        ft.setFromValue(0.0);

        ft.play();
    }
}

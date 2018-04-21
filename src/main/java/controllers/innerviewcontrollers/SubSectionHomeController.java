package controllers.innerviewcontrollers;

import controllers.dynamicviewcontrollers.UniqueMatchedGamerCardController;
import controllers.dynamicviewcontrollers.UniqueMatchedGroupController;
import controllers.dynamicviewcontrollers.UniqueRecentPostController;
import functionality.DatabaseInteractionService;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import models.*;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class SubSectionHomeController implements Initializable {

    private ClassPathXmlApplicationContext context;
    private DatabaseInteractionService dbService;
    private Matchmaker matchmaker;
    private List<Gamer> matchedGamers;
    private List<Post> mostRecentPosts;
    private ArrayList<GamerGroup> matchingGroupsNotAMemberOf;
    private List<GamerAvatar> gamerAvatars;
    private List<GroupAvatar> groupAvatars;

    @FXML private Text matchedGamersCount;
    @FXML private Text postCount;
    @FXML private Text matchedGroupCount;
    @FXML private HBox matchedGamersList;
    @FXML private HBox newPostsList;
    @FXML private HBox groupsBasedOnRequirements;
    @FXML private AnchorPane injectablePane;



    @Override
    public void initialize(URL url, ResourceBundle rb){

        initResources();
        getMatchedGamers();
        getMostRecentPosts();
        getMatchingGroups();

    }

    private void getMatchedGamers(){

        matchmaker.run();

        if(matchmaker.checkDoesUserHaveARequirement()){

            matchedGamers = matchmaker.checkForGamersThatMatch();

            if(matchedGamers.size() > 0){

                for(int i = 0; i < matchedGamers.size(); i++){

                    displayMatchedGamer(matchedGamers.get(i));
                }
                //display them in thew view
                matchedGamersCount.setText("Displaying " + matchedGamers.size() + " matched gamer(s)");
            }

        }else{

            //show message no requirement
        }

    }

    private void getMostRecentPosts(){

        mostRecentPosts = matchmaker.fetchMostRecentPosts();

        if(mostRecentPosts.size() > 0){

            for(int i = 0; i < mostRecentPosts.size(); i++){

                displayRecentPost(mostRecentPosts.get(i));
            }

            postCount.setText("Displaying " + mostRecentPosts.size() + " most recent post(s)");

        }else{

            //show message no posts
        }
    }

    private void getMatchingGroups(){

        matchingGroupsNotAMemberOf = matchmaker.fetchMatchingGroups();

        if(matchingGroupsNotAMemberOf != null){

            for(int i = 0; i < matchingGroupsNotAMemberOf.size(); i++){

                displayMatchedGroup(matchingGroupsNotAMemberOf.get(i));
            }

            matchedGroupCount.setText("Displaying " + matchingGroupsNotAMemberOf.size() + " group(s)");
        }else{

        }

    }

    private void displayRecentPost(Post post){

        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/dynamiclycreatedviews/recent_post_display_card.fxml"));
            Pane node = loader.load();

            UniqueRecentPostController uniqueRecentPostController = loader.getController();
            uniqueRecentPostController.postTitle.setText(post.getPostTitle());
            uniqueRecentPostController.gamePlaying.setText(post.getGamePlayed());
            uniqueRecentPostController.languageSpoken.setText(post.getLanguageSpoken());
            uniqueRecentPostController.postTags.setText(post.getPostTags());

            uniqueRecentPostController.gotoPost.setOnMouseClicked(e ->{

                Session.resetInnerViewPost();
                Session.innerViewPost = post;

                try{
                    AnchorPane innerViewPost = FXMLLoader.load(getClass().getResource("/view/innerviews/inner_innerviews/post_view_inside_with_comments.fxml"));
                    injectablePane.getChildren().setAll(innerViewPost);
                }catch (Exception i){

                    i.printStackTrace();
                }


            });

            newPostsList.getChildren().add(node);

        }catch (Exception e){

            e.printStackTrace();
        }
    }

    private void displayMatchedGroup(GamerGroup group){

        try{

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/dynamiclycreatedviews/matched-group-card.fxml"));
            Pane node = loader.load();

            UniqueMatchedGroupController uniqueMatchedGroupController = loader.getController();
            uniqueMatchedGroupController.groupName.setText(group.getGroupName());
            uniqueMatchedGroupController.gamePlaying.setText(group.getMainGame());
            uniqueMatchedGroupController.languageSpoken.setText(group.getGroupLanguageSpoken());

            groupAvatars = dbService.fetchGroupAvatars();
            if(groupAvatars.size() > 0){

                for(int i = 0; i < groupAvatars.size(); i++){

                    if(groupAvatars.get(i).getGamerGroup().getGroupId() == group.getGroupId()){

                        uniqueMatchedGroupController.groupImage.setImage(convertToJavaFXImage(groupAvatars.get(i).getImage(), 146, 161));
                    }
                }
            }

            uniqueMatchedGroupController.gotoGroup.setOnMouseClicked(e ->{

                Session.resetInnerViewGamerGroup();
                Session.resetInnerViewGamerGroupMemberList();
                Session.innerViewGamerGroup = group;
                Session.innerViewGamerGroupMemberList = group.getMemberList();

                try{
                    AnchorPane innerGroupView = FXMLLoader.load(getClass().getResource("/view/innerviews/inner_innerviews/group_information_view.fxml"));
                    injectablePane.getChildren().setAll(innerGroupView);
                }catch (Exception ex){

                    ex.printStackTrace();
                }
            });

            groupsBasedOnRequirements.getChildren().add(node);

        }catch (Exception e){

            e.printStackTrace();
        }
    }

    private void displayMatchedGamer(Gamer matchedGamer){

        try{

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/dynamiclycreatedviews/matched-gamer-card.fxml"));
            Pane node = loader.load();

            UniqueMatchedGamerCardController uniqueMatchedGamerCardController = loader.getController();
            uniqueMatchedGamerCardController.username.setText(matchedGamer.getUserName());

            if(matchedGamer.getPlayerOnlineStatus()){
                uniqueMatchedGamerCardController.onlineStatus_online.setVisible(true);
            }else {
                uniqueMatchedGamerCardController.onlineStatus_offline.setVisible(true);
            }

            gamerAvatars = dbService.fetchGamerAvatars();

            if(gamerAvatars.size() > 0){
                for(int i = 0; i < gamerAvatars.size(); i++){

                    if(gamerAvatars.get(i).getGamer().getId() == matchedGamer.getId()){

                        uniqueMatchedGamerCardController.gamerImage.setImage(convertToJavaFXImage(gamerAvatars.get(i).getAvatarImage(), 146, 161));
                    }
                }
            }

            uniqueMatchedGamerCardController.gotoProfile.setOnMouseClicked(e ->{

                try{

                    Session.resetGamerProfile();
                    Session.resetProfileImage();
                    Session.profileImage = uniqueMatchedGamerCardController.gamerImage.getImage();
                    Session.gamersProfile = matchedGamer;


                    AnchorPane profileView = FXMLLoader.load(getClass().getResource("/view/innerviews/inner_innerviews/profile_users_view.fxml"));
                    injectablePane.getChildren().setAll(profileView);

                }catch (Exception ei){

                    ei.printStackTrace();
                }

            });

            matchedGamersList.getChildren().add(node);

        }catch (Exception e){

            e.printStackTrace();
        }


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

    private void initResources(){

        context = new ClassPathXmlApplicationContext("ApplicationContext.xml");
        matchmaker = (Matchmaker) context.getBean("matchmaker");
        dbService = (DatabaseInteractionService) context.getBean("databaseinteractionservice");
    }

}

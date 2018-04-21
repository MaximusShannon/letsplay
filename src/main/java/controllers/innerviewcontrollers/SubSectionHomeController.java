package controllers.innerviewcontrollers;

import controllers.dynamicviewcontrollers.UniqueMatchedGamerCardController;
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

    @FXML private Text matchedGamersCount;
    @FXML private Text postCount;
    @FXML private Text matchedGroupCount;
    @FXML private HBox matchedGamersList;



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


            //show posts in view.
            postCount.setText("Displaying " + mostRecentPosts.size() + " most recent post(s)");

        }else{

            //show message no posts
        }
    }

    private void displayRecentPost(Post post){

        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/dynamiclycreatedviews/recent_post_display_card.fxml"));
            Pane node = loader.load();

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

                //got to profile

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

    private void getMatchingGroups(){

        matchingGroupsNotAMemberOf = matchmaker.fetchMatchingGroups();

        if(matchingGroupsNotAMemberOf != null){


            matchedGroupCount.setText("Displaying " + matchingGroupsNotAMemberOf.size() + " group(s)");
        }else{

            System.out.println("NO GROUPS THAT MATCH");
        }

    }

    private void initResources(){

        context = new ClassPathXmlApplicationContext("ApplicationContext.xml");
        matchmaker = (Matchmaker) context.getBean("matchmaker");
        dbService = (DatabaseInteractionService) context.getBean("databaseinteractionservice");
    }

}

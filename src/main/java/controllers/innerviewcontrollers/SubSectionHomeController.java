package controllers.innerviewcontrollers;

import controllers.dynamicviewcontrollers.UniqueMatchedGamerCardController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import models.Gamer;
import models.GamerGroup;
import models.Matchmaker;
import models.Post;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class SubSectionHomeController implements Initializable {

    private ClassPathXmlApplicationContext context;
    private Matchmaker matchmaker;
    private List<Gamer> matchedGamers;
    private List<Post> mostRecentPosts;
    private ArrayList<GamerGroup> matchingGroupsNotAMemberOf;

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

            //handle button click

            matchedGamersList.getChildren().add(node);


        }catch (Exception e){

            e.printStackTrace();
        }


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
    }

}

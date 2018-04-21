package controllers.innerviewcontrollers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

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

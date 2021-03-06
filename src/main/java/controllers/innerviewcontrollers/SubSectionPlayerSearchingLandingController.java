package controllers.innerviewcontrollers;

import functionality.DatabaseInteractionService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import models.Post;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class SubSectionPlayerSearchingLandingController implements Initializable {

    private DatabaseInteractionService dbService;
    private List<Post> postsRetrieved;
    private int fortniteCount;
    private int pubgCount;
    private int lolCount;
    private int rustCount;
    private int escapeFromTarkovCount;
    private int csgoCount;
    private int rb6sCount;
    private int dayzCount;

    @FXML private AnchorPane playerSearchingPane;
    @FXML private Pane fortnitePane;
    @FXML private Text fortnitePostCount;
    @FXML private Text pubgPostCount;
    @FXML private Text lolPostCount;
    @FXML private Text rustPostCount;
    @FXML private Text eftPostCount;
    @FXML private Text csgoPostCount;
    @FXML private Text rb6sPostCount;
    @FXML private Text dayzPostCount;

    @Override
    public void initialize(URL url, ResourceBundle rb){

        initCounters();
        getPostList();
        countGames();
        updateView();
    }

    private void getPostList(){

        dbService = new DatabaseInteractionService();

        postsRetrieved = dbService.fetchPostList();
    }

    private void initCounters(){

        fortniteCount = 0;
        pubgCount = 0;
        lolCount = 0;
        rustCount = 0;
        escapeFromTarkovCount = 0;
        csgoCount = 0;
        rb6sCount = 0;
        dayzCount = 0;
    }

    private void countGames(){

        for (Post aPostsRetrieved : postsRetrieved) {

            switch (aPostsRetrieved.getGamePlayed().toLowerCase()) {

                case "fortnite":
                    fortniteCount++;
                    break;
                case "league of legends":
                    lolCount++;
                    break;
                case "pubg":
                    pubgCount++;
                    break;
                case "counter-strike: global offensive":
                    csgoCount++;
                    break;
                case "rust":
                    rustCount++;
                    break;
                case "dayz":
                    dayzCount++;
                    break;
                case "rainbow six:siege":
                    rb6sCount++;
                    break;
                case "escape from tarkov":
                    escapeFromTarkovCount++;
                    break;
            }
        }
    }

    private void updateView(){

        fortnitePostCount.setText(Integer.toString(fortniteCount));
        pubgPostCount.setText(Integer.toString(pubgCount));
        lolPostCount.setText(Integer.toString(lolCount));
        rustPostCount.setText(Integer.toString(rustCount));
        eftPostCount.setText(Integer.toString(escapeFromTarkovCount));
        csgoPostCount.setText(Integer.toString(csgoCount));
        rb6sPostCount.setText(Integer.toString(rb6sCount));
        dayzPostCount.setText(Integer.toString(dayzCount));
    }

    @FXML
    private void loadFortunePostDisplayView(){

        try{

            PostDisplayController.game = "fortnite";

            AnchorPane postDisplay = FXMLLoader.load(getClass().getResource("/view/innerviews/postsdisplay.fxml"));
            playerSearchingPane.getChildren().setAll(postDisplay);

        }catch (Exception e){

            e.printStackTrace();
        }
    }

    @FXML
    private void loadPUBGPostDisplayView(){

        try{

            PostDisplayController.game = "pubg";

            AnchorPane postDisplay = FXMLLoader.load(getClass().getResource("/view/innerviews/postsdisplay.fxml"));
            playerSearchingPane.getChildren().setAll(postDisplay);
        }catch (Exception e){

            e.printStackTrace();
        }
    }

    @FXML
    private void loadLeagueOfLegendsPostView(){

        try{

            PostDisplayController.game = "league of legends";

            AnchorPane postDisplay = FXMLLoader.load(getClass().getResource("/view/innerviews/postsdisplay.fxml"));
            playerSearchingPane.getChildren().setAll(postDisplay);

        }catch (Exception e){

            e.printStackTrace();
        }
    }

    @FXML
    private void loadRustPostView(){

        try{

            PostDisplayController.game = "rust";

            AnchorPane postDisplay = FXMLLoader.load(getClass().getResource("/view/innerviews/postsdisplay.fxml"));
            playerSearchingPane.getChildren().setAll(postDisplay);

        }catch (Exception e){

            e.printStackTrace();
        }
    }

    @FXML
    private void loadEscapeFromTarkovPostView(){

        try{

            PostDisplayController.game = "escape from tarkov";

            AnchorPane postDisplay = FXMLLoader.load(getClass().getResource("/view/innerviews/postsdisplay.fxml"));
            playerSearchingPane.getChildren().setAll(postDisplay);
        }catch (Exception e){

            e.printStackTrace();
        }
    }

    @FXML
    private void loadCsgoPostView(){

        try{

            PostDisplayController.game = "counter-strike: global offensive";

            AnchorPane postDisplay = FXMLLoader.load(getClass().getResource("/view/innerviews/postsdisplay.fxml"));
            playerSearchingPane.getChildren().setAll(postDisplay);
        }catch (Exception e){

            e.printStackTrace();
        }
    }

    @FXML
    private void loadRb6sPostView(){

        try{

            PostDisplayController.game = "rainbow six: siege";

            AnchorPane postDisplay = FXMLLoader.load(getClass().getResource("/view/innerviews/postsdisplay.fxml"));
            playerSearchingPane.getChildren().setAll(postDisplay);
        }catch (Exception e){

            e.printStackTrace();
        }
    }

    @FXML
    private void loadDayzPostView(){

        try{

            PostDisplayController.game = "dayz";

            AnchorPane postDisplay = FXMLLoader.load(getClass().getResource("/view/innerviews/postsdisplay.fxml"));
            playerSearchingPane.getChildren().setAll(postDisplay);
        }catch (Exception e){

            e.printStackTrace();
        }
    }

}

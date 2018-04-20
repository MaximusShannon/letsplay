package controllers.innerviewcontrollers;

import controllers.dynamicviewcontrollers.UniqueGroupController;
import functionality.DatabaseInteractionService;
import javafx.animation.FadeTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Duration;
import models.GamerGroup;
import models.GroupAvatar;
import models.MemberList;
import models.Session;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.lang.reflect.Member;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

public class GroupMainViewController implements Initializable {

    private List<GamerGroup> publicGroups;
    private List<MemberList> publicGroupMemberLists;
    private List<GamerGroup> filteredByGame;
    private DatabaseInteractionService dbService;
    private ClassPathXmlApplicationContext context;
    private List<GroupAvatar> groupAvatars;

    @FXML private AnchorPane injectablePane;
    @FXML private Button createGroupBtn;
    @FXML private VBox groupsVbox;
    @FXML private Text groupCount;
    @FXML private ChoiceBox<String> gameFilter;

    @FXML
    private void openCreateViewView() throws IOException{

        AnchorPane createGroupPane = FXMLLoader.load(getClass().getResource("/view/innerviews/creategroupview.fxml"));
        injectablePane.getChildren().setAll(createGroupPane);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        initContext();
        dbService = (DatabaseInteractionService) context.getBean("databaseinteractionservice");
        publicGroups = getPublicGroups(dbService.fetchGroupsList());
        publicGroupMemberLists = dbService.fetchMemberLists();

        Collections.reverse(publicGroups);

        for(int i = 0; i < publicGroups.size(); i++){

            displayUniqueGroupsInView(publicGroups.get(i));
        }

        instantiateGroupGamePlayedChoiceBox();

        setGroupCount(publicGroups.size());
        closeContext();
    }

    private void instantiateGroupGamePlayedChoiceBox(){

        gameFilter.setItems(FXCollections.observableArrayList("All groups", "League Of Legends", "FORTNITE",
                "PUBG", "Counter-Strike: Global Offensive",
                "Rust", "DayZ",
                "Rainbow SIX: Siege", "Escape From Tarkov"));

        gameFilter.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {

            filterByGame(newValue);
        });
    }

    /**
     * All games = 0, league of legends = 1, fortnite = 2, etc.
     * @param choiceBoxValueId
     */
    private void filterByGame(Number choiceBoxValueId){

        switch (choiceBoxValueId.toString()){

            case "0":
                filteredByGame = filterGames("All games");
                displayFilteredList(filteredByGame);

                break;
            case "1":
                filteredByGame = filterGames("League Of Legends");
                displayFilteredList(filteredByGame);

                break;
            case "2":
                filteredByGame = filterGames("FORTNITE");
                displayFilteredList(filteredByGame);

                break;
            case "3":
                filteredByGame = filterGames("PUBG");
                displayFilteredList(filteredByGame);

                break;
            case "4":
                filteredByGame = filterGames("Counter-Strike: Global Offensive");
                displayFilteredList(filteredByGame);

                break;
            case "5":
                filteredByGame = filterGames("Rust");
                displayFilteredList(filteredByGame);

                break;
            case "6":
                filteredByGame = filterGames("DayZ");
                displayFilteredList(filteredByGame);

                break;
            case "7":
                filteredByGame = filterGames("Rainbow SIX: Siege");
                displayFilteredList(filteredByGame);

                break;
            case "8":
                filteredByGame = filterGames("Escape From Tarkov");
                displayFilteredList(filteredByGame);

                break;
        }

    }

    private void displayFilteredList(List<GamerGroup> filteredGroup){

        groupsVbox.getChildren().clear();

        for(int i = 0; i < filteredGroup.size(); i++){

            displayUniqueGroupsInView(filteredGroup.get(i));
        }

        setGroupCount(filteredByGame.size());

        filteredByGame.clear();
    }

    private List<GamerGroup> filterGames(String gameName){

        ArrayList<GamerGroup> matchedList = new ArrayList<>();

        if(gameName.equals("All games")){

            matchedList.addAll(publicGroups);
            return matchedList;
        }

        for(int i = 0; i < publicGroups.size(); i++){

            if(publicGroups.get(i).getMainGame().equals(gameName)){

                matchedList.add(publicGroups.get(i));
            }

        }

        return matchedList;
    }

    private void setGroupCount(int size){

        groupCount.setText("Displaying " + size + " group(s).");
    }

    private void displayUniqueGroupsInView(GamerGroup group){

        int groupSize = checkGroupSize(group.getGroupId());
        MemberList currentMemberList = getGroupMemberList(group.getGroupId());

        groupAvatars = dbService.fetchGroupAvatars();

        try{

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/dynamiclycreatedviews/group_display_card.fxml"));
            AnchorPane node = loader.load();

            UniqueGroupController uniqueGroupController = loader.getController();

            uniqueGroupController.groupName.setText(group.getGroupName());
            uniqueGroupController.gamePlayed.setText(group.getMainGame());
            uniqueGroupController.comsChannel.setText(group.getComsChannel());
            uniqueGroupController.activityLevel.setText(group.getActivityLevel());
            uniqueGroupController.groupSize.setText(Integer.toString(groupSize +1) + " / 15");

            for(int i = 0; i < groupAvatars.size(); i++){

                if(groupAvatars.get(i).getGamerGroup().getGroupId() == group.getGroupId()){

                    uniqueGroupController.groupImage.setImage(convertToJavaFXImage(groupAvatars.get(i).getImage(), 218, 202));
                }
            }

            uniqueGroupController.viewPost.setOnMouseClicked( e->{

                Session.resetInnerViewGamerGroup();
                Session.resetInnerViewGamerGroupMemberList();
                Session.innerViewGamerGroup = group;
                Session.innerViewGamerGroupMemberList = currentMemberList;

                openInnerGamerGroupView();
            });

            groupsVbox.getChildren().add(node);

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

    private void openInnerGamerGroupView(){

        try{
            AnchorPane innerGamerGroupView = FXMLLoader.load(getClass().getResource("/view/innerviews/inner_innerviews/group_information_view.fxml"));
            injectablePane.getChildren().setAll(innerGamerGroupView);

        }catch (Exception e){

            e.printStackTrace();
        }
    }

    private MemberList getGroupMemberList(int groupId){

        MemberList matchingMemberList = new MemberList();

        for(int i = 0; i < publicGroupMemberLists.size(); i++){

            if(publicGroupMemberLists.get(i).getMemberListId() == groupId){

                matchingMemberList = publicGroupMemberLists.get(i);
            }
        }

        return matchingMemberList;
    }

    private int checkGroupSize(int groupId){

        int size = 0;


        for(int i = 0; i < publicGroupMemberLists.size(); i++){

            if(publicGroupMemberLists.get(i).getMemberListId() == groupId){

                if(publicGroupMemberLists.get(i).getM_2_id() > 0){

                    size++;
                }
                if(publicGroupMemberLists.get(i).getM_3_id() > 0){

                    size++;
                }
                if(publicGroupMemberLists.get(i).getM_4_id() > 0){

                    size++;
                }
                if(publicGroupMemberLists.get(i).getM_5_id() > 0){

                    size++;
                }
                if(publicGroupMemberLists.get(i).getM_6_id() > 0){

                    size++;
                }
                if(publicGroupMemberLists.get(i).getM_7_id() > 0){

                    size++;
                }
                if(publicGroupMemberLists.get(i).getM_8_id() > 0){

                    size++;
                }
                if(publicGroupMemberLists.get(i).getM_9_id() > 0){

                    size++;
                }
                if(publicGroupMemberLists.get(i).getM_10_id() > 0){

                    size++;
                }
                if(publicGroupMemberLists.get(i).getM_11_id() > 0){

                    size++;
                }
                if(publicGroupMemberLists.get(i).getM_12_id() > 0){

                    size++;
                }
                if(publicGroupMemberLists.get(i).getM_13_id() > 0){

                    size++;
                }
                if(publicGroupMemberLists.get(i).getM_14_id() > 0){

                    size++;
                }
                if(publicGroupMemberLists.get(i).getM_15_id() > 0){

                    size++;
                }
            }
        }

        return size;
    }

    private void initContext(){

        context = new ClassPathXmlApplicationContext("ApplicationContext.xml");
    }

    private void closeContext(){

        context.close();
    }

    private List<GamerGroup> getPublicGroups(List<GamerGroup> unfilteredResults){

        for(int i = 0; i < unfilteredResults.size(); i++){

            if(unfilteredResults.get(i).getGroupVisability().equals("Private")){

                unfilteredResults.remove(i);
            }
        }

        return unfilteredResults;
    }
}

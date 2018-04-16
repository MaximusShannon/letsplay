package controllers.innerviewcontrollers;

import controllers.dynamicviewcontrollers.UniqueGroupController;
import functionality.DatabaseInteractionService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import models.GamerGroup;
import models.MemberList;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

public class GroupMainViewController implements Initializable {

    private List<GamerGroup> publicGroups;
    private List<MemberList> publicGroupMemberLists;
    private DatabaseInteractionService dbService;
    private ClassPathXmlApplicationContext context;

    @FXML private AnchorPane injectablePane;
    @FXML private Button createGroupBtn;
    @FXML private VBox groupsVbox;
    @FXML private Text groupCount;

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

        setGroupCount();
    }

    private void setGroupCount(){

        groupCount.setText("Displaying " + publicGroups.size() + " groups.");
    }

    private void displayUniqueGroupsInView(GamerGroup group){

        int groupSize = checkGroupSize(group.getGroupId());

        try{

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/dynamiclycreatedviews/group_display_card.fxml"));
            AnchorPane node = loader.load();

            UniqueGroupController uniqueGroupController = loader.getController();

            uniqueGroupController.groupName.setText(group.getGroupName());
            uniqueGroupController.gamePlayed.setText(group.getMainGame());
            uniqueGroupController.comsChannel.setText(group.getComsChannel());
            uniqueGroupController.activityLevel.setText(group.getActivityLevel());
            uniqueGroupController.groupSize.setText(Integer.toString(groupSize +1) + " / 15");

            groupsVbox.getChildren().add(node);

        }catch (Exception e){

            e.printStackTrace();
        }
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

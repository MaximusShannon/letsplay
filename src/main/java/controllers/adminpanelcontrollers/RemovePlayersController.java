package controllers.adminpanelcontrollers;

import functionality.DatabaseInteractionService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import models.Gamer;
import models.MemberList;
import models.Session;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class RemovePlayersController implements Initializable {

    private ClassPathXmlApplicationContext context;
    private DatabaseInteractionService dbService;
    private MemberList groupMemberList;
    private List<Gamer> members;

    @FXML private VBox groupMemberListVbox;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        initResources();
        getMembers();

        if(members.size() > 0){

            for(int i = 0; i < members.size(); i++){

                displayMembersInVbox(members.get(i));

            }
        }

        closeResources();

    }

    private void displayMembersInVbox(Gamer member){

        try{

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/dynamiclycreatedviews/group_member_remove_card.fxml"));
            AnchorPane node = loader.load();

            RemovePlayerCardController removePlayerCardController = loader.getController();

            removePlayerCardController.memberUsername.setText(member.getUserName());
            removePlayerCardController.memberUserId.setText("UserID: " + member.getId());
            removePlayerCardController.removeMemberButton.setOnMouseClicked(e ->{

                dbService.removeMemberFromGroupMemberList(Session.adminGroup.getMemberList(), member.getId());

                groupMemberListVbox.getChildren().remove(node);
                //TODO: Notify the user the member has been removed.
            });

            groupMemberListVbox.getChildren().add(node);
        }catch (Exception e){

            e.printStackTrace();
        }
    }

    private void getMembers(){

        members = new ArrayList<>();

        if(groupMemberList.getM_2_id() != 0){

            members.add(dbService.fetchGamerForGroupMemberList(groupMemberList.getM_2_id()));
        }
        if(groupMemberList.getM_3_id() != 0){

            members.add(dbService.fetchGamerForGroupMemberList(groupMemberList.getM_3_id()));
        }
        if(groupMemberList.getM_4_id() != 0){

            members.add(dbService.fetchGamerForGroupMemberList(groupMemberList.getM_4_id()));
        }
        if(groupMemberList.getM_5_id() != 0){

            members.add(dbService.fetchGamerForGroupMemberList(groupMemberList.getM_5_id()));
        }
        if(groupMemberList.getM_6_id() != 0){

            members.add(dbService.fetchGamerForGroupMemberList(groupMemberList.getM_6_id()));
        }
        if(groupMemberList.getM_7_id() != 0){

            members.add(dbService.fetchGamerForGroupMemberList(groupMemberList.getM_7_id()));
        }
        if(groupMemberList.getM_8_id() != 0){

            members.add(dbService.fetchGamerForGroupMemberList(groupMemberList.getM_8_id()));
        }
        if(groupMemberList.getM_9_id() != 0){

            members.add(dbService.fetchGamerForGroupMemberList(groupMemberList.getM_9_id()));
        }
        if(groupMemberList.getM_10_id() != 0){

            members.add(dbService.fetchGamerForGroupMemberList(groupMemberList.getM_10_id()));
        }
        if(groupMemberList.getM_11_id() != 0){

            members.add(dbService.fetchGamerForGroupMemberList(groupMemberList.getM_11_id()));
        }
        if(groupMemberList.getM_12_id() != 0){

            members.add(dbService.fetchGamerForGroupMemberList(groupMemberList.getM_12_id()));
        }
        if(groupMemberList.getM_13_id() != 0){

            members.add(dbService.fetchGamerForGroupMemberList(groupMemberList.getM_13_id()));
        }
        if(groupMemberList.getM_14_id() != 0){

            members.add(dbService.fetchGamerForGroupMemberList(groupMemberList.getM_14_id()));
        }
        if(groupMemberList.getM_15_id() != 0){

            members.add(dbService.fetchGamerForGroupMemberList(groupMemberList.getM_14_id()));
        }
    }

    private void initResources(){

        context = new ClassPathXmlApplicationContext("ApplicationContext.xml");
        dbService = (DatabaseInteractionService) context.getBean("databaseinteractionservice");

        groupMemberList = Session.adminGroup.getMemberList();
    }

    private void closeResources(){

        context.close();
    }
}

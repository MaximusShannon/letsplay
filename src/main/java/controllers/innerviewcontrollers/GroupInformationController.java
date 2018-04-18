package controllers.innerviewcontrollers;

import controllers.dynamicviewcontrollers.UniqueGroupMemberController;
import functionality.DatabaseInteractionService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import models.Gamer;
import models.Session;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class GroupInformationController implements Initializable {

    private int[] memberListIds;
    private DatabaseInteractionService dbService;
    private ClassPathXmlApplicationContext context;
    private ArrayList<Gamer> memberList;

    @FXML private Text groupName;
    @FXML private Text groupComsChannel;
    @FXML private Text memberSize;
    @FXML private Text groupGamePlayed;
    @FXML private Text comsServerAddress;
    @FXML private Text activityLevel;
    @FXML private Text languageSpoken;
    @FXML private Text groupDescription;
    @FXML private Text isMember;
    @FXML private VBox memberlistVbox;
    @FXML private Button groupApplyButton;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        initContext();
        getMemberListIds();
        getGroupMembers();

        fillInformationFromGroupSession();

        for(int i = 0; i < memberList.size(); i++){

            fillMemberListVBox(memberList.get(i));

            if(checkIsAdminViewing()){

                groupApplyButton.setVisible(false);
                isMember.setVisible(true);
            }
            if(checkIsLoggedInGamerAMemberOfThisGroup()){

                groupApplyButton.setVisible(false);
                isMember.setVisible(true);
            }
        }

        closeContext();
    }

    @FXML
    private void startApplication(){


    }

    private boolean checkIsAdminViewing(){

        return Session.gamerSession.getId() == Session.innerViewGamerGroup.getAdminId();
    }

    private boolean checkIsLoggedInGamerAMemberOfThisGroup(){

        for(int i = 0; i < memberListIds.length; i++){

            return memberListIds[i] == Session.gamerSession.getId();
        }

        return false;
    }


    private void getMemberListIds(){

        memberListIds = new int[15];

        memberListIds[0] = Session.innerViewGamerGroupMemberList.getM_1_id();
        memberListIds[1] = Session.innerViewGamerGroupMemberList.getM_2_id();
        memberListIds[2] = Session.innerViewGamerGroupMemberList.getM_3_id();
        memberListIds[3] = Session.innerViewGamerGroupMemberList.getM_4_id();
        memberListIds[4] = Session.innerViewGamerGroupMemberList.getM_5_id();
        memberListIds[5] = Session.innerViewGamerGroupMemberList.getM_6_id();
        memberListIds[6] = Session.innerViewGamerGroupMemberList.getM_7_id();
        memberListIds[7] = Session.innerViewGamerGroupMemberList.getM_8_id();
        memberListIds[8] = Session.innerViewGamerGroupMemberList.getM_9_id();
        memberListIds[9] = Session.innerViewGamerGroupMemberList.getM_10_id();
        memberListIds[10] = Session.innerViewGamerGroupMemberList.getM_11_id();
        memberListIds[11] = Session.innerViewGamerGroupMemberList.getM_12_id();
        memberListIds[12] = Session.innerViewGamerGroupMemberList.getM_13_id();
        memberListIds[13] = Session.innerViewGamerGroupMemberList.getM_14_id();
        memberListIds[14] = Session.innerViewGamerGroupMemberList.getM_15_id();
    }

    private void fillInformationFromGroupSession(){

        groupName.setText(Session.innerViewGamerGroup.getGroupName());
        groupComsChannel.setText("Find us on: " + Session.innerViewGamerGroup.getComsChannel());
        memberSize.setText("# " + memberList.size() + "/ 15 Members");
        groupGamePlayed.setText(Session.innerViewGamerGroup.getMainGame());
        comsServerAddress.setText(Session.innerViewGamerGroup.getGroupComsAddress());
        activityLevel.setText(Session.innerViewGamerGroup.getActivityLevel());
        languageSpoken.setText(Session.innerViewGamerGroup.getGroupLanguageSpoken());
        groupDescription.setText(Session.innerViewGamerGroup.getGroupDescription());
    }

    private void  getGroupMembers(){

        dbService = (DatabaseInteractionService) context.getBean("databaseinteractionservice");
        memberList = new ArrayList<>();

        for(int i = 0; i < memberListIds.length; i++){

            if(memberListIds[i] > 0){

                memberList.add(dbService.fetchGamerForGroupMemberList(memberListIds[i]));
            }
        }

    }

    private void fillMemberListVBox(Gamer groupMember){

        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/dynamiclycreatedviews/group_member_card.fxml"));
            AnchorPane node = loader.load();

            UniqueGroupMemberController uniqueGroupMemberController = loader.getController();
            uniqueGroupMemberController.username.setText(groupMember.getUserName());

            memberlistVbox.getChildren().add(node);

        }catch (Exception e){

            e.printStackTrace();
        }
    }

    private void initContext(){

        context = new ClassPathXmlApplicationContext("ApplicationContext.xml");
    }

    private void closeContext(){

        context.close();
    }


}

package controllers.innerviewcontrollers;

import controllers.dynamicviewcontrollers.UniqueGroupInvitationController;
import controllers.dynamicviewcontrollers.UniqueMessageController;
import functionality.DatabaseInteractionService;
import functionality.InvitationService;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Duration;
import models.*;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ProfileInviteAndMessagesController implements Initializable {

    private ClassPathXmlApplicationContext context;
    private DatabaseInteractionService dbService;
    private InvitationService invService;
    private List<Invitation> invitationList;
    private List<Invitation> validInvitations;
    private List<GroupMessage> messageList;
    private List<GamerGroup> groupList;

    @FXML private VBox invitationVbox;
    @FXML private VBox messagesVbox;
    @FXML private Text inivteAccepted;
    @FXML private Text inviteFailed;
    @FXML private Text inviteDeclined;
    @FXML private Text inviteCount;
    @FXML private Text messageCount;
    @FXML private Text messageDeleted;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        initResources();

        displayInvites();
        displayMessages();

        closeResources();
    }

    private void displayMessages(){

        fetchThisUsersMessages();

        if(messageList.size() > 0){

            for(int i = 0; i < messageList.size(); i++){

                displayMessageCard(messageList.get(i));
            }

            messageCount.setText("Displaying " + messageList.size() + " message(s).");
        }
    }

    private void displayMessageCard(GroupMessage message){

        try{

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/dynamiclycreatedviews/message_card.fxml"));
            AnchorPane node = loader.load();

            UniqueMessageController uniqueMessageController = loader.getController();

            uniqueMessageController.messenger.setText(dbService.fetchGamerGroupsName(message.getGroupId())+ " sent you a message.");
            uniqueMessageController.messageText.setText(message.getMessage());
            uniqueMessageController.deleteMessage.setOnMouseClicked(e ->{

                dbService.deleteMessage(message.getMessageId());
                messageDeleted.setVisible(true);
                fadeText(messageDeleted);

                messagesVbox.getChildren().remove(node);
            });

            messagesVbox.getChildren().add(node);

        }catch (Exception e){

            e.printStackTrace();
        }
    }

    private void fetchThisUsersMessages(){

        List<GroupMessage> groupMessages = dbService.fetchGroupMessages();
        messageList = new ArrayList<>();

        for(int i = 0; i < groupList.size(); i++){

            if(checkIsUserAMemberOfGroup(groupList.get(i).getMemberList(), Session.gamerSession.getId())){

                for(int x = 0; x < groupMessages.size(); x++){

                    if(groupMessages.get(x).getGroupId() == groupList.get(i).getGroupId()){

                        messageList.add(groupMessages.get(x));
                    }
                }
            }
        }
    }

    private void displayInvites(){

        fetchThisUsersInvites();
        groupList = dbService.fetchGroupsList();

        List<Integer> validInvitationGroupIds = checkGroupThatSentInvitationStillExists();

        if(validInvitationGroupIds.size() > 0){

            validInvitations = cleanInvites(validInvitationGroupIds);
            inviteCount.setText("Displaying " + validInvitations.size() + " invite(s).");
        }

        if(validInvitations != null && validInvitations.size() > 0){

            for(int i = 0; i < validInvitations.size(); i++){

                displayUniqueInvitationInView(validInvitations.get(i));
            }
        }

    }

    private List<Invitation> cleanInvites(List<Integer> validInvitationIds){

        List<Invitation> validInvitations = new ArrayList<>();

        for(int i = 0; i < validInvitationIds.size(); i++){

            for(int x = 0; x < invitationList.size(); x++){

                if(validInvitationIds.get(i) == invitationList.get(x).getGroupId()){

                    validInvitations.add(invitationList.get(x));
                }
            }
        }
        return validInvitations;
    }

    private ArrayList<Integer> checkGroupThatSentInvitationStillExists(){

        int[] invitationGroupIds = new int[invitationList.size()];
        int[] groupIds = new int[groupList.size()];

        ArrayList<Integer> validInvitationIds = new ArrayList<>();

        for(int y = 0; y < invitationList.size(); y++){

            invitationGroupIds[y] = invitationList.get(y).getGroupId();
        }

        for(int z = 0; z < groupIds.length; z++){

            groupIds[z] = groupList.get(z).getGroupId();
        }

        for(int i = 0; i < invitationGroupIds.length; i++){

            for(int x = 0; x < groupIds.length; x++){

                if(invitationGroupIds[i] == groupIds[x]){

                    validInvitationIds.add(invitationGroupIds[i]);
                }
            }
        }

        return validInvitationIds;
    }

    private void displayUniqueInvitationInView(Invitation inv){

        try{

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/dynamiclycreatedviews/invitation_card.fxml"));
            AnchorPane node = loader.load();

            UniqueGroupInvitationController uniqueGroupInvitationController = loader.getController();

            for(int i = 0; i < groupList.size(); i++){

                if(groupList.get(i).getGroupId() == inv.getGroupId()){

                    uniqueGroupInvitationController.groupName.setText(groupList.get(i).getGroupName() + " has invited you to join them!");
                }
            }

            uniqueGroupInvitationController.inviteMessage.setText(inv.getInvitationMessage());
            uniqueGroupInvitationController.acceptInvite.setOnMouseClicked(e ->{

                for(int i = 0; i < groupList.size(); i++){

                    if(groupList.get(i).getGroupId() == inv.getGroupId()){

                        if(!checkIsUserAMemberOfGroup(groupList.get(i).getMemberList(), inv.getInvitedGamer().getId())){

                            if(dbService.addMemberToGroupMemberList(groupList.get(i).getMemberList(), inv.getInvitedGamer().getId())){

                                inivteAccepted.setVisible(true);
                                fadeText(inivteAccepted);

                                if(dbService.getSessionFactory() == null){

                                    dbService.initFactory();
                                }

                                dbService.deleteInvitation(inv.getId());

                                invitationVbox.getChildren().remove(node);
                            }else{

                                inviteFailed.setVisible(true);
                                fadeText(inviteFailed);

                                invitationVbox.getChildren().remove(node);
                            }
                        }else{

                            inviteFailed.setVisible(true);
                            fadeText(inviteFailed);
                            dbService.deleteInvitation(inv.getId());

                            invitationVbox.getChildren().remove(node);
                        }
                    }
                }
            });

            uniqueGroupInvitationController.declineInvite.setOnMouseClicked(e ->{

                inviteDeclined.setVisible(true);
                fadeText(inviteDeclined);

                dbService.deleteInvitation(inv.getId());

                invitationVbox.getChildren().remove(node);
            });

            invitationVbox.getChildren().add(node);
        }catch (Exception e){

            e.printStackTrace();
        }
    }

    private void fadeText(Text toFade){

        FadeTransition ft = new FadeTransition(Duration.millis(5000), toFade);
        ft.setFromValue(1.0);
        ft.setToValue(0.0);

        ft.play();
    }

    private boolean checkIsUserAMemberOfGroup(MemberList memberList, int invitedId){

        boolean memberAlready = false;

        if(memberList.getM_2_id() == invitedId){

            memberAlready = true;
        }
        if(memberList.getM_3_id() == invitedId){

            memberAlready = true;
        }
        if(memberList.getM_4_id() == invitedId){

            memberAlready = true;
        }
        if(memberList.getM_5_id() == invitedId){

            memberAlready = true;
        }
        if(memberList.getM_6_id() == invitedId){

            memberAlready = true;
        }
        if(memberList.getM_7_id() == invitedId){

            memberAlready = true;
        }
        if(memberList.getM_8_id() == invitedId){

            memberAlready = true;
        }
        if(memberList.getM_9_id() == invitedId){

            memberAlready = true;
        }
        if(memberList.getM_10_id() == invitedId){

            memberAlready = true;
        }
        if(memberList.getM_11_id() == invitedId){

            memberAlready = true;
        }
        if(memberList.getM_12_id() == invitedId){

            memberAlready = true;
        }
        if(memberList.getM_13_id() == invitedId){

            memberAlready = true;
        }
        if(memberList.getM_14_id() == invitedId){

            memberAlready = true;
        }
        if(memberList.getM_15_id() == invitedId){

            memberAlready = true;
        }

        return memberAlready;
    }

    private void fetchThisUsersInvites(){

        List<Invitation> allInvitations = dbService.fetchAllInvitations();
        invitationList = new ArrayList<>();

        for(int i = 0; i < allInvitations.size(); i++){

            if(allInvitations.get(i).getInvitedGamer().getId() == Session.gamerSession.getId()){

                invitationList.add(allInvitations.get(i));
            }
        }
    }

    private void initResources(){

        context = new ClassPathXmlApplicationContext("ApplicationContext.xml");
        dbService = (DatabaseInteractionService) context.getBean("databaseinteractionservice");
    }

    private void closeResources(){

        context.close();
    }


}

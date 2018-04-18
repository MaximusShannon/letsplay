package controllers.adminpanelcontrollers;

import functionality.InvitationService;
import functionality.Validator;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.util.Duration;
import models.Invitation;
import models.Session;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class InvitePlayersController {

    private ClassPathXmlApplicationContext context;
    private Validator validator;
    private InvitationService invitationService;
    private Invitation groupInvitation;

    @FXML private TextField invitationUsername;
    @FXML private TextField invitationMessage;
    @FXML private Text failureText;
    @FXML private Text userNameFail;
    @FXML private Text alreadyMember;
    @FXML private Text invitationSent;

    @FXML
    private void sendInvitation(){

        initResources();

        if(validator.validateTextFieldNotEmpty(invitationUsername.getText())){

            invitationService = (InvitationService) context.getBean("invitationservice");
            invitationService.initResources();

            groupInvitation = (Invitation) context.getBean("invitation");

            if(invitationService.checkDoesGamerExist(invitationUsername.getText()) > 0){

                int gamerId = invitationService.checkDoesGamerExist(invitationUsername.getText());

                if(!invitationService.checkIsGamerAMemberOfGroup(gamerId)){

                    groupInvitation.setInvitationMessage(invitationMessage.getText());
                    groupInvitation.setInvitedGamer(invitationService.fetchGamerForInvitation(gamerId));
                    groupInvitation.setGroupId(Session.adminGroup.getGroupId());

                    invitationService.persistInvitation(groupInvitation);
                    invitationService.closeResources();

                    // display notification that invitation was sent.
                    invitationSent.setVisible(true);
                    fadeFailureText(invitationSent);

                }else{

                    alreadyMember.setVisible(true);
                    fadeFailureText(alreadyMember);
                }

                userNameFail.setVisible(false);
            }else {

                userNameFail.setVisible(true);
            }

        }else {

            failureText.setVisible(true);
            fadeFailureText(failureText);
        }

        closeResources();
    }

    private void initResources(){

        context = new ClassPathXmlApplicationContext("ApplicationContext.xml");
        validator = (Validator) context.getBean("validator");
    }

    private void closeResources(){

        context.close();
    }

    private void fadeFailureText(Text failureText){

        FadeTransition ft = new FadeTransition(Duration.millis(5000), failureText);
        ft.setFromValue(1.0);
        ft.setToValue(0.0);

        ft.play();
    }

}

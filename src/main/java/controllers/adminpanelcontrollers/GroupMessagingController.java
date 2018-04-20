package controllers.adminpanelcontrollers;

import functionality.DatabaseInteractionService;
import functionality.Validator;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import javafx.util.Duration;
import models.GroupMessage;
import models.Session;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class GroupMessagingController {

    private ClassPathXmlApplicationContext context;
    private DatabaseInteractionService dbService;
    private Validator validator;
    private GroupMessage message;

    @FXML private TextArea messageBody;
    @FXML private Text messageEmptyText;
    @FXML private Text messageSent;

    @FXML
    private void sendMessage(){

        initResources();

        if(validator.validateTextFieldNotEmpty(messageBody.getText())){

            //persist the message
            message.setGroupId(Session.adminGroup.getGroupId());
            message.setMessage(messageBody.getText());

            int id = dbService.persistGroupMessage(message);

            if(id > 0){

                messageSent.setVisible(true);
                fadeText(messageSent);

            }else {

                messageEmptyText.setText("Whoops, Something went wrong. Please try again");
                messageEmptyText.setVisible(true);
                fadeText(messageEmptyText);
            }

        }else {

            messageEmptyText.setVisible(true);
            fadeText(messageEmptyText);
        }
    }

    private void fadeText(Text textToFade){

        FadeTransition ft = new FadeTransition(Duration.millis(5000), textToFade);
        ft.setFromValue(1.0);
        ft.setToValue(0.0);

        ft.play();
    }

    private void initResources(){

        context = new ClassPathXmlApplicationContext("ApplicationContext.xml");
        dbService = (DatabaseInteractionService) context.getBean("databaseinteractionservice");
        validator = (Validator) context.getBean("validator");
        message = (GroupMessage) context.getBean("groupmessage");
    }


}

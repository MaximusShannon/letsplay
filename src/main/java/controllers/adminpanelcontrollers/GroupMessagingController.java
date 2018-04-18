package controllers.adminpanelcontrollers;

import functionality.DatabaseInteractionService;
import functionality.Validator;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import javafx.util.Duration;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class GroupMessagingController {

    private ClassPathXmlApplicationContext context;
    private DatabaseInteractionService dbService;
    private Validator validator;

    @FXML private TextArea messageBody;
    @FXML private Text messageEmptyText;

    @FXML
    private void sendMessage(){

        initResources();

        if(validator.validateTextFieldNotEmpty(messageBody.getText())){

            //persist the message
            // with groupid..

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
    }


}

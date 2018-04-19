package controllers.adminpanelcontrollers;

import functionality.DatabaseInteractionService;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import models.Session;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Optional;

public class DeleteGroupController {

    private ClassPathXmlApplicationContext context;
    private DatabaseInteractionService dbService;

    @FXML private Button deleteButton;

    @FXML
    private void deleteGroupFunctionality(){

        //so we need to show a confirmation to confirm again.
        Alert deleteGroupDialog = new Alert(Alert.AlertType.CONFIRMATION);
        deleteGroupDialog.setTitle("Delete Group Confirmation");
        deleteGroupDialog.setHeaderText("Are you sure you want to delete your group?");
        deleteGroupDialog.setContentText("Please choose. . .");

        Stage deleteGroupStage = (Stage) deleteGroupDialog.getDialogPane().getScene().getWindow();
        deleteGroupStage.getIcons().add(new Image("/images/letsplay_icon.png"));

        Optional<ButtonType> result = deleteGroupDialog.showAndWait();

        if(result.get() == ButtonType.OK){

            initResources();


            dbService.deleteGroup(Session.adminGroup.getGroupId());
            Session.resetAdminGroup();



            //we then need to delete the group, and its memberlist
            //then we need to reset the session.
            //exit the view.

            closeResources();

        }else{

            deleteGroupDialog.close();
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

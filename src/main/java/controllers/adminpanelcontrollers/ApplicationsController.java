package controllers.adminpanelcontrollers;

import functionality.DatabaseInteractionService;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Duration;
import models.GroupApplication;
import models.Session;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

public class ApplicationsController implements Initializable {

    private ClassPathXmlApplicationContext context;
    private DatabaseInteractionService dbService;
    private List<GroupApplication> groupApplications;

    @FXML private VBox applicationsVbox;
    @FXML private Text applicationFailed;
    @FXML private Text applicationAccepted;
    @FXML private Text applicationDeclined;
    @FXML private Text applicationCount;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        initResources();

        groupApplications = dbService.fetchAllApplicationsForGroup(Session.adminGroup.getGroupId());

        if(groupApplications.size() > 0){

            applicationCount.setText("Displaying " + groupApplications.size() + " Application(s)");

            for(int i = 0; i < groupApplications.size(); i++){

                Collections.reverse(groupApplications);

                try{

                    displayApplicationInVbox(groupApplications.get(i));
                }catch (Exception e){

                    e.printStackTrace();
                }
            }
        }

        closeResources();
    }

    private void displayApplicationInVbox(GroupApplication application) throws IOException{

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/dynamiclycreatedviews/group_application_card.fxml"));
        AnchorPane node = loader.load();

        GroupApplicationCardController groupApplicationCardController = loader.getController();
        groupApplicationCardController.applicantUsername
                .setText(dbService.fetchGamerForGroupMemberList(application.getGamerId())
                        .getUserName());
        groupApplicationCardController.applicantMessage.setText(application.getMessage());
        groupApplicationCardController.acceptButton.setOnMouseClicked(e ->{

            if(dbService.addMemberToGroupMemberList(
                    Session.adminGroup.getMemberList(),
                    application.getGamerId())){

                applicationAccepted.setVisible(true);
                fadeText(applicationAccepted);

                applicationsVbox.getChildren().remove(node);
                dbService.initFactory();
                dbService.deleteApplication(application.getApplicationId());

            }else {

                applicationFailed.setVisible(true);
                fadeText(applicationFailed);
            }

        });

        groupApplicationCardController.declineButton.setOnMouseClicked(e ->{

            dbService.deleteApplication(application.getApplicationId());

            applicationDeclined.setVisible(true);
            fadeText(applicationDeclined);

            applicationsVbox.getChildren().remove(node);

        });

        applicationsVbox.getChildren().add(node);
    }

    private void fadeText(Text toFade){

        FadeTransition ft = new FadeTransition(Duration.millis(5000), toFade);
        ft.setFromValue(1.0);
        ft.setToValue(0.0);

        ft.play();
    }

    private void initResources(){

        context = new ClassPathXmlApplicationContext("ApplicationContext.xml");
        dbService = (DatabaseInteractionService) context.getBean("databaseinteractionservice");
    }

    private void closeResources(){

        context.close();
    }

}

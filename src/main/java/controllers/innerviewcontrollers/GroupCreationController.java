package controllers.innerviewcontrollers;

import functionality.DatabaseInteractionService;
import functionality.Validator;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.util.Duration;
import models.GamerGroup;
import models.MemberList;
import models.Session;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GroupCreationController implements Initializable {

    private ClassPathXmlApplicationContext context;
    private Validator validator;
    private DatabaseInteractionService dbService;
    private GamerGroup group;
    private MemberList memberList;

    @FXML private AnchorPane injectablePane;
    @FXML private TextField groupName;
    @FXML private TextField comsAddress;
    @FXML private TextArea groupDescription;
    @FXML private ChoiceBox<String> groupVisability;
    @FXML private ChoiceBox<String> gamePlayed;
    @FXML private ChoiceBox<String> comsChannel;
    @FXML private ChoiceBox<String> activityLevel;
    @FXML private ChoiceBox<String> languageSpoken;
    @FXML private Text postFailureText;
    @FXML private Text somethingWentWrong;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        instantiateGroupVisabilityChoiceBox();
        instantiateGroupGamePlayedChoiceBox();
        instantiateGroupComsChannelChoiceBox();
        instantiateGroupActivityLevelChoiceBox();
        instantiateGroupMainLanguageChoiceBox();
    }

    @FXML
    private void startGroupRegistrationProcess(){

        initContext();
        validator = (Validator) context.getBean("validator");
        dbService = new DatabaseInteractionService();

        if((validator.validateTextFieldNotEmpty(groupName.getText())
                && (validator.validateTextFieldNotEmpty(groupDescription.getText())))){

            buildTheGroupBean();
            Integer[] ids = dbService.persistNewGroupAndGroupMemberList(group, memberList);

            if(ids[0] > 0 && ids[1] > 0){

                dbService.closeFactory();
                closeContext();

                try {

                    loadSuccessPane();
                }catch (IOException e){

                    e.printStackTrace();
                }

            }else {

                somethingWentWrong.setVisible(true);
                fadeFailureText(somethingWentWrong);
            }
        }else {

            postFailureText.setVisible(true);
            fadeFailureText(postFailureText);
        }
    }

    private void loadSuccessPane() throws IOException{

        AnchorPane groupCreationSuccess = FXMLLoader.load(getClass().getResource("/view/feedbackviews/group_creation_success.fxml"));
        injectablePane.getChildren().setAll(groupCreationSuccess);
    }

    private void buildTheGroupBean(){

        group = (GamerGroup) context.getBean("group");
        group.setGroupName(groupName.getText());
        group.setGroupDescription(groupDescription.getText());
        group.setGroupVisability(groupVisability.getValue());
        group.setMainGame(gamePlayed.getValue());

        if(comsChannel.getValue() != null){

            group.setComsChannel(comsChannel.getValue());
        }else {
            group.setComsChannel("");
        }
        if(activityLevel.getValue() != null){

            group.setActivityLevel(activityLevel.getValue());
        }else {
            group.setActivityLevel("");
        }
        if(languageSpoken.getValue() != null){

            group.setGroupLanguageSpoken(languageSpoken.getValue());
        }else {
            group.setGroupLanguageSpoken("");
        }

        group.setGroupComsAddress(comsAddress.getText());
        group.setGroupPictureReference("");

        //The groups admin
        group.setAdminId(Session.gamerSession.getId());

        createGroupMemberList();
        group.setMemberList(memberList);

        memberList.setGroup(group);
    }

    private void createGroupMemberList(){

        memberList = (MemberList) context.getBean("memberlist");
        memberList.setM_1_id(Session.gamerSession.getId());
    }

    private void fadeFailureText(Text textSent){

        FadeTransition fadeTransition = new FadeTransition(Duration.millis(5000), textSent);
        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0.0);

        fadeTransition.play();
    }

    private void initContext(){

        context = new ClassPathXmlApplicationContext("ApplicationContext.xml");
    }

    private void closeContext(){

        context.close();
    }

    private void instantiateGroupVisabilityChoiceBox(){

        groupVisability.setItems(FXCollections.observableArrayList("Public", "Private"
        ));
        groupVisability.getSelectionModel().selectFirst();
    }

    private void instantiateGroupGamePlayedChoiceBox(){

        gamePlayed.setItems(FXCollections.observableArrayList("League Of Legends", "FORTNITE",
                "PUBG", "Counter-Strike: Global Offensive",
                "Rust", "DayZ",
                "Rainbow SIX: Siege", "Escape From Tarkov"));
        gamePlayed.getSelectionModel().selectFirst();

    }

    private void instantiateGroupComsChannelChoiceBox(){

        comsChannel.setItems(FXCollections.observableArrayList("TeamSpeak 3", "Discord",
                "Skype", "Other"
        ));
    }

    private void instantiateGroupActivityLevelChoiceBox(){

        activityLevel.setItems(FXCollections.observableArrayList("At least 5 hours weekly",
                "At least 10 hours weekly", "At least 15 hours weekly",
                "At least 20 hours weekly", "25+ hours weekly"));
    }

    private void instantiateGroupMainLanguageChoiceBox(){

        languageSpoken.setItems(FXCollections.observableArrayList("English", "Irish",
                "French", "German",
                "Norwegian", "Dutch",
                "Russian"));
    }

}

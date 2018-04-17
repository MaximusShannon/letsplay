package controllers.innerviewcontrollers;

import controllers.dynamicviewcontrollers.UniqueUserGroupController;
import functionality.DatabaseInteractionService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import models.GamerGroup;
import models.MemberList;
import models.Session;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ProfileUserGroupsController implements Initializable {

    private ClassPathXmlApplicationContext context;
    private DatabaseInteractionService dbService;
    private List<GamerGroup> listOfAllGroups;
    private List<GamerGroup> listOfGroupsLoggedInUserIsMemberOf;
    private List<MemberList> listOfAllMemberLists;

    @FXML private VBox gamersGroupsVbox;
    @FXML private Text groupCount;
    @FXML private AnchorPane injectablePane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        initContext();
        dbService = (DatabaseInteractionService) context.getBean("databaseinteractionservice");

        getAllGroups();
        getAllMemberLists();
        checkMemberLists();

        if(listOfGroupsLoggedInUserIsMemberOf.size() > 0){

            for(int i = 0; i < listOfGroupsLoggedInUserIsMemberOf.size(); i++){

                displayUniqueGroupsForUser(listOfGroupsLoggedInUserIsMemberOf.get(i));
            }

        }else {

            //display text no groups etc.
        }

        closeContext();

        groupCount.setText("Displaying: " + listOfGroupsLoggedInUserIsMemberOf.size() + " group(s)");

    }

    private void displayUniqueGroupsForUser(GamerGroup group){


        try{

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/dynamiclycreatedviews/group_user_display_card.fxml"));
            AnchorPane node = loader.load();

            UniqueUserGroupController uniqueUserGroupController = loader.getController();

            uniqueUserGroupController.groupName.setText(group.getGroupName());
            uniqueUserGroupController.groupGame.setText(group.getMainGame());
            uniqueUserGroupController.groupVisibility.setText(group.getGroupVisability());

            if(!checkIfUserIsAdminOfGroup(group)){

                uniqueUserGroupController.adminText.setVisible(false);
                uniqueUserGroupController.adminImage.setVisible(false);
                uniqueUserGroupController.adminButton.setVisible(false);
                uniqueUserGroupController.memberStatus.setText("Member");

            }else {

                uniqueUserGroupController.memberStatus.setText("Admin");
                uniqueUserGroupController.adminButton.setOnMouseClicked(e ->{

                    System.out.println("OPEN ADMIN PANEL");

                    try{

                        loadAdminPanel();
                    }catch (IOException io){

                        io.printStackTrace();
                    }
                });
            }

            uniqueUserGroupController.leaveGroup.setOnMouseClicked(e ->{

                System.out.println("LEAVE GROUP TO ADD");

                });

            gamersGroupsVbox.getChildren().add(node);

        }catch (Exception e){

            e.printStackTrace();
        }
    }

    private void loadAdminPanel() throws IOException{

        AnchorPane adminPanel = FXMLLoader.load(getClass().getResource("/view/innerviews/inner_innerviews/group_admin_panel.fxml"));
        injectablePane.getChildren().setAll(adminPanel);
    }

    private Boolean checkIfUserIsAdminOfGroup(GamerGroup group){

        return group.getAdminId() == Session.gamerSession.getId();
    }

    private void checkMemberLists(){

        listOfGroupsLoggedInUserIsMemberOf = new ArrayList<>();

        for(int i = 0; i < listOfAllMemberLists.size(); i++){

            if((listOfAllMemberLists.get(i).getM_1_id() == Session.gamerSession.getId())
                    || listOfAllMemberLists.get(i).getM_2_id() == Session.gamerSession.getId()
                    || listOfAllMemberLists.get(i).getM_3_id() == Session.gamerSession.getId()
                    || listOfAllMemberLists.get(i).getM_4_id() == Session.gamerSession.getId()
                    || listOfAllMemberLists.get(i).getM_5_id() == Session.gamerSession.getId()
                    || listOfAllMemberLists.get(i).getM_6_id() == Session.gamerSession.getId()
                    || listOfAllMemberLists.get(i).getM_7_id() == Session.gamerSession.getId()
                    || listOfAllMemberLists.get(i).getM_8_id() == Session.gamerSession.getId()
                    || listOfAllMemberLists.get(i).getM_9_id() == Session.gamerSession.getId()
                    || listOfAllMemberLists.get(i).getM_10_id() == Session.gamerSession.getId()
                    || listOfAllMemberLists.get(i).getM_11_id() == Session.gamerSession.getId()
                    || listOfAllMemberLists.get(i).getM_12_id() == Session.gamerSession.getId()
                    || listOfAllMemberLists.get(i).getM_13_id() == Session.gamerSession.getId()
                    || listOfAllMemberLists.get(i).getM_14_id() == Session.gamerSession.getId()
                    || listOfAllMemberLists.get(i).getM_15_id() == Session.gamerSession.getId()){

                listOfGroupsLoggedInUserIsMemberOf.add(listOfAllMemberLists.get(i).getGroup());
            }
        }
    }

    private void getAllMemberLists(){

        listOfAllMemberLists = dbService.fetchMemberLists();
    }

    private void getAllGroups(){

        listOfAllGroups = dbService.fetchGroupsList();
    }

    private void initContext(){

        context = new ClassPathXmlApplicationContext("ApplicationContext.xml");
    }

    private void closeContext(){

        context.close();
    }


}

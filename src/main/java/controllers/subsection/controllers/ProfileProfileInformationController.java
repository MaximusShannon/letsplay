package controllers.subsection.controllers;

import functionality.DatabaseInteractionService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import models.Session;


import java.net.URL;
import java.util.ResourceBundle;

public class ProfileProfileInformationController implements Initializable {

    private DatabaseInteractionService dbService;

    @FXML
    private Text userName;
    @FXML
    private Text interests;
    @FXML
    private Text firstName;
    @FXML
    private Text surname;
    @FXML
    private Text bio;
    @FXML
    private Text email;
    @FXML
    private Text location;
    @FXML
    private Text postCount;
    @FXML
    private Text commentCount;
    @FXML
    private Text applicationCount;
    @FXML
    private TextField firstnameEdit;
    @FXML
    private TextField surnameEdit;
    @FXML
    private TextField emailEdit;
    @FXML
    private TextField locationEdit;
    @FXML
    private TextField bioEdit;
    @FXML
    private TextField interestsEdit;
    @FXML
    private Button editProfile;
    @FXML
    private Button updateProfile;
    @FXML
    private Button cancelUpdate;



    @Override
    public void initialize(URL location, ResourceBundle resources) {

        initFields();
        setEditFieldsInvisible();
        hideUpdateAndCancel();
    }

    private void initFields(){

        userName.setText(Session.gamerSession.getUserName());
        firstName.setText(Session.gamerSession.getFirstName());
        surname.setText(Session.gamerSession.getSecondName());
        email.setText(Session.gamerSession.getEmail());
        postCount.setText(Integer.toString(Session.gamerSession.getPostCount()));
        commentCount.setText(Integer.toString(Session.gamerSession.getCommentsCount()));
        applicationCount.setText(Integer.toString(Session.gamerSession.getApplicationsCount()));

        if(Session.gamerSession.getInterest().equals("")){

            interests.setText("You can set your interests here, or a quote.");
        }else{

            interests.setText(Session.gamerSession.getInterest());
        }

        if(Session.gamerSession.getBio().equals("")){

            bio.setText("No bio to display yet.");
        }else{

            bio.setText(Session.gamerSession.getBio());
        }

        if(Session.gamerSession.getLocation().equals("")){

            location.setText("No location set, you can set your location to whatever, it doesn't have to be real!");
        }else{

            location.setText(Session.gamerSession.getLocation());
        }
    }

    private void setEditFieldsInvisible(){

        firstnameEdit.setVisible(false);
        surnameEdit.setVisible(false);
        emailEdit.setVisible(false);
        locationEdit.setVisible(false);
        bioEdit.setVisible(false);
        interestsEdit.setVisible(false);
    }

    private void setEditFieldsVisible(){

        firstnameEdit.setVisible(true);
        surnameEdit.setVisible(true);
        emailEdit.setVisible(true);
        interestsEdit.setVisible(true);
        locationEdit.setVisible(true);
        bioEdit.setVisible(true);
    }

    private void setProfileInformationInvisible(){

        firstName.setVisible(false);
        surname.setVisible(false);
        email.setVisible(false);
        interests.setVisible(false);
        location.setVisible(false);
        bio.setVisible(false);
    }

    private void setProfileInformationVisible(){

        firstName.setVisible(true);
        surname.setVisible(true);
        email.setVisible(true);
        interests.setVisible(true);
        location.setVisible(true);
        bio.setVisible(true);
    }

    private void showUpdateAndCancel(){

        cancelUpdate.setVisible(true);
        updateProfile.setVisible(true);
    }

    private void hideEditProfileButton(){

        editProfile.setVisible(false);
    }

    private void showEditButton(){

        editProfile.setVisible(true);
    }

    private void hideUpdateAndCancel(){

        cancelUpdate.setVisible(false);
        updateProfile.setVisible(false);
    }

    @FXML
    private void showEditFields(){

        setEditFieldsVisible();
        setProfileInformationInvisible();

        hideEditProfileButton();
        showUpdateAndCancel();
    }

    @FXML
    private void cancelUpdate(){

        setEditFieldsInvisible();
        showEditButton();
        setProfileInformationVisible();
        showEditButton();
        hideUpdateAndCancel();
    }

    @FXML
    private void updateUser(){

        dbService = new DatabaseInteractionService();

        if(checkFieldsHaveChanged()){

            dbService.updateGamer(dbService.fetchUserForUpdate(), firstnameEdit.getText(),
                    surnameEdit.getText(), emailEdit.getText(),
                    locationEdit.getText(), bioEdit.getText(),
                    interestsEdit.getText());

        }else{

            //notify not changed
        }
    }

    private boolean checkFieldsHaveChanged(){

        boolean changed = false;

        if(!firstnameEdit.getText().equals(Session.gamerSession.getFirstName())
                || !surnameEdit.getText().equals(Session.gamerSession.getSecondName())
                || !emailEdit.getText().equals(Session.gamerSession.getEmail())
                || !location.getText().equals(Session.gamerSession.getLocation())
                || !bio.getText().equals(Session.gamerSession.getBio())
                || !interestsEdit.getText().equals(Session.gamerSession.getInterest())){

            changed = true;
        }
        return changed;
    }
}


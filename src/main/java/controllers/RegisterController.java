package controllers;

import functionality.Validator;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import models.Gamer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.crypto.bcrypt.BCrypt;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

/**
 * Java Class to handle a user trying to register
 * - Author: Max Shannon
 * - Date: 08/03/2018
 * - Version: 0.1
 */

public class RegisterController {

    /**
     * Objects used by this class - bean definitions etc.
     * Lists used for checking if user exists
     */
    private ClassPathXmlApplicationContext context;
    private Validator validator;
    private Gamer gamer;
    private Session session;
    private List<Gamer> gamersFound;
    private SessionFactory factory;

    /**
     * View properties binded.
     */
    @FXML
    private AnchorPane registerStage;
    @FXML
    private TextField firstNameText;
    @FXML
    private TextField secondNameText;
    @FXML
    private TextField emailText;
    @FXML
    private TextField userNameText;
    @FXML
    private PasswordField passwordOne;
    @FXML
    private PasswordField passwordTwo;
    @FXML
    private Text hintMessage;
    @FXML
    private Text registrationSuccesful;
    @FXML
    private Text whoopsMessage;

    /**
     * This method loads the loginView, this is used for the button to go back to the
     * login view, and for when a use is registered completely.
     */
    @FXML
    private void loadLoginView(){

        try{

            Stage registerStage = new Stage();

            Parent root = FXMLLoader.load(getClass().getResource("/view/login.fxml"));
            registerStage.setTitle("Login");
            registerStage.setScene(new Scene(root, 900, 500));
            registerStage.show();

            /*Close this stage - Registration stage*/
            closeStage();

        }catch (Exception e){

            e.printStackTrace();
        }
    }

    /**
     * This method closes the current stage which is the registration stage
     * it does this by getting a handle on the root AnchorPane
     */
    private void closeStage(){
        Stage stage =(Stage) registerStage.getScene().getWindow();
        stage.close();
    }

    /**
     * This method initializes the hibernate SessionFactory
     * then it gets the context from the Spring application context file allowing beans to be created
     *
     * It is called when the user clicks the register button on the view.
     *
     * It then checks to make sure the user hasn't clicked that button without entering any details,
     * t checks that the passwords match to make sure the user hasn't made an error
     * it checks that the email also contains @ and . using the Validator object
     *
     * It then creates a gamer object from the textfields, and initializes default values used later on
     * by the user to tailor their profiles
     *
     * It checks to see if a userName or email exist within the database to stop users creating the same records.
     *
     * If the user doesn't exist it then persists the user to the database, then closes the sessionFactory,
     * loads the loginView
     *
     * and closes this stage.
     */
    @FXML
    private void startRegistrationProcess(){

        initializeFactory();

        context = new ClassPathXmlApplicationContext("ApplicationContext.xml");
        validator = (Validator) context.getBean("validator");

        if(validator.validateRegistrationText(firstNameText.getText(),
                secondNameText.getText(),
                userNameText.getText(),
                emailText.getText())
                && validator.validatePasswordsMatch(passwordOne.getText(), passwordTwo.getText())
                && validator.validateEmail(emailText.getText())){

            createGamerObject();
            fetchAllGamersInDb();

            if(gamersFound != null){

                if(!checkDoesUsernameOrEmailExist()){

                    Integer id = persistNewUser();

                    if(id > 0){

                        registrationSuccesful.setVisible(true);

                        closeFactory();
                        loadLoginView();
                        closeStage();
                    }
                }else {
                    displayUserExistsMessage();
                    resetUsernameAndEmailFields();
                }
            }
        }
        else {
            displayMessageOnView();
        }
    }

    /**
     * If a user enters a username or email that already exists within the database
     * this method will be called to reset the fields so the user doesn't try it again.
     */
    private void resetUsernameAndEmailFields(){

        emailText.setText("");
        userNameText.setText("");
    }

    /**
     * This method displays a message if the username or email exists within the database
     */
    private void displayUserExistsMessage(){

        whoopsMessage.setText("It seems that username or email has been taken.");
        whoopsMessage.setVisible(true);

    }

    /**
     *Persists the user to the database if it is successful it will return a Serializable integer (the primary key)
     * id.
     *
     * @return id which is returned by session.save()
     */
    private int persistNewUser(){

        Integer id;

        session = factory.openSession();
        session.beginTransaction();

        id = (Integer) session.save(gamer);
        session.getTransaction().commit();
        session.close();

        return id;
    }

    /**
     * Instantiate the gamerBean
     */
    private void createGamerObject(){

        gamer = (Gamer) context.getBean("gamer");
        gamer.setFirstName(firstNameText.getText());
        gamer.setSecondName(secondNameText.getText());
        gamer.setEmail(emailText.getText());
        gamer.setUserName(userNameText.getText());
        gamer.setPassword(hashPassword(passwordOne.getText()));
        /*Setting initial values to the gamer object to be updated later by the user*/
        gamer.setBio("");
        gamer.setProfilePictureReference("");
        gamer.setInterest("");
        gamer.setLocation("");
        gamer.setPostCount(0);
        gamer.setCommentsCount(0);
        gamer.setApplicationsCount(0);
        gamer.setLoginCount(0);
        gamer.setProfileVersion(1);
        gamer.setPlayerOnlineStatus(false);
        gamer.setAvailableToPlay(false);
        /*By default auto-matchmaking will be set to false, due to their settings not being configured*/
        gamer.setAutoMatchmaking(false);
    }

    /**
     * Gets a list of all gamers in the database, used to check if a user exists already when trying to
     * create a new user.
     */
    private void fetchAllGamersInDb(){


        session = factory.openSession();

        CriteriaBuilder builder = session.getCriteriaBuilder();

        CriteriaQuery<Gamer> gamers = builder.createQuery(Gamer.class);
        gamers.from(Gamer.class);

        gamersFound = session.createQuery(gamers).getResultList();

        session.close();
    }

    /**
     * Uses spring security to encrypt the password with a generated salt string
     *
     * @param password got from the passwordOne textField, only called after they
     *                 are validated.
     * @return return the encrypted password so it can be persisted to the database.
     */
    private String hashPassword(String password){

        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    /**
     * Displays a message on the view if the user hasn't matched their passwords or left a field empty
     */
    private void displayMessageOnView(){

        hintMessage.setVisible(true);
    }

    /**
     * Checks does a user exist within the database by checking the userName and email against other records
     * @return returns a boolean to see if it existed or not
     */
    private boolean checkDoesUsernameOrEmailExist(){

        boolean exist = false;

        for(int i = 0; i < gamersFound.size(); i++){

            if(gamersFound.get(i).getUserName().equals(userNameText.getText())
                    || gamersFound.get(i).getEmail().equals(emailText.getText())){

                exist = true;
            }
        }
        return exist;
    }

    /**
     * TODO:
     * initialize the sessionFactory
     * Might refactor this to initialize on application startup
     */
    private void initializeFactory(){

        factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Gamer.class)
                .buildSessionFactory();
    }

    /**
     * Close the session factory
     */
    private void closeFactory(){

        factory.close();
    }
}

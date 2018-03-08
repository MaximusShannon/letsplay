package controllers;

import functionality.HibernateUtil;
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


public class RegisterController {

    private ClassPathXmlApplicationContext context;
    private Validator validator;
    private Gamer gamer;
    private Session session;
    private List<Gamer> gamersFound;
    private SessionFactory factory;

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

    @FXML
    private void loadLoginView(){

        try{

            Stage registerStage = new Stage();

            Parent root = FXMLLoader.load(getClass().getResource("/view/login.fxml"));
            registerStage.setTitle("Login");
            registerStage.setScene(new Scene(root, 900, 500));
            registerStage.show();

            closeStage();

        }catch (Exception e){

            e.printStackTrace();
        }
    }

    private void closeStage(){
        Stage stage =(Stage) registerStage.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void startRegistrationProcess(){

        initilizeFactory();

        context = new ClassPathXmlApplicationContext("ApplicationContext.xml");
        validator = (Validator) context.getBean("validator");

        if(validator.validateRegistrationText(firstNameText.getText(),
                secondNameText.getText(),
                userNameText.getText(),
                emailText.getText())
                && validator.validatePasswordsMatch(passwordOne.getText(), passwordTwo.getText())){

            createGamerObject();
            fetchAllGamersInDb();

            if(gamersFound != null){

                if(!checkDoesUsernameOrEmailExist()){

                    Integer id = persistNewUser();

                    if(id > 0){

                        registrationSuccesful.setVisible(true);
                        closeFactory();
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

    private void resetUsernameAndEmailFields(){

        emailText.setText("");
        userNameText.setText("");
    }

    private void displayUserExistsMessage(){

        whoopsMessage.setText("It seems that username or email has been taken.");
        whoopsMessage.setVisible(true);

    }

    private int persistNewUser(){

        Integer id;

        session = factory.openSession();
        session.beginTransaction();

        id = (Integer) session.save(gamer);
        session.getTransaction().commit();
        session.close();

        return id;
    }

    private void createGamerObject(){

        gamer = new Gamer();
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

    private void fetchAllGamersInDb(){


        session = factory.openSession();

        CriteriaBuilder builder = session.getCriteriaBuilder();

        CriteriaQuery<Gamer> gamers = builder.createQuery(Gamer.class);
        gamers.from(Gamer.class);

        gamersFound = session.createQuery(gamers).getResultList();

        session.close();
    }

    private String hashPassword(String password){

        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    private void displayMessageOnView(){

        hintMessage.setVisible(true);
    }

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

    private void initilizeFactory(){

        factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Gamer.class)
                .buildSessionFactory();
    }

    private void closeFactory(){

        factory.close();
    }
}

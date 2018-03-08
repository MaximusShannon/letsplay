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
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class RegisterController {

    private ClassPathXmlApplicationContext context;
    private Validator validator;
    private Gamer gamer;

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

        context = new ClassPathXmlApplicationContext("ApplicationContext.xml");
        validator = (Validator) context.getBean("validator");

        if(validator.validateRegistrationText(firstNameText.getText(),
                secondNameText.getText(),
                userNameText.getText(),
                emailText.getText())){

            /*
            * Fields Validated
            * - create the object
            * - check does username and email exists
            * - if(not) persist user
            * */

            gamer = new Gamer();



        }
        else {
            displayMessageOnView();
        }
    }

    private void displayMessageOnView(){

        hintMessage.setVisible(true);
    }

}

package functionality;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("/view/frameviews/login.fxml"));
        primaryStage.setTitle("LetsPlay");
        primaryStage.getIcons().add(new Image("/images/letsplay_icon.png"));
        primaryStage.setScene(new Scene(root, 900 , 500));
        primaryStage.setResizable(false);
        primaryStage.sizeToScene();
        primaryStage.show();


    }
    public static void main(String[] args) {
        launch(args);

    }
}

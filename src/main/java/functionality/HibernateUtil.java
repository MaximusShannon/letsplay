package functionality;

import javafx.fxml.Initializable;
import models.Gamer;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.net.URL;
import java.util.ResourceBundle;

public class HibernateUtil implements Initializable {

    public static SessionFactory factory;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Gamer.class)
                .buildSessionFactory();
    }
}

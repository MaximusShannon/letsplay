package functionality;

import models.Gamer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class HibernateUtil {

    public static SessionFactory factory;

    public HibernateUtil(){
        factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Gamer.class)
                .buildSessionFactory();
    }

    public static Session returnSession(){

        return factory.openSession();
    }

}

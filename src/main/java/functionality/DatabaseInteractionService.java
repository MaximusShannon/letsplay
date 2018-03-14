package functionality;

import models.Gamer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class DatabaseInteractionService {

    private ClassPathXmlApplicationContext context;
    private Session session;
    private SessionFactory sessionFactory;
    private List<Gamer> gamersFound;

    public DatabaseInteractionService(){

        initFactory();
    }

    /**
     * Get a list of user's from the gamer table in the database.
     */
    public List<Gamer> fetchGamerList(){

        session = sessionFactory.openSession();

        CriteriaBuilder builder = session.getCriteriaBuilder();

        CriteriaQuery<Gamer> gamers = builder.createQuery(Gamer.class);
        gamers.from(Gamer.class);

        gamersFound = session.createQuery(gamers).getResultList();

        session.close();

        return gamersFound;

    }

    private void initFactory(){

        sessionFactory = new Configuration()
                .configure()
                .addAnnotatedClass(Gamer.class)
                .buildSessionFactory();
    }

    private void closeFactory(){

        sessionFactory.close();
    }
}

package functionality;

import models.Gamer;
import models.Post;
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
    private List<Post> postsFound;

    public DatabaseInteractionService(){

        initFactory();
    }

    public Gamer fetchUserForUpdate(){

        session = sessionFactory.openSession();

        return session.load(Gamer.class, models.Session.gamerSession.getId());
    }

    public void updateGamer(){


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

    public List<Post> fetchPostList(){

        session = sessionFactory.openSession();

        CriteriaBuilder builder = session.getCriteriaBuilder();

        CriteriaQuery<Post> posts = builder.createQuery(Post.class);
        posts.from(Post.class);

        postsFound = session.createQuery(posts).getResultList();

        session.close();

        return postsFound;

    }

    private void initFactory(){

        sessionFactory = new Configuration()
                .configure()
                .addAnnotatedClass(Gamer.class)
                .buildSessionFactory();
    }

    public void closeFactory(){

        sessionFactory.close();
    }
}

package functionality;

import models.Post;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class PostFunctionalityService {

    private Session session;
    private SessionFactory sessionFactory;

    public PostFunctionalityService(){

        initFactory();
    }

    /**
     * Duplicate method in register controller TODO: Make a unified solution
     *
     * Pass in a post which is created in the create post controller
     * return an integer Id, if id is null then post creation failed.
     * @param post
     * @return
     */
    public int persistNewPost(Post post){

        Integer id;

        session = sessionFactory.openSession();
        session.beginTransaction();

        id = (Integer) session.save(post);
        session.getTransaction().commit();
        session.close();

        return id;

    }

    /**
     * Initialize the session factory
     */
    private void initFactory(){

        sessionFactory = new Configuration()
                .configure()
                .addAnnotatedClass(Post.class)
                .buildSessionFactory();
    }
    /**
     * Close the session factory
     */
    public void closeFactory(){

        sessionFactory.close();
    }
}

package functionality;

import javafx.geometry.Pos;
import models.Gamer;
import models.Notification;
import models.Post;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.spi.ServiceException;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class DatabaseInteractionService {

    private ClassPathXmlApplicationContext context;
    private Session session;
    private SessionFactory sessionFactory;
    private List<Gamer> gamersFound;
    private List<Post> postsFound;
    private Notification notification;

    public DatabaseInteractionService(){

        initFactory();
    }

    public Gamer fetchUserForUpdate(){

        session = sessionFactory.openSession();

        return session.load(Gamer.class, models.Session.gamerSession.getId());
    }

    public Post fetchPostForUpdate(){

        session = sessionFactory.openSession();

        return session.load(Post.class, models.Session.updateablePost.getId());
    }

    public void updateGamer(Gamer gamer, String firstName,
                            String surname, String email,
                            String location, String bio,
                            String interests){

        Transaction tx = session.beginTransaction();

        gamer.setFirstName(firstName);
        gamer.setSecondName(surname);
        gamer.setEmail(email);
        gamer.setLocation(location);
        gamer.setBio(bio);
        gamer.setInterest(interests);
        gamer.setProfileVersion(gamer.getProfileVersion() + 1);

        session.update(gamer);
        tx.commit();

        updateSession(gamer);

        session.close();
        closeFactory();
    }

    public void updatePost(Post post, String postTitle,
                           String ageRange, String postDesc,
                           String postTags, String langSpoken,
                           String gamePlayed, String timeZone,
                           boolean isMicAccepted, boolean isCompetitiveAccept,
                           boolean isCasualAccepted, boolean isFemalesAccepted,
                           boolean isMalesAccepted){

        Transaction tx = session.beginTransaction();

        post.setPostTitle(postTitle);
        post.setAgeRange(ageRange);
        post.setPostDescription(postDesc);
        post.setPostTags(postTags);
        post.setLanguageSpoken(langSpoken);
        post.setGamePlayed(gamePlayed);
        post.setTimeZone(timeZone);
        post.setMicrophoneRequired(isMicAccepted);
        post.setCompetitivePlayers(isCompetitiveAccept);
        post.setCasualPlayers(isCasualAccepted);
        post.setAcceptFemales(isFemalesAccepted);
        post.setAcceptMales(isMalesAccepted);

        session.update(post);
        tx.commit();

        session.close();
        closeFactory();

    }

    private void updateSession(Gamer gamer){

        models.Session.resetSession();
        models.Session.gamerSession = gamer;
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

        try{
            sessionFactory = new Configuration()
                    .configure()
                    .addAnnotatedClass(Gamer.class)
                    .buildSessionFactory();

        }catch (ServiceException e){

            System.out.println("Caught");

        }

    }

    public void closeFactory(){

        sessionFactory.close();
    }

    public ArrayList<Post> filterPostsByUserId(){

        ArrayList<Post> userPosts = new ArrayList<>();
        for(int i = 0; i < postsFound.size(); i++){

            if(postsFound.get(i).getGamer().getId() == models.Session.gamerSession.getId()){

                userPosts.add(postsFound.get(i));
            }
        }

        return userPosts;
    }

    public Notification deletePost(int postId){

        session = sessionFactory.openSession();

        Transaction tx = session.beginTransaction();

        Post toDelete = session.load(Post.class, postId);
        session.delete(toDelete);

        session.flush();
        tx.commit();

        session.close();
        setDeleteNotification();

        return notification;
    }

    private void setDeleteNotification(){

        Timestamp newStamp = new Timestamp(System.currentTimeMillis());

        notification = new Notification();
        notification.setMessage("Your post has been deleted");
        notification.setTimestamp(newStamp);
    }

}

package functionality;

import javafx.geometry.Pos;
import models.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.spi.ServiceException;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.lang.reflect.Member;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class DatabaseInteractionService {

    private ClassPathXmlApplicationContext context;
    private Session session;
    private SessionFactory sessionFactory;
    private List<Gamer> gamersFound;
    private List<Post> postsFound;
    private List<GamerGroup> groupsFound;
    private List<MemberList> memberListsFound;
    private List<GroupApplication> groupApplications;
    private List<PostComment> postComments;
    private Notification notification;

    public DatabaseInteractionService(){

        initFactory();
    }

    public Gamer fetchUserForUpdate(){

        session = sessionFactory.openSession();

        return session.load(Gamer.class, models.Session.gamerSession.getId());
    }

    public GamerGroup fetchGamerGroupForUpdate(){

        session = sessionFactory.openSession();

        return session.load(GamerGroup.class, models.Session.adminGroup.getGroupId());
    }

    public Gamer fetchGamerForGroupMemberList(int gamerId){

        session = sessionFactory.openSession();

        return session.load(Gamer.class, gamerId);
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

    public void updateGamerCommentCount(Gamer gamer){

        Transaction tx = session.beginTransaction();
        gamer.setCommentsCount(gamer.getCommentsCount() + 1);

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

    public GamerGroup updateGroup(GamerGroup group, String groupName,
                            String groupDesc, String groupVisability,
                            String gamePlayed, String comsChannel,
                            String activityLevel, String langSpoken,
                            String comsAddress){

        Transaction tx = session.beginTransaction();

        group.setGroupName(groupName);
        group.setGroupDescription(groupDesc);
        group.setGroupVisability(groupVisability);
        group.setMainGame(gamePlayed);
        group.setComsChannel(comsChannel);
        group.setActivityLevel(activityLevel);
        group.setGroupLanguageSpoken(langSpoken);
        group.setGroupComsAddress(comsAddress);

        session.update(group);
        tx.commit();

        session.close();
        closeFactory();

        return group;
    }

    public boolean addMemberToGroupMemberList(MemberList memberList, int idToAdd){

        boolean memberListAllowingNewMembers = false;

        Transaction tx = session.beginTransaction();

        if(memberList.getM_2_id() == 0){

            memberList.setM_2_id(idToAdd);
            memberListAllowingNewMembers = true;
        }
        if(memberList.getM_3_id() == 0 && !memberListAllowingNewMembers){

            memberList.setM_3_id(idToAdd);
            memberListAllowingNewMembers = true;
        }
        if(memberList.getM_4_id() == 0 && !memberListAllowingNewMembers){

            memberList.setM_4_id(idToAdd);
            memberListAllowingNewMembers = true;
        }
        if(memberList.getM_5_id() != 0 && !memberListAllowingNewMembers){

            memberList.setM_5_id(idToAdd);
            memberListAllowingNewMembers = true;
        }
        if(memberList.getM_6_id() != 0 && !memberListAllowingNewMembers){

            memberList.setM_6_id(idToAdd);
            memberListAllowingNewMembers = true;
        }
        if(memberList.getM_7_id() != 0 && !memberListAllowingNewMembers){

            memberList.setM_7_id(idToAdd);
            memberListAllowingNewMembers = true;
        }
        if(memberList.getM_8_id() != 0 && !memberListAllowingNewMembers){

            memberList.setM_8_id(idToAdd);
            memberListAllowingNewMembers = true;
        }
        if(memberList.getM_9_id() != 0 && !memberListAllowingNewMembers){

            memberList.setM_9_id(idToAdd);
            memberListAllowingNewMembers = true;
        }
        if(memberList.getM_10_id() != 0 && !memberListAllowingNewMembers){

            memberList.setM_10_id(idToAdd);
            memberListAllowingNewMembers = true;
        }
        if(memberList.getM_11_id() != 0 && !memberListAllowingNewMembers){

            memberList.setM_11_id(idToAdd);
            memberListAllowingNewMembers = true;
        }
        if(memberList.getM_12_id() != 0 && !memberListAllowingNewMembers){

            memberList.setM_12_id(idToAdd);
            memberListAllowingNewMembers = true;
        }
        if(memberList.getM_13_id() != 0 && !memberListAllowingNewMembers){

            memberList.setM_13_id(idToAdd);
            memberListAllowingNewMembers = true;
        }
        if(memberList.getM_14_id() != 0 && !memberListAllowingNewMembers){

            memberList.setM_14_id(idToAdd);
            memberListAllowingNewMembers = true;
        }
        if(memberList.getM_15_id() != 0 && !memberListAllowingNewMembers){

            memberList.setM_15_id(idToAdd);
            memberListAllowingNewMembers = true;
        }

        if(memberListAllowingNewMembers){

            session.update(memberList);
            tx.commit();

            session.close();
            closeFactory();
        }

        return memberListAllowingNewMembers;
    }

    public void removeMemberFromGroupMemberList(MemberList memberList, int memberId){

        Transaction tx = session.beginTransaction();

        if(memberList.getM_2_id() == memberId){

            memberList.setM_2_id(0);
        }
        if(memberList.getM_3_id() == memberId){

            memberList.setM_3_id(0);
        }
        if(memberList.getM_4_id() == memberId){

            memberList.setM_4_id(0);
        }
        if(memberList.getM_5_id() == memberId){

            memberList.setM_5_id(0);
        }
        if(memberList.getM_6_id() == memberId){

            memberList.setM_6_id(0);
        }
        if(memberList.getM_7_id() == memberId){

            memberList.setM_7_id(0);
        }
        if(memberList.getM_8_id() == memberId){

            memberList.setM_8_id(0);
        }
        if(memberList.getM_9_id() == memberId){

            memberList.setM_9_id(0);
        }
        if(memberList.getM_10_id() == memberId){

            memberList.setM_10_id(0);
        }
        if(memberList.getM_11_id() == memberId){

            memberList.setM_11_id(0);
        }
        if(memberList.getM_12_id() == memberId){

            memberList.setM_12_id(0);
        }
        if(memberList.getM_13_id() == memberId){

            memberList.setM_13_id(0);
        }
        if(memberList.getM_14_id() == memberId){

            memberList.setM_14_id(0);
        }
        if(memberList.getM_15_id() == memberId){

            memberList.setM_15_id(0);
        }

        session.update(memberList);
        tx.commit();

        session.close();
        closeFactory();
    }

    private void updateSession(Gamer gamer){

        models.Session.resetSession();
        models.Session.gamerSession = gamer;
    }

    public Integer[] persistNewGroupAndGroupMemberList(GamerGroup group, MemberList memberList){

        Integer[] ids = new Integer[2];

        session = sessionFactory.openSession();
        session.beginTransaction();

        ids[0] = (Integer) session.save(group);
        ids[1] = (Integer) session.save(memberList);

        session.getTransaction().commit();
        session.close();

        return ids;
    }

    public Integer persistGroupMessage(GroupMessage message){

        Integer id;

        session = sessionFactory.openSession();
        session.beginTransaction();

        id = (Integer) session.save(message);

        session.getTransaction().commit();
        session.close();

        return id;

    }

    public Integer persistPostComment(PostComment comment){

        session = sessionFactory.openSession();
        session.beginTransaction();

        Integer id = (Integer) session.save(comment);

        session.getTransaction().commit();
        session.close();

        return id;
    }

    public Integer persistGroupApplication(GroupApplication application){

        session = sessionFactory.openSession();
        session.beginTransaction();

        Integer id = (Integer) session.save(application);

        session.getTransaction().commit();
        session.close();

        return id;
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

    public List<GroupApplication> fetchAllApplicationsForGroup(int id){

        session = sessionFactory.openSession();

        CriteriaBuilder builder = session.getCriteriaBuilder();

        CriteriaQuery<GroupApplication> applications = builder.createQuery(GroupApplication.class);
        applications.from(GroupApplication.class);

        groupApplications = session.createQuery(applications).getResultList();

        session.close();

        return filterApplicationsToGroup(groupApplications, id);
    }

    private List<GroupApplication> filterApplicationsToGroup(List<GroupApplication> groupApplications, int id){

        for(int i = 0; i < groupApplications.size(); i++){

            if(groupApplications.get(i).getGamerGroup().getGroupId() != id){

                groupApplications.remove(i);
            }
        }

        return groupApplications;
    }

    public List<GamerGroup> fetchGroupsList(){

        session = sessionFactory.openSession();

        CriteriaBuilder builder = session.getCriteriaBuilder();

        CriteriaQuery<GamerGroup> groups = builder.createQuery(GamerGroup.class);
        groups.from(GamerGroup.class);

        groupsFound = session.createQuery(groups).getResultList();

        session.close();

        return groupsFound;

    }

    public List<MemberList> fetchMemberLists(){

        session = sessionFactory.openSession();

        CriteriaBuilder builder = session.getCriteriaBuilder();

        CriteriaQuery<MemberList> memberLists = builder.createQuery(MemberList.class);
        memberLists.from(MemberList.class);

        memberListsFound = session.createQuery(memberLists).getResultList();

        session.close();

        return memberListsFound;
    }

    public List<PostComment> fetchPostCommentList(){

        session = sessionFactory.openSession();

        CriteriaBuilder builder = session.getCriteriaBuilder();

        CriteriaQuery<PostComment> commentsList = builder.createQuery(PostComment.class);
        commentsList.from(PostComment.class);

        postComments = session.createQuery(commentsList).getResultList();

        session.close();

        return postComments;
    }

    public void initFactory(){

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

    public void deleteApplication(int applicationId){

        session = sessionFactory.openSession();

        Transaction tx = session.beginTransaction();

        GroupApplication toDelete = session.load(GroupApplication.class, applicationId);
        session.delete(toDelete);

        session.flush();
        tx.commit();

        session.close();

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

    public void deleteGroup(int groupId){

        session = sessionFactory.openSession();

        Transaction tx = session.beginTransaction();

        GamerGroup toDelete = session.load(GamerGroup.class, groupId);
        session.delete(toDelete);

        session.flush();
        tx.commit();

        session.close();
    }

    public void deleteComment(int commentId){

        session = sessionFactory.openSession();

        Transaction tx = session.beginTransaction();

        PostComment toDelete = session.load(PostComment.class, commentId);
        session.delete(toDelete);

        session.flush();
        tx.commit();

        session.close();
    }

    private void setDeleteNotification(){

        Timestamp newStamp = new Timestamp(System.currentTimeMillis());

        notification = new Notification();
        notification.setMessage("Your post has been deleted");
        notification.setTimestamp(newStamp);
    }

}

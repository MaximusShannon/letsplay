package functionality;

import models.Gamer;
import models.GamerGroup;
import models.Invitation;
import models.MemberList;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.xml.crypto.Data;
import java.util.List;

public class InvitationService {

    private Session session;
    private SessionFactory sessionFactory;
    private ClassPathXmlApplicationContext context;
    private DatabaseInteractionService dbService;
    private List<Gamer> gamerList;
    private List<Invitation> allInvitations;
    private List<GamerGroup> groupList;

    public void initResources(){

        context = new ClassPathXmlApplicationContext("ApplicationContext.xml");
        dbService = (DatabaseInteractionService) context.getBean("databaseinteractionservice");

        try{

            sessionFactory = new Configuration()
                    .configure()
                    .addAnnotatedClass(Gamer.class)
                    .buildSessionFactory();

        }catch (Exception e){

            e.printStackTrace();
        }
    }

    public void closeResources(){

        context.close();
        sessionFactory.close();
    }

    public int checkDoesGamerExist(String username){

        gamerList = dbService.fetchGamerList();

        for(int i = 0; i < gamerList.size(); i++){

            if(gamerList.get(i).getUserName().equals(username)){

                return gamerList.get(i).getId();
            }
        }

        return 0;
    }

    public boolean checkIsGamerAMemberOfGroup(int id){

        return models.Session.adminGroup.getMemberList().getM_1_id() == id
                || models.Session.adminGroup.getMemberList().getM_2_id() == id
                || models.Session.adminGroup.getMemberList().getM_3_id() == id
                || models.Session.adminGroup.getMemberList().getM_4_id() == id
                || models.Session.adminGroup.getMemberList().getM_5_id() == id
                || models.Session.adminGroup.getMemberList().getM_6_id() == id
                || models.Session.adminGroup.getMemberList().getM_7_id() == id
                || models.Session.adminGroup.getMemberList().getM_8_id() == id
                || models.Session.adminGroup.getMemberList().getM_9_id() == id
                || models.Session.adminGroup.getMemberList().getM_10_id() == id
                || models.Session.adminGroup.getMemberList().getM_11_id() == id
                || models.Session.adminGroup.getMemberList().getM_12_id() == id
                || models.Session.adminGroup.getMemberList().getM_13_id() == id
                || models.Session.adminGroup.getMemberList().getM_14_id() == id
                || models.Session.adminGroup.getMemberList().getM_5_id() == id;

    }

    public Gamer fetchGamerForInvitation(int id){

        session = sessionFactory.openSession();

        return session.load(Gamer.class, id);
    }

    public int persistInvitation(Invitation invitationSent){

        Integer id = 0;

        session = sessionFactory.openSession();
        session.beginTransaction();

        id = (Integer) session.save(invitationSent);

        session.getTransaction().commit();
        session.close();

        return id;
    }


}

package functionality;

import models.Gamer;
import models.MatchmakerRequirement;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.spi.ServiceException;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class MatchmakerPersistenceService {

    private Session session;
    private SessionFactory sessionFactory;
    private List<MatchmakerRequirement> allPlayersRequirements;

    public MatchmakerPersistenceService(){

        initFactory();
    }

    private void initFactory(){

        try{

            sessionFactory = new Configuration()
                    .configure()
                    .addAnnotatedClass(Gamer.class)
                    .buildSessionFactory();

        }catch (ServiceException e){

            e.printStackTrace();
        }

    }

    private void closeFactory(){

        sessionFactory.close();
    }

    public void fetchAllRequirementObjects(){

        session = sessionFactory.openSession();

        CriteriaBuilder builder = session.getCriteriaBuilder();

        CriteriaQuery<MatchmakerRequirement> allRequirements = builder.createQuery(MatchmakerRequirement.class);
        allRequirements.from(MatchmakerRequirement.class);

        allPlayersRequirements = session.createQuery(allRequirements).getResultList();

        session.close();
    }

    public MatchmakerRequirement checkDoesUserHaveRequirement(int loggedInUsersId){

        for(int i = 0; i < allPlayersRequirements.size(); i++){

            if(allPlayersRequirements.get(i).getGamer().getId() == loggedInUsersId){

                return allPlayersRequirements.get(i);
            }
        }
        return null;
    }


    public Integer persistNewRequirement(MatchmakerRequirement mmRequirement){

        session = sessionFactory.openSession();
        session.beginTransaction();

        Integer id = (Integer) session.save(mmRequirement);

        session.getTransaction().commit();
        session.close();

        return id;

    }

    public MatchmakerRequirement fetchRequirementForUpdate(int requirementId){

        session = sessionFactory.openSession();

        return session.load(MatchmakerRequirement.class, requirementId);
    }

    public void updateUsersMatchmakerRequirement(MatchmakerRequirement mmRequirement, boolean atpCb,
                                                 boolean activeMatchmakingCb, boolean micRequiredCb,
                                                 boolean acceptMalesCb, boolean acceptFemalesCb,
                                                 boolean competitivePlayersCb, boolean casualPlayersCb,
                                                 String languageSpoken, String gamePlayed, int ageRangeSlider){


        Transaction tx = session.beginTransaction();

        mmRequirement.setAvailableToPlayFlag(atpCb);
        mmRequirement.setMatchmakerActiviationFlag(activeMatchmakingCb);
        mmRequirement.setDoesGamerRequireMicrophone(micRequiredCb);
        mmRequirement.setDoesGamerRequireMales(acceptMalesCb);
        mmRequirement.setDoesGamerRequireFemales(acceptFemalesCb);
        mmRequirement.setDoesGamerRequireCompetitiveGamers(competitivePlayersCb);
        mmRequirement.setDoesGamerRequireCasualPlayers(casualPlayersCb);
        mmRequirement.setRequiredLanguage(languageSpoken);
        mmRequirement.setRequiredGame(gamePlayed);
        mmRequirement.setGamersMinimumAgeRequirement(ageRangeSlider);

        session.update(mmRequirement);
        tx.commit();

        session.close();
        closeFactory();
    }
}

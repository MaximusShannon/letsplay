package models;

import functionality.DatabaseInteractionService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Matchmaker {

    private ClassPathXmlApplicationContext context;
    private DatabaseInteractionService dbService;
    private List<Gamer> allGamers;
    private List<Gamer> matchedGamers;
    private List<Post> allPosts;
    private List<Post> mostRecentPosts;
    private List<GamerGroup> allGroups;
    private ArrayList<GamerGroup> matchedGroup;
    private List<MatchmakerRequirement> allPlayersRequirements;
    private MatchmakerRequirement gamersRequirement;

    private void initResources(){

        context = new ClassPathXmlApplicationContext("ApplicationContext.xml");
        dbService = (DatabaseInteractionService) context.getBean("databaseinteractionservice");
    }

    public void run(){

        initResources();
        fetchInitialData();
    }

    private void fetchInitialData(){

        allGamers = dbService.fetchGamerList();
        allPosts = dbService.fetchPostList();
        allGroups = dbService.fetchGroupsList();
        allPlayersRequirements = dbService.fetchAllRequirements();
    }

    public List<Gamer> checkForGamersThatMatch(){

        matchedGamers = new ArrayList<>();
        //remove the current gamer
        for (int i = 0; i < allPlayersRequirements.size(); i++){

            if(allPlayersRequirements.get(i).getGamer().getId() == Session.gamerSession.getId()){

                gamersRequirement = allPlayersRequirements.get(i);
                allPlayersRequirements.remove(i);
                break;
            }
        }

        // because we are matching requirements remove the logged in users requirement.
        for (int i = 0; i < allPlayersRequirements.size(); i++){

            if(allPlayersRequirements.get(i).isMatchmakerActiviationFlag() && gamersRequirement.isMatchmakerActiviationFlag()){

                if(allPlayersRequirements.get(i).isAvailableToPlayFlag() == gamersRequirement.isAvailableToPlayFlag()
                        && allPlayersRequirements.get(i).isDoesGamerRequireMicrophone() == gamersRequirement.isDoesGamerRequireMicrophone()
                        && allPlayersRequirements.get(i).isDoesGamerRequireCompetitiveGamers() == gamersRequirement.isDoesGamerRequireCompetitiveGamers()
                        && allPlayersRequirements.get(i).isDoesGamerRequireCasualPlayers() == gamersRequirement.isDoesGamerRequireCasualPlayers()
                        && allPlayersRequirements.get(i).isDoesGamerRequireMales() == gamersRequirement.isDoesGamerRequireMales()
                        && allPlayersRequirements.get(i).isDoesGamerRequireFemales() == gamersRequirement.isDoesGamerRequireFemales()
                        && allPlayersRequirements.get(i).getRequiredGame().equals(gamersRequirement.getRequiredGame())
                        && allPlayersRequirements.get(i).getRequiredLanguage().equals(gamersRequirement.getRequiredLanguage())
                        && allPlayersRequirements.get(i).getGamersMinimumAgeRequirement() >= gamersRequirement.getGamersMinimumAgeRequirement()){

                    matchedGamers.add(gamersRequirement.getGamer());
                    System.out.println("MATCHED");
                }
            }
        }

        return matchedGamers;
    }

    public List<Post> fetchMostRecentPosts(){

        mostRecentPosts = new ArrayList<>();

        if(allPosts.size() < 5){

            return allPosts;
        }else{

            Collections.reverse(allPosts);

            for(int i = 0; i < 5; i++){

                mostRecentPosts.add(allPosts.get(i));
            }
        }

        return mostRecentPosts;
    }

    public ArrayList<GamerGroup> fetchMatchingGroups(){

        matchedGroup = new ArrayList<>();
        ArrayList<GamerGroup> filteredList;

        if(gamersRequirement != null){

            for(int i = 0; i < allGroups.size(); i++){

                if(allGroups.get(i).getMainGame().equals(gamersRequirement.getRequiredGame())
                        && allGroups.get(i).getGroupLanguageSpoken().equals(gamersRequirement.getRequiredLanguage())){

                    matchedGroup.add(allGroups.get(i));
                }
            }

            filteredList = filterOutGroupsGamerIsAlreadyAMemberOf(matchedGroup);
            return filteredList;

        }else{

            return null;
        }
    }

    private ArrayList<GamerGroup> filterOutGroupsGamerIsAlreadyAMemberOf(ArrayList<GamerGroup> matchedGroup){

        for(int i = 0; i < matchedGroup.size(); i++){

            if(checkIsAMember(matchedGroup.get(i).getMemberList())){

                matchedGroup.remove(i);
            }
        }

        return matchedGroup;

    }

    private boolean checkIsAMember(MemberList groupsMemberList){

        if(groupsMemberList.getM_1_id() == Session.gamerSession.getId()){

            return true;
        }
        if(groupsMemberList.getM_2_id() == Session.gamerSession.getId()){

            return true;
        }
        if(groupsMemberList.getM_3_id() == Session.gamerSession.getId()){

            return true;
        }
        if(groupsMemberList.getM_4_id() == Session.gamerSession.getId()){

            return true;
        }
        if(groupsMemberList.getM_5_id() == Session.gamerSession.getId()){

            return true;
        }
        if(groupsMemberList.getM_6_id() == Session.gamerSession.getId()){

            return true;
        }
        if(groupsMemberList.getM_7_id() == Session.gamerSession.getId()){

            return true;
        }
        if(groupsMemberList.getM_8_id() == Session.gamerSession.getId()){

            return true;
        }
        if(groupsMemberList.getM_9_id() == Session.gamerSession.getId()){

            return true;
        }
        if(groupsMemberList.getM_10_id() == Session.gamerSession.getId()){

            return true;
        }
        if(groupsMemberList.getM_11_id() == Session.gamerSession.getId()){

            return true;
        }
        if(groupsMemberList.getM_12_id() == Session.gamerSession.getId()){

            return true;
        }
        if(groupsMemberList.getM_13_id() == Session.gamerSession.getId()){

            return true;
        }
        if(groupsMemberList.getM_14_id() == Session.gamerSession.getId()){

            return true;
        }
        if(groupsMemberList.getM_15_id() == Session.gamerSession.getId()){

            return true;
        }

        return false;
    }

    public boolean checkDoesUserHaveARequirement(){

        for(int i = 0; i < allPlayersRequirements.size(); i++){

            if(allPlayersRequirements.get(i).getGamer().getId() == Session.gamerSession.getId()){

                return true;
            }
        }
        return false;
    }
}

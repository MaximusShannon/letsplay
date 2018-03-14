package functionality;

import models.Gamer;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.util.List;

public class Authentication {

    private List<Gamer> gamersList;
    private Gamer gamer;
    private DatabaseInteractionService dbService;

    public Authentication(){

        getGamerList();
    }

    private void getGamerList(){

        dbService = new DatabaseInteractionService();
        gamersList = dbService.fetchGamerList();
    }

    public boolean checkDoesUsernameExist(String userNameRequested){

        boolean exists = false;

        if(gamersList != null){

            for (int i =  0; i < gamersList.size(); i++){

                if(gamersList.get(i).getUserName()
                        .equals(userNameRequested)){

                    exists = true;
                }
            }
        }
        return exists;
    }

    public Gamer fetchExistingGamer(String userNameRequested){

        for(int i = 0; i < gamersList.size(); i++){

            if(gamersList.get(i).getUserName().equals(userNameRequested)){

                gamer = gamersList.get(i);
            }
        }
        return gamer;
    }

    public boolean checkPasswordsMatch(String passwordRequested, String hashedPasswordFromUserRetreived){

        return BCrypt.checkpw(passwordRequested, hashedPasswordFromUserRetreived);
    }
}

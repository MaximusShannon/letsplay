package models;

import java.util.ArrayList;

public class Session {

    public static Gamer gamerSession;
    public static ArrayList<Notification> notifcations;

    public static void resetSession(){

        gamerSession = null;
    }
}

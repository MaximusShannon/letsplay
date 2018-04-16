package models;

import java.util.ArrayList;

public class Session {

    public static Gamer gamerSession;
    public static ArrayList<Notification> notifcations = new ArrayList<>();
    public static Post updateablePost;
    public static GamerGroup innerViewGamerGroup;
    public static MemberList innerViewGamerGroupMemberList;

    public static void resetSession(){

        gamerSession = null;
    }

    public static void resetInnerViewGamerGroup(){

        innerViewGamerGroup = null;
    }

    public static void resetInnerViewGamerGroupMemberList(){

        innerViewGamerGroupMemberList = null;
    }
}

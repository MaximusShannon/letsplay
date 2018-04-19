package models;

import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Session {

    public static Gamer gamerSession;
    public static ArrayList<Notification> notifcations = new ArrayList<>();
    public static Post updateablePost;
    public static GamerGroup innerViewGamerGroup;
    public static MemberList innerViewGamerGroupMemberList;
    public static GamerGroup adminGroup;
    public static AnchorPane adminGroupView;
    public static Post innerViewPost;
    public static Stage thisStage;

    public static void resetSession(){

        gamerSession = null;
    }

    public static void resetInnerViewPost(){

        innerViewPost = null;
    }

    public static void resetInnerViewGamerGroup(){

        innerViewGamerGroup = null;
    }

    public static void resetInnerViewGamerGroupMemberList(){

        innerViewGamerGroupMemberList = null;
    }

    public static void resetAdminGroup(){

        adminGroup = null;
    }

    public static void resetAdminGroupView(){

        adminGroupView = null;
    }
}

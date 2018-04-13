package models;

import javafx.fxml.FXML;

import javax.persistence.*;

@Entity
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int groupId;
    private String groupName;
    private String groupDescription;
    private String groupVisability; // Public or private
    private String mainGame;
    private String comsChannel;
    private String activityLevel;
    private String groupLanguageSpoken;
    private String groupComsAddress;
    private String groupPictureReference;

    //Admin
    @OneToOne
    private Gamer gamer;
    @OneToOne
    //memberlist

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupDescription() {
        return groupDescription;
    }

    public void setGroupDescription(String groupDescription) {
        this.groupDescription = groupDescription;
    }

    public String getGroupVisability() {
        return groupVisability;
    }

    public void setGroupVisability(String groupVisability) {
        this.groupVisability = groupVisability;
    }

    public String getMainGame() {
        return mainGame;
    }

    public void setMainGame(String mainGame) {
        this.mainGame = mainGame;
    }

    public String getComsChannel() {
        return comsChannel;
    }

    public void setComsChannel(String comsChannel) {
        this.comsChannel = comsChannel;
    }

    public String getActivityLevel() {
        return activityLevel;
    }

    public void setActivityLevel(String activityLevel) {
        this.activityLevel = activityLevel;
    }

    public String getGroupLanguageSpoken() {
        return groupLanguageSpoken;
    }

    public void setGroupLanguageSpoken(String groupLanguageSpoken) {
        this.groupLanguageSpoken = groupLanguageSpoken;
    }

    public String getGroupComsAddress() {
        return groupComsAddress;
    }

    public void setGroupComsAddress(String groupComsAddress) {
        this.groupComsAddress = groupComsAddress;
    }

    public String getGroupPictureReference() {
        return groupPictureReference;
    }

    public void setGroupPictureReference(String groupPictureReference) {
        this.groupPictureReference = groupPictureReference;
    }

    public Gamer getGamer() {
        return gamer;
    }

    public void setGamer(Gamer gamer) {
        this.gamer = gamer;
    }
}

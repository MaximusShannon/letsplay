package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Gamer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY);
    private int id;
    private String userName;
    private String bio;
    private String email;
    private String password;
    private String profilePictureReference; /*I think i will use FireBase to store images, not sure yet*/
    private String location;
    private String interest;
    private int postCount;
    private int commentsCount;
    private int applicationsCount; /* How many applications they've made on others posts*/
    private int loginCount;
    private int profileVersion; /* How many times the profile has been updated, default to 1*/
    private boolean isOnline;
    private boolean availableToPlay;
    private boolean autoMatchmaking;

    public Gamer(){

    }

    public Gamer(int id, String userName, String bio, String email, String password,
                 String profilePictureReference, String location, String interest, int postCount,
                 int commentsCount, int applicationsCount, int loginCount, int profileVersion,
                 boolean isOnline, boolean availableToPlay, boolean autoMatchmaking) {
        this.id = id;
        this.userName = userName;
        this.bio = bio;
        this.email = email;
        this.password = password;
        this.profilePictureReference = profilePictureReference;
        this.location = location;
        this.interest = interest;
        this.postCount = postCount;
        this.commentsCount = commentsCount;
        this.applicationsCount = applicationsCount;
        this.loginCount = loginCount;
        this.profileVersion = profileVersion;
        this.isOnline = isOnline;
        this.availableToPlay = availableToPlay;
        this.autoMatchmaking = autoMatchmaking;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfilePictureReference() {
        return profilePictureReference;
    }

    public void setProfilePictureReference(String profilePictureReference) {
        this.profilePictureReference = profilePictureReference;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }

    public int getPostCount() {
        return postCount;
    }

    public void setPostCount(int postCount) {
        this.postCount = postCount;
    }

    public int getCommentsCount() {
        return commentsCount;
    }

    public void setCommentsCount(int commentsCount) {
        this.commentsCount = commentsCount;
    }

    public int getApplicationsCount() {
        return applicationsCount;
    }

    public void setApplicationsCount(int applicationsCount) {
        this.applicationsCount = applicationsCount;
    }

    public int getLoginCount() {
        return loginCount;
    }

    public void setLoginCount(int loginCount) {
        this.loginCount = loginCount;
    }

    public int getProfileVersion() {
        return profileVersion;
    }

    public void setProfileVersion(int profileVersion) {
        this.profileVersion = profileVersion;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public void setOnline(boolean online) {
        isOnline = online;
    }

    public boolean isAvailableToPlay() {
        return availableToPlay;
    }

    public void setAvailableToPlay(boolean availableToPlay) {
        this.availableToPlay = availableToPlay;
    }

    public boolean isAutoMatchmaking() {
        return autoMatchmaking;
    }

    public void setAutoMatchmaking(boolean autoMatchmaking) {
        this.autoMatchmaking = autoMatchmaking;
    }
}

package models;

import javax.persistence.*;

@Entity
public class Post {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
    private String postTitle;
    private String ageRange;
    private String postDescription;
    private String postTags;
    private String languageSpoken;
    private String gamePlayed;
    private String timeZone;
    /*Requirements*/
    private boolean microphoneRequired;
    private boolean competitivePlayers;
    private boolean casualPlayers;
    private boolean acceptFemales;
    private boolean acceptMales;

    @OneToOne
    private Gamer gamer;

    public int getId() {
        return id;
    }

    public Gamer getGamer() {
        return gamer;
    }

    public void setGamer(Gamer gamer) {
        this.gamer = gamer;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getAgeRange() {
        return ageRange;
    }

    public void setAgeRange(String ageRange) {
        this.ageRange = ageRange;
    }

    public String getPostDescription() {
        return postDescription;
    }

    public void setPostDescription(String postDescription) {
        this.postDescription = postDescription;
    }

    public String getPostTags() {
        return postTags;
    }

    public void setPostTags(String postTags) {
        this.postTags = postTags;
    }

    public String getLanguageSpoken() {
        return languageSpoken;
    }

    public void setLanguageSpoken(String languageSpoken) {
        this.languageSpoken = languageSpoken;
    }

    public String getGamePlayed() {
        return gamePlayed;
    }

    public void setGamePlayed(String gamePlayed) {
        this.gamePlayed = gamePlayed;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public boolean isMicrophoneRequired() {
        return microphoneRequired;
    }

    public void setMicrophoneRequired(boolean microphoneRequired) {
        this.microphoneRequired = microphoneRequired;
    }

    public boolean isAcceptingCompetitivePlayers() {
        return competitivePlayers;
    }

    public void setCompetitivePlayers(boolean competitivePlayers) {
        this.competitivePlayers = competitivePlayers;
    }

    public boolean isAcceptingCasualPlayers() {
        return casualPlayers;
    }

    public void setCasualPlayers(boolean casualPlayers) {
        this.casualPlayers = casualPlayers;
    }

    public boolean isAcceptFemales() {
        return acceptFemales;
    }

    public void setAcceptFemales(boolean acceptFemales) {
        this.acceptFemales = acceptFemales;
    }

    public boolean isAcceptMales() {
        return acceptMales;
    }

    public void setAcceptMales(boolean acceptMales) {
        this.acceptMales = acceptMales;
    }
}

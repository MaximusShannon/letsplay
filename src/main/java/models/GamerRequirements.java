package models;

import javax.persistence.*;

@Entity
public class GamerRequirements {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private boolean availableToPlay;
    private boolean activeMatchmakingEnabled;
    /*Session requirements*/
    private int ageRangeMinimum; /* The minimum age they want to play with*/
    private String languageSpokenRequired;
    private String gamePlayedRequired;
    private int matchmakerSensitivity; /* The sensitivity of the matchmaker example: if set to 5 it only needs to me 5 requirements*/
    private boolean microphoneRequired;
    private boolean acceptMales;
    private boolean acceptFemales;
    private boolean lookingForCasualPlayers;
    private boolean lookingForCompetitivePlayers;

    public GamerRequirements(){

    }

    public GamerRequirements(boolean availableToPlay, boolean activeMatchmakingEnabled, int ageRangeMinimum, String languageSpokenRequired,
                             String gamePlayedRequired, int matchmakerSensitivity, boolean microphoneRequired,
                             boolean acceptMales, boolean acceptFemales, boolean lookingForCasualPlayers,
                             boolean lookingForCompetitivePlayers) {

        this.availableToPlay = availableToPlay;
        this.activeMatchmakingEnabled = activeMatchmakingEnabled;
        this.ageRangeMinimum = ageRangeMinimum;
        this.languageSpokenRequired = languageSpokenRequired;
        this.gamePlayedRequired = gamePlayedRequired;
        this.matchmakerSensitivity = matchmakerSensitivity;
        this.microphoneRequired = microphoneRequired;
        this.acceptMales = acceptMales;
        this.acceptFemales = acceptFemales;
        this.lookingForCasualPlayers = lookingForCasualPlayers;
        this.lookingForCompetitivePlayers = lookingForCompetitivePlayers;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isAvailableToPlay() {
        return availableToPlay;
    }

    public void setAvailableToPlay(boolean availableToPlay) {
        this.availableToPlay = availableToPlay;
    }

    public boolean isActiveMatchmakingEnabled() {
        return activeMatchmakingEnabled;
    }

    public void setActiveMatchmakingEnabled(boolean activeMatchmakingEnabled) {
        this.activeMatchmakingEnabled = activeMatchmakingEnabled;
    }

    public int getAgeRangeMinimum() {
        return ageRangeMinimum;
    }

    public void setAgeRangeMinimum(int ageRangeMinimum) {
        this.ageRangeMinimum = ageRangeMinimum;
    }

    public String getLanguageSpokenRequired() {
        return languageSpokenRequired;
    }

    public void setLanguageSpokenRequired(String languageSpokenRequired) {
        this.languageSpokenRequired = languageSpokenRequired;
    }

    public String getGamePlayedRequired() {
        return gamePlayedRequired;
    }

    public void setGamePlayedRequired(String gamePlayedRequired) {
        this.gamePlayedRequired = gamePlayedRequired;
    }

    public int getMatchmakerSensitivity() {
        return matchmakerSensitivity;
    }

    public void setMatchmakerSensitivity(int matchmakerSensitivity) {
        this.matchmakerSensitivity = matchmakerSensitivity;
    }

    public boolean isMicrophoneRequired() {
        return microphoneRequired;
    }

    public void setMicrophoneRequired(boolean microphoneRequired) {
        this.microphoneRequired = microphoneRequired;
    }

    public boolean isAcceptMales() {
        return acceptMales;
    }

    public void setAcceptMales(boolean acceptMales) {
        this.acceptMales = acceptMales;
    }

    public boolean isAcceptFemales() {
        return acceptFemales;
    }

    public void setAcceptFemales(boolean acceptFemales) {
        this.acceptFemales = acceptFemales;
    }

    public boolean isLookingForCasualPlayers() {
        return lookingForCasualPlayers;
    }

    public void setLookingForCasualPlayers(boolean lookingForCasualPlayers) {
        this.lookingForCasualPlayers = lookingForCasualPlayers;
    }

    public boolean isLookingForCompetitivePlayers() {
        return lookingForCompetitivePlayers;
    }

    public void setLookingForCompetitivePlayers(boolean lookingForCompetitivePlayers) {
        this.lookingForCompetitivePlayers = lookingForCompetitivePlayers;
    }
}

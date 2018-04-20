package models;

import javax.persistence.*;

@Entity
public class MatchmakerRequirement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int matchmakerRequirementId;
    private boolean availableToPlayFlag;
    private boolean matchmakerActivationFlag;
    private boolean doesGamerRequireMicrophone;
    private boolean doesGamerRequireCompetitiveGamers;
    private boolean doesGamerRequireCasualPlayers;
    private boolean doesGamerRequireMales;
    private boolean doesGamerRequireFemales;
    private String requiredLanguage;
    private String requiredGame;
    private int gamersMinimumAgeRequirement;

    public MatchmakerRequirement(){}

    public MatchmakerRequirement(boolean availableToPlayFlag, boolean matchmakerActiviationFlag,
                                 boolean doesGamerRequireMicrophone, boolean doesGamerRequireCompetitiveGamers,
                                 boolean doesGamerRequireCasualPlayers, boolean doesGamerRequireMales,
                                 boolean doesGamerRequireFemales, String requiredLanguage,
                                 String requiredGame, int gamersMinimumAgeRequirement, Gamer gamer) {

        this.availableToPlayFlag = availableToPlayFlag;
        this.matchmakerActivationFlag = matchmakerActiviationFlag;
        this.doesGamerRequireMicrophone = doesGamerRequireMicrophone;
        this.doesGamerRequireCompetitiveGamers = doesGamerRequireCompetitiveGamers;
        this.doesGamerRequireCasualPlayers = doesGamerRequireCasualPlayers;
        this.doesGamerRequireMales = doesGamerRequireMales;
        this.doesGamerRequireFemales = doesGamerRequireFemales;
        this.requiredLanguage = requiredLanguage;
        this.requiredGame = requiredGame;
        this.gamersMinimumAgeRequirement = gamersMinimumAgeRequirement;
        this.gamer = gamer;
    }

    @OneToOne
    private Gamer gamer;

    public int getMatchmakerRequirementId() {
        return matchmakerRequirementId;
    }

    public void setMatchmakerRequirementId(int matchmakerRequirementId) {
        this.matchmakerRequirementId = matchmakerRequirementId;
    }

    public boolean isAvailableToPlayFlag() {
        return availableToPlayFlag;
    }

    public void setAvailableToPlayFlag(boolean availableToPlayFlag) {
        this.availableToPlayFlag = availableToPlayFlag;
    }

    public boolean isMatchmakerActiviationFlag() {
        return matchmakerActivationFlag;
    }

    public void setMatchmakerActiviationFlag(boolean matchmakerActiviationFlag) {
        this.matchmakerActivationFlag = matchmakerActiviationFlag;
    }

    public boolean isDoesGamerRequireMicrophone() {
        return doesGamerRequireMicrophone;
    }

    public void setDoesGamerRequireMicrophone(boolean doesGamerRequireMicrophone) {
        this.doesGamerRequireMicrophone = doesGamerRequireMicrophone;
    }

    public boolean isDoesGamerRequireCompetitiveGamers() {
        return doesGamerRequireCompetitiveGamers;
    }

    public void setDoesGamerRequireCompetitiveGamers(boolean doesGamerRequireCompetitiveGamers) {
        this.doesGamerRequireCompetitiveGamers = doesGamerRequireCompetitiveGamers;
    }

    public boolean isDoesGamerRequireCasualPlayers() {
        return doesGamerRequireCasualPlayers;
    }

    public void setDoesGamerRequireCasualPlayers(boolean doesGamerRequireCasualPlayers) {
        this.doesGamerRequireCasualPlayers = doesGamerRequireCasualPlayers;
    }

    public boolean isDoesGamerRequireMales() {
        return doesGamerRequireMales;
    }

    public void setDoesGamerRequireMales(boolean doesGamerRequireMales) {
        this.doesGamerRequireMales = doesGamerRequireMales;
    }

    public boolean isDoesGamerRequireFemales() {
        return doesGamerRequireFemales;
    }

    public void setDoesGamerRequireFemales(boolean doesGamerRequireFemales) {
        this.doesGamerRequireFemales = doesGamerRequireFemales;
    }

    public String getRequiredLanguage() {
        return requiredLanguage;
    }

    public void setRequiredLanguage(String requiredLanguage) {
        this.requiredLanguage = requiredLanguage;
    }

    public String getRequiredGame() {
        return requiredGame;
    }

    public void setRequiredGame(String requiredGame) {
        this.requiredGame = requiredGame;
    }

    public int getGamersMinimumAgeRequirement() {
        return gamersMinimumAgeRequirement;
    }

    public void setGamersMinimumAgeRequirement(int gamersMinimumAgeRequirement) {
        this.gamersMinimumAgeRequirement = gamersMinimumAgeRequirement;
    }

    public Gamer getGamer() {
        return gamer;
    }

    public void setGamer(Gamer gamer) {
        this.gamer = gamer;
    }
}

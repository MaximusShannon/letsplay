package models;

public class Filter {

    private String languageRequired;
    private String timeZoneRequired;
    private boolean micRequired;
    private boolean acceptMales;
    private boolean acceptFemales;
    private boolean acceptCasualPlayers;
    private boolean acceptCompetitivePlayers;

    public String getLanguageRequired() {
        return languageRequired;
    }

    public void setLanguageRequired(String languageRequired) {
        this.languageRequired = languageRequired;
    }

    public boolean isMicRequired() {
        return micRequired;
    }

    public void setMicRequired(boolean micRequired) {
        this.micRequired = micRequired;
    }

    public String getTimeZoneRequired() {
        return timeZoneRequired;
    }

    public void setTimeZoneRequired(String timeZoneRequired) {
        this.timeZoneRequired = timeZoneRequired;
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

    public boolean isAcceptCasualPlayers() {
        return acceptCasualPlayers;
    }

    public void setAcceptCasualPlayers(boolean acceptCasualPlayers) {
        this.acceptCasualPlayers = acceptCasualPlayers;
    }

    public boolean isAcceptCompetitivePlayers() {
        return acceptCompetitivePlayers;
    }

    public void setAcceptCompetitivePlayers(boolean acceptCompetitivePlayers) {
        this.acceptCompetitivePlayers = acceptCompetitivePlayers;
    }
}

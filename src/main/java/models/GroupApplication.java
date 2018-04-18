package models;

import javax.persistence.*;

@Entity
public class GroupApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int applicationId;
    private int gamerId;
    private String message;

    @OneToOne
    private GamerGroup gamerGroup;

    public int getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(int applicationId) {
        this.applicationId = applicationId;
    }

    public int getGamerId() {
        return gamerId;
    }

    public void setGamerId(int gamerId) {
        this.gamerId = gamerId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public GamerGroup getGamerGroup() {
        return gamerGroup;
    }

    public void setGamerGroup(GamerGroup gamerGroup) {
        this.gamerGroup = gamerGroup;
    }
}

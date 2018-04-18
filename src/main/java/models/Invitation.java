package models;

import javax.persistence.*;

@Entity
public class Invitation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int groupId;
    private String invitationMessage;

    @OneToOne
    private Gamer invitedGamer;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getInvitationMessage() {
        return invitationMessage;
    }

    public void setInvitationMessage(String invitationMessage) {
        this.invitationMessage = invitationMessage;
    }

    public Gamer getInvitedGamer() {
        return invitedGamer;
    }

    public void setInvitedGamer(Gamer invitedGamer) {
        this.invitedGamer = invitedGamer;
    }
}

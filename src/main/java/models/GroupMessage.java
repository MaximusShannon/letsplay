package models;

import javax.persistence.*;

@Entity
public class GroupMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int messageId;
    private String message;

    @OneToOne
    private GamerGroup gamerGroup;

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
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

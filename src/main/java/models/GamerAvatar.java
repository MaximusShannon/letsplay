package models;

import org.hibernate.type.Type;
import org.hibernate.type.descriptor.sql.LobTypeMappings;

import javax.persistence.*;

@Entity
public class GamerAvatar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int avatarId;

    @Lob
    private byte[] avatarImage;

    @OneToOne
    private Gamer gamer;

    public int getAvatarId() {
        return avatarId;
    }

    public void setAvatarId(int avatarId) {
        this.avatarId = avatarId;
    }

    public byte[] getAvatarImage() {
        return avatarImage;
    }

    public void setAvatarImage(byte[] avatarImage) {
        this.avatarImage = avatarImage;
    }

    public Gamer getGamer() {
        return gamer;
    }

    public void setGamer(Gamer gamer) {
        this.gamer = gamer;
    }
}

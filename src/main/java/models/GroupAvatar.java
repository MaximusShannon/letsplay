package models;

import javax.persistence.*;

@Entity
public class GroupAvatar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int groupAvatarId;

    @Lob
    private byte[] image;

    @OneToOne
    private GamerGroup gamerGroup;

    public int getGroupAvatarId() {
        return groupAvatarId;
    }

    public void setGroupAvatarId(int groupAvatarId) {
        this.groupAvatarId = groupAvatarId;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public GamerGroup getGamerGroup() {
        return gamerGroup;
    }

    public void setGamerGroup(GamerGroup gamerGroup) {
        this.gamerGroup = gamerGroup;
    }
}

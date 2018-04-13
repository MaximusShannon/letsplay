package models;

import javax.persistence.*;

@Entity
public class MemberList {

    //m_1_id = Member One Id; IE: Gamer
    //Each group can have 15 members for demo purposes.

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int memberListId;

    @OneToMany
    private Gamer m_1_id;
    private Gamer m_2_id;
    private Gamer m_3_id;
    private Gamer m_4_id;
    private Gamer m_5_id;
    private Gamer m_6_id;
    private Gamer m_7_id;
    private Gamer m_8_id;
    private Gamer m_9_id;
    private Gamer m_10_id;
    private Gamer m_11_id;
    private Gamer m_12_id;
    private Gamer m_13_id;
    private Gamer m_14_id;
    private Gamer m_15_id;

    public int getMemberListId() {
        return memberListId;
    }

    public void setMemberListId(int memberListId) {
        this.memberListId = memberListId;
    }

    public Gamer getM_1_id() {
        return m_1_id;
    }

    public void setM_1_id(Gamer m_1_id) {
        this.m_1_id = m_1_id;
    }

    public Gamer getM_2_id() {
        return m_2_id;
    }

    public void setM_2_id(Gamer m_2_id) {
        this.m_2_id = m_2_id;
    }

    public Gamer getM_3_id() {
        return m_3_id;
    }

    public void setM_3_id(Gamer m_3_id) {
        this.m_3_id = m_3_id;
    }

    public Gamer getM_4_id() {
        return m_4_id;
    }

    public void setM_4_id(Gamer m_4_id) {
        this.m_4_id = m_4_id;
    }

    public Gamer getM_5_id() {
        return m_5_id;
    }

    public void setM_5_id(Gamer m_5_id) {
        this.m_5_id = m_5_id;
    }

    public Gamer getM_6_id() {
        return m_6_id;
    }

    public void setM_6_id(Gamer m_6_id) {
        this.m_6_id = m_6_id;
    }

    public Gamer getM_7_id() {
        return m_7_id;
    }

    public void setM_7_id(Gamer m_7_id) {
        this.m_7_id = m_7_id;
    }

    public Gamer getM_8_id() {
        return m_8_id;
    }

    public void setM_8_id(Gamer m_8_id) {
        this.m_8_id = m_8_id;
    }

    public Gamer getM_9_id() {
        return m_9_id;
    }

    public void setM_9_id(Gamer m_9_id) {
        this.m_9_id = m_9_id;
    }

    public Gamer getM_10_id() {
        return m_10_id;
    }

    public void setM_10_id(Gamer m_10_id) {
        this.m_10_id = m_10_id;
    }

    public Gamer getM_11_id() {
        return m_11_id;
    }

    public void setM_11_id(Gamer m_11_id) {
        this.m_11_id = m_11_id;
    }

    public Gamer getM_12_id() {
        return m_12_id;
    }

    public void setM_12_id(Gamer m_12_id) {
        this.m_12_id = m_12_id;
    }

    public Gamer getM_13_id() {
        return m_13_id;
    }

    public void setM_13_id(Gamer m_13_id) {
        this.m_13_id = m_13_id;
    }

    public Gamer getM_14_id() {
        return m_14_id;
    }

    public void setM_14_id(Gamer m_14_id) {
        this.m_14_id = m_14_id;
    }

    public Gamer getM_15_id() {
        return m_15_id;
    }

    public void setM_15_id(Gamer m_15_id) {
        this.m_15_id = m_15_id;
    }
}

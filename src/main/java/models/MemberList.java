package models;

import javax.persistence.*;

@Entity
public class MemberList {

    //m_1_id = Member One Id; IE: Gamer
    //Each group can have 15 members for demo purposes.

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int memberListId;

    private int m_1_id;
    private int m_2_id;
    private int m_3_id;
    private int m_4_id;
    private int m_5_id;
    private int m_6_id;
    private int m_7_id;
    private int m_8_id;
    private int m_9_id;
    private int m_10_id;
    private int m_11_id;
    private int m_12_id;
    private int m_13_id;
    private int m_14_id;
    private int m_15_id;

    @OneToOne
    private GamerGroup group;

    public GamerGroup getGroup() {
        return group;
    }

    public void setGroup(GamerGroup group) {
        this.group = group;
    }

    public int getMemberListId() {
        return memberListId;
    }

    public void setMemberListId(int memberListId) {
        this.memberListId = memberListId;
    }

    public int getM_1_id() {
        return m_1_id;
    }

    public void setM_1_id(int m_1_id) {
        this.m_1_id = m_1_id;
    }

    public int getM_2_id() {
        return m_2_id;
    }

    public void setM_2_id(int m_2_id) {
        this.m_2_id = m_2_id;
    }

    public int getM_3_id() {
        return m_3_id;
    }

    public void setM_3_id(int m_3_id) {
        this.m_3_id = m_3_id;
    }

    public int getM_4_id() {
        return m_4_id;
    }

    public void setM_4_id(int m_4_id) {
        this.m_4_id = m_4_id;
    }

    public int getM_5_id() {
        return m_5_id;
    }

    public void setM_5_id(int m_5_id) {
        this.m_5_id = m_5_id;
    }

    public int getM_6_id() {
        return m_6_id;
    }

    public void setM_6_id(int m_6_id) {
        this.m_6_id = m_6_id;
    }

    public int getM_7_id() {
        return m_7_id;
    }

    public void setM_7_id(int m_7_id) {
        this.m_7_id = m_7_id;
    }

    public int getM_8_id() {
        return m_8_id;
    }

    public void setM_8_id(int m_8_id) {
        this.m_8_id = m_8_id;
    }

    public int getM_9_id() {
        return m_9_id;
    }

    public void setM_9_id(int m_9_id) {
        this.m_9_id = m_9_id;
    }

    public int getM_10_id() {
        return m_10_id;
    }

    public void setM_10_id(int m_10_id) {
        this.m_10_id = m_10_id;
    }

    public int getM_11_id() {
        return m_11_id;
    }

    public void setM_11_id(int m_11_id) {
        this.m_11_id = m_11_id;
    }

    public int getM_12_id() {
        return m_12_id;
    }

    public void setM_12_id(int m_12_id) {
        this.m_12_id = m_12_id;
    }

    public int getM_13_id() {
        return m_13_id;
    }

    public void setM_13_id(int m_13_id) {
        this.m_13_id = m_13_id;
    }

    public int getM_14_id() {
        return m_14_id;
    }

    public void setM_14_id(int m_14_id) {
        this.m_14_id = m_14_id;
    }

    public int getM_15_id() {
        return m_15_id;
    }

    public void setM_15_id(int m_15_id) {
        this.m_15_id = m_15_id;
    }
}

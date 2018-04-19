package models;

import javax.persistence.*;

@Entity
public class PostComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int commentId;
    private String commentText;

    @OneToOne
    private Gamer commentor;

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public Gamer getCommentor() {
        return commentor;
    }

    public void setCommentor(Gamer commentor) {
        this.commentor = commentor;
    }
}

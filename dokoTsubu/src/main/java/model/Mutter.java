package model;

import java.io.Serializable;
import java.util.List;

public class Mutter implements Serializable {
    private int id;
    private String userName;
    private String text;
    private String tag;
    private int likeCount;
    private List<Comment> comments;
    private String imageUrl;

    public Mutter() {}

    public Mutter(String userName, String text, String tag, int likeCount, List<Comment> comments, String imageUrl) {
        this.userName = userName;
        this.text = text;
        this.tag = tag;
        this.likeCount = likeCount;
        this.comments = comments;
        this.imageUrl = imageUrl;
    }

    public Mutter(int id, String userName, String text, String tag, int likeCount, List<Comment> comments, String imageUrl) {
        this.id = id;
        this.userName = userName;
        this.text = text;
        this.tag = tag;
        this.likeCount = likeCount;
        this.comments = comments;
        this.imageUrl = imageUrl;
    }

    public int getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getText() {
        return text;
    }

    public String getTag() {
        return tag;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}

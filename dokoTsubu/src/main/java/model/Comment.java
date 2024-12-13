package model;

import java.io.Serializable;

public class Comment implements Serializable {
    private int id;
    private int mutterId;
    private String userName;
    private String text;

    public Comment(int id, int mutterId, String userName, String text) {
        this.id = id;
        this.mutterId = mutterId;
        this.userName = userName;
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public int getMutterId() {
        return mutterId;
    }

    public String getUserName() {
        return userName;
    }

    public String getText() {
        return text;
    }
}


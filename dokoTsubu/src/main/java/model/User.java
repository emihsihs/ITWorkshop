package model;

import java.io.Serializable;

public class User implements Serializable {
    private int id;
    private String name;
    private String pass;

    public User() {}

    // 既存のコンストラクタ
    public User(int id, String name, String pass) {
        this.id = id;
        this.name = name;
        this.pass = pass;
    }

    // 新しいコンストラクタ
    public User(String name, String pass) {
        this.name = name;
        this.pass = pass;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // ここでメソッド名を変更
    public String getPassword() {
        return pass;
    }

    public void setPassword(String pass) {
        this.pass = pass;
    }
}

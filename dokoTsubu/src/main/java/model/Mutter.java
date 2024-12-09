package model;

import java.io.Serializable;

public class Mutter implements Serializable {
    private int id;
    private String userName;
    private String text;
    private String tag; // タグ情報

    public Mutter() {}

    public Mutter(String userName, String text, String tag) {
        this.userName = userName;
        this.text = text;
        this.tag = tag;
    }

    public Mutter(int id, String userName, String text, String tag) {
        this.id = id;
        this.userName = userName;
        this.text = text;
        this.tag = tag;
    }

    // 旧コンストラクタを追加してエラーを解消
    public Mutter(int id, String userName, String text) {
        this.id = id;
        this.userName = userName;
        this.text = text;
        this.tag = ""; // タグは空文字で初期化
    }

    public int getId() { return id; }
    public String getUserName() { return userName; }
    public String getText() { return text; }
    public String getTag() { return tag; }
}


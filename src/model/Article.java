package model;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;

public class Article {
    private String title;
    private String body;
    private User author;
    private String imageSource;
    private boolean ban;
    private String reason;

    public Article() {

    }

    public Article(String title, String body, User author) {
        this.title = title;
        this.body = body;
        this.author = author;
        this.imageSource = null;
        this.ban = false;
        this.reason = null;
    }

    public void submit() {
        //1. title == null? body == null?
        if (title == null || body == null) {
            return;
        }

        try {
            title = new String(title.getBytes("8859_1"), "utf-8");
            body = new String(body.getBytes("8859_1"), "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        //2. ad? (AdCheck class의 메소드를 실행)

        // db connection & insertion
        Database db = new ArticleDB();
        db.connect();
        // db.insert(title, body, null);
        db.insert(this);
        db.closeDB();
    }

    //수정시에는 웹 화면에서 수정전 데이터가 보여진다
    public void edit() {
        this.submit();
    }

    public void delete() {
        //1. 고냥 DB에서 지운다
        //2. 다시 ArticleRequest로 보냄
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public User getAuthor() {
        return author;
    }

    public String getImageSource() {
        return imageSource;
    }

    public boolean isBan() {
        BannedListDB bannedListDB = new BannedListDB();
        bannedListDB.selectAll();
        ArrayList<String> bannedWords = new ArrayList<>();
        try {
            while (bannedListDB.rs.next()) {
                bannedWords.add(bannedListDB.rs.getString("word"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        TextConfirm textConfirm = new TextConfirm();
        textConfirm.checkBlackWord(title, bannedWords);
        textConfirm.checkBlackWord(body, bannedWords);
        ImageConfirm imageConfirm = new ImageConfirm();
        if (this.imageSource != null) {
            imageConfirm.confirm(this.imageSource, bannedWords);
        }
        return false;
    }

    public String getReason() {
        return reason;
    }
}
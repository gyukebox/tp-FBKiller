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
        if (title == null || body == null) {
            return;
        }

        try {
            title = new String(title.getBytes("8859_1"), "utf-8");
            body = new String(body.getBytes("8859_1"), "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        this.ban = filter();
        if(ban) {
            try {
                reason = new String(reason.getBytes("8859_1"), "utf-8");
                System.out.println(reason);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        Database db = new ArticleDB();
        db.connect();
        db.insert(this);
        db.closeDB();
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

    public boolean filter() {
        BannedListDB bannedListDB = new BannedListDB();
        bannedListDB.connect();
        bannedListDB.selectAll();
        ArrayList<String> bannedWords = new ArrayList<>();
        try {
            while (bannedListDB.rs.next()) {
                bannedWords.add(bannedListDB.rs.getString("word"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(TextConfirm.checkBlackWord(title, bannedWords) || TextConfirm.checkBlackWord(body, bannedWords)) {
            this.reason = "게시물 차단 이유 : [금지어]";
            System.out.println(reason);
            return true;
        }

        if (this.imageSource != null) {
            this.reason = ImageConfirm.confirm(this.imageSource, bannedWords);
            if(reason != null) {
                return true;
            }
        }

        return false;
    }

    public boolean isBan() {
        return ban;
    }

    public String getReason() {
        return reason;
    }

    public void setImageSource(String imageSource) {
        this.imageSource = imageSource;
    }
}
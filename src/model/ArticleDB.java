package model;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ArticleDB extends Database {

    public ArticleDB() {
        super();
    }

    public ResultSet getResult() {
        return super.rs;
    }

    @Override
    public int insert(Article article) {
        int resultValue = 0;
        int ban;
        if (article.isBan()) {
            ban = 1;
        } else {
            ban = 0;
        }

        //test
        System.out.println("article info:");
        System.out.println(article.getAuthor());
        System.out.println(article.getTitle());
        System.out.println(article.getBody());
        System.out.println(article.isBan());
        System.out.println(article.getReason());

        try {
            String queryString = "INSERT INTO article (head, author, authorprofile, body, image, ban, reason) "
                    + "VALUES (\'" + article.getTitle() + "\',\'"
                    + article.getAuthor().getUsername() + "\',\'"
                    + article.getAuthor().getProfilePicture() + "\',\'"
                    + article.getBody() + "\',\'"
                    + article.getImageSource() + "\',\'"
                    + ban + "\',\'"
                    + article.getReason() + "\')";
            stmt = conn.createStatement();
            resultValue = stmt.executeUpdate(queryString);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultValue;
    }

    public void selectAll() {

        try {
            String queryString = "SELECT * FROM article";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(queryString);
            rs.beforeFirst();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int remove(int num) {
        int resultValue = 0;

        try {
            String queryString = "DELETE FROM article WHERE number=" + num;
            stmt = conn.createStatement();
            resultValue = stmt.executeUpdate(queryString);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultValue;
    }

    public int productUpdate(int num, String Head, String Body) {
        int resultValue = 0;

        try {
            String queryString = "UPDATE article SET head=" + Head + ", body='" + Body + "' WHERE number =" + num;
            stmt = conn.createStatement();
            resultValue = stmt.executeUpdate(queryString);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultValue;

    }

    public void selectOne(int num) {
        try {
            String queryString = "SELECT * FROM article WHERE number =" + num;
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            stmt = conn.createStatement();
            rs = stmt.executeQuery(queryString);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void search(String searchKeyWord) {

        try {
            String queryString = "SELECT * FROM article WHERE head LIKE '%" + searchKeyWord + "%'";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(queryString);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

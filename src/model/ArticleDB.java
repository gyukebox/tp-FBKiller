package model;

import java.sql.DriverManager;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class ArticleDB extends Database {

    public ArticleDB() {
        super();
    }

    public int Insert(String head, String body, String image) {
        int resultValue = 0;

        try {
            String queryString = "INSERT INTO article (head, body, image) VALUES (\'" + head + "\',\'" + body + "\',\'" + image + "\')";
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            stmt = conn.createStatement();
            resultValue = stmt.executeUpdate(queryString);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDB();
        }

        return resultValue;
    }

    public void selectAll() {

        try {
            String queryString = "SELECT * FROM article";
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            stmt = conn.createStatement();

            rs = stmt.executeQuery(queryString);

            ResultSetMetaData resultSetMetaData = rs.getMetaData();

            System.out.println(resultSetMetaData.getColumnName(1) + "\t" + resultSetMetaData.getColumnName(2) + "\t" + resultSetMetaData.getColumnName(3));

            while (rs.next()) {
                System.out.println(rs.getString("head") + "\t" + rs.getString("body") + "\t" + rs.getString("image"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDB();
        }
    }

    public int remove(int num) {
        int resultValue = 0;

        try {
            String queryString = "DELETE FROM article WHERE number=" + num;
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            stmt = conn.createStatement();
            resultValue = stmt.executeUpdate(queryString);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDB();
        }

        return resultValue;
    }

    public int productUpdate(int num, String Head, String Body) {
        int resultValue = 0;

        try {
            String queryString = "UPDATE article SET head=" + Head + ", body='" + Body + "' WHERE number =" + num;
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            stmt = conn.createStatement();
            resultValue = stmt.executeUpdate(queryString);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDB();
        }

        return resultValue;

    }

    public void selectOne(int num) {
        try {
            String queryString = "SELECT * FROM article WHERE number =" + num;

            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            stmt = conn.createStatement();

            rs = stmt.executeQuery(queryString);

            System.out.println("number" + "\t" + "head" + "\t" + "body" + "\t" + "image");

            while (rs.next()) {
                System.out.println(rs.getInt("number") + "\t" + rs.getString("head") + "\t" + rs.getInt("body") + rs.getInt("image"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDB();
        }

    }

    public void search(String searchKeyWord) {

        try {
            String queryString = "SELECT * FROM article WHERE head LIKE '%" + searchKeyWord + "%'";
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            stmt = conn.createStatement();
            rs = stmt.executeQuery(queryString);

            System.out.println("number" + "\t" + "head" + "\t" + "body" + "\t" + "image");

            while (rs.next()) {
                System.out.println(rs.getInt("number") + "\t" + rs.getString("head") + "\t" + rs.getString("body") + "\t" + rs.getString("image"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDB();
        }
    }

}

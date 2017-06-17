package model;

import java.sql.DriverManager;
import java.sql.SQLException;

public class BannedListDB extends Database {

    public BannedListDB() {
        super();
    }


    public int Insert(String word) {
        int resultValue = 0;

        try {
            String queryString = "INSERT INTO ban (word) VALUES (\'" + word + "\')";
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            stmt = conn.createStatement();
            resultValue = stmt.executeUpdate(queryString);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultValue;
    }

    public void selectAll() {

        try {
            String queryString = "SELECT * FROM ban";
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            stmt = conn.createStatement();
            rs = stmt.executeQuery(queryString);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int remove(int num) {
        int resultValue = 0;

        try {
            String queryString = "DELETE FROM ban WHERE number =" + num;
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            stmt = conn.createStatement();
            resultValue = stmt.executeUpdate(queryString);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultValue;
    }

    public int update(int num, String word) {
        int resultValue = 0;

        try {
            String queryString = "UPDATE ban SET word=" + word + " WHERE number =" + num;
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            stmt = conn.createStatement();
            resultValue = stmt.executeUpdate(queryString);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultValue;

    }

    public void selectOne(int num) {
        try {
            String queryString = "SELECT * FROM ban WHERE number =" + num;
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            stmt = conn.createStatement();
            rs = stmt.executeQuery(queryString);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void search(String searchKeyWord) {

        try {
            String queryString = "SELECT * FROM ban WHERE word LIKE '%" + searchKeyWord + "%'";
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            stmt = conn.createStatement();
            rs = stmt.executeQuery(queryString);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

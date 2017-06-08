package model;

import java.sql.DriverManager;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class UserDB extends Database {

    public UserDB() {
        super();
    }

    public int Insert(String ID, String PW, int status) {
        int resultValue = 0;

        try {
            String queryString = "INSERT INTO user (id, pw, status) VALUES (\'" + ID + "\',\'" + PW + "\',\'" + status + "\')";
            System.out.println(queryString);
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
            String queryString = "SELECT * FROM user";
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            stmt = conn.createStatement();
            rs = stmt.executeQuery(queryString);
            ResultSetMetaData resultSetMetaData = rs.getMetaData();
            System.out.println(resultSetMetaData.getColumnName(1) + "\t" + resultSetMetaData.getColumnName(2) + "\t" + resultSetMetaData.getColumnName(3) + "\t" + resultSetMetaData.getColumnName(4));
            while (rs.next()) {
                System.out.println(rs.getInt("number") + "\t" + rs.getString("id") + "\t" + rs.getString("pw") + "\t" + rs.getInt("status"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            super.closeDB();
        }
    }

    public int remove(int num) {
        int resultValue = 0;

        try {
            String queryString = "DELETE FROM user WHERE number =" + num;
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

    public int update(int user_num, String Head, String Body) {
        int resultValue = 0;

        try {
            String queryString = "UPDATE user SET head=" + Head + ", body='" + Body + "' WHERE number =" + user_num;
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
            String queryString = "SELECT * FROM user WHERE number =\'" + num + "\'";
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            stmt = conn.createStatement();
            rs = stmt.executeQuery(queryString);
            System.out.println("user_num" + "\t" + "ID" + "\t" + "PW" + "\t" + "Status");
            while (rs.next()) {
                System.out.println(rs.getInt("number") + "\t" + rs.getString("id") + "\t" + rs.getInt("pw") + rs.getInt("status"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDB();
        }

    }

    public void search(String searchKeyWord) {

        try {
            String queryString = "SELECT * FROM user WHERE id LIKE '%" + searchKeyWord + "%'";
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            stmt = conn.createStatement();
            rs = stmt.executeQuery(queryString);
            System.out.println("user_num" + "\t" + "ID" + "\t" + "PW" + "\t" + "status");
            while (rs.next()) {
                System.out.println(rs.getInt("number") + "\t" + rs.getString("id") + "\t" + rs.getString("pw") + "\t" + rs.getInt("status"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDB();
        }
    }

}

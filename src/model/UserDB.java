package model;

import java.sql.DriverManager;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class UserDB extends Database {

    public UserDB() {
        super();
    }

    @Override
    public int insert(String ID, String PW, int status, String HP, char gender) {
        int resultValue = 0;

        try {
            String queryString = "INSERT INTO user (id, pw, status, HP, gender) VALUES (\'" + ID + "\',\'" + PW + "\',\'" + status + "\',\'" + HP + "\',\'" + gender + "\')";
            System.out.println(queryString);
            stmt = conn.createStatement();
            resultValue = stmt.executeUpdate(queryString);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultValue;
    }

    @Override
    public void selectAll() {

        try {
            String queryString = "SELECT * FROM user";
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            stmt = conn.createStatement();
            rs = stmt.executeQuery(queryString);
            ResultSetMetaData resultSetMetaData = rs.getMetaData();
            System.out.print(resultSetMetaData.getColumnName(1));
            System.out.print("\t");
            System.out.print(resultSetMetaData.getColumnName(2));
            System.out.print("\t");
            System.out.print(resultSetMetaData.getColumnName(3));
            System.out.print("\t");
            System.out.println(resultSetMetaData.getColumnName(4));
            while (rs.next()) {
                System.out.print(rs.getInt("number"));
                System.out.print("\t");
                System.out.print(rs.getString("id"));
                System.out.print("\t");
                System.out.print(rs.getString("pw"));
                System.out.print("\t");
                System.out.print(rs.getInt("status"));
                System.out.println();
            }
            rs.beforeFirst();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int remove(int num) {
        int resultValue = 0;

        try {
            super.connect();
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

    //string 값을 지니는 id의 정보를 삭제
    public int remove(String str) {
        int result = 0;
        try {
            super.connect();
            String query = "DELETE FROM user WHERE id =" + str;
            stmt = conn.createStatement();
            result = stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int update(int user_num, String Head, String Body) {
        int resultValue = 0;

        try {
            String queryString = "UPDATE user SET head=" + Head + ", body='" + Body + "' WHERE number =" + user_num;
            stmt = conn.createStatement();
            resultValue = stmt.executeUpdate(queryString);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultValue;
    }

    public int update(String id) {
        int resultValue = 0;

        try {
            String query = "UPDATE user SET status = 1 WHERE id = " + id;
            stmt = conn.createStatement();
            resultValue = stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultValue;
    }

    @Override
    public void selectOne(int num) {
        try {
            super.connect();
            String queryString = "SELECT * FROM user WHERE number =\'" + num + "\'";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(queryString);
            System.out.println("user_num" + "\t" + "ID" + "\t" + "PW" + "\t" + "Status");
            while (rs.next()) {
                System.out.println(rs.getInt("number") + "\t" + rs.getString("id") + "\t" + rs.getInt("pw") + rs.getInt("status"));
            }
            rs.beforeFirst();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
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
            rs.beforeFirst();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

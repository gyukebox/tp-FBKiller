package model;

import java.sql.DriverManager;
import java.sql.SQLException;

public class UserDB extends Database {

    public UserDB() {
        super();
    }

    @Override
    public void insert(User user) {
        try {
            String query = "INSERT INTO user (id, username, pw, picture, HP, gender)" +
                    " VALUES (\'" +
                    user.getId() + "\',\'" +
                    user.getUsername() + "\',\'" +
                    user.getPassword() + "\',\'" +
                    user.getProfilePicture() + "\',\'" +
                    user.getPhoneNumber() + "\',\'" +
                    user.getGender() + "\')";
            stmt = conn.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void selectAll() {

        try {
            String queryString = "SELECT * FROM user";
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            stmt = conn.createStatement();
            rs = stmt.executeQuery(queryString);
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

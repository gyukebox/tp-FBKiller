package model;

import java.sql.*;

public abstract class Database {

    private final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    final String DB_URL = "jdbc:mysql://localhost:3306/facebook";

    final String USERNAME = "root";
    final String PASSWORD = "biss9541";

    Connection conn;
    Statement stmt;
    ResultSet rs;

    public Database() {

    }

    public void connect() {
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("connection error!");
            e.printStackTrace();
        }
    }

    public void closeDB() {
        try {
            if (conn != null) {
                conn.close();
            }

            if (stmt != null) {
                stmt.close();
            }

            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insert(User user) {

    }

    public int insert(Article article) {
        return 0;
    }

    public int update(String id) {
        return 0;
    }

    public abstract void selectAll();

    public abstract int remove(int num);

    public abstract void selectOne(int num);

    public abstract void search(String searchKeyWord);
}
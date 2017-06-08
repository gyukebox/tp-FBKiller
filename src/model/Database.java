package model;

import java.sql.*;

public abstract class Database {

    private final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    final String DB_URL = "jdbc:mysql://127.0.0.1:3306/Facebook";

    final String USERNAME = "root";
    final String PASSWORD = "1234";

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

    public abstract void selectAll();

    public abstract int remove(int num);

    public abstract void selectOne(int num);

    public abstract void search(String searchKeyWord);
}
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Database {
    private String sql;
    private String dburl;
    private String dbid;
    private String dbpw;
    private Connection conn;
    private PreparedStatement statement;

    private Database() {
        this.sql = null;
        this.dburl = "jdbc:mysql://localhost:3306/timeline";
        this.dbid = "root";
        this.dbpw = "biss9541";
        this.conn = null;
        this.statement = null;
    }

    //다른 메소드를 사용하기 전에 이 메소드를 호출해야 함!
    private void connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(dburl, dbid, dbpw);
            System.out.println("connected!");
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e);
            e.printStackTrace();
        }
    }

    //게시물을 db에 추가하는 메소드
    public void insertData(String id, String subject, String body) {
        this.connect();

        try {
            this.sql = "INSERT INTO infos(id, subject, body) VALUES('" + id + "','" + subject + "','" + body + "')";
            this.statement = conn.prepareStatement(sql);
            System.out.println("successfully added!");
        } catch (SQLException e) {
            System.err.println(e);
            e.printStackTrace();
        } finally {
            try {
                statement.close();
                conn.close();
            } catch (SQLException e) {
                System.err.println(e);
                e.printStackTrace();
            }
        }
    }


    public void viewAllData() {
        this.connect();

        try {
            this.sql = "SELECT * FROM infos";
            this.statement = conn.prepareStatement(sql);
        } catch (SQLException e) {
            System.err.println(e);
            e.printStackTrace();
        }
    }

}


package model;

import java.sql.*;

public class Database {
    private static final Database db = new Database();

    final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    final String DB_URL = "jdbc:mysql://127.0.0.1:3306/Facebook";

    final String USERNAME = "root";
    final String PASSWORD = "biss9541";

    Connection conn;
    Statement stmt;
    ResultSet rs;

    public Database() {
        try {
            // ① 로드
            Class.forName(JDBC_DRIVER);

            // ② 연결
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException e) {
            System.out.println("[로드 오류]\n" + e.getStackTrace());
        } catch (SQLException e) {
            System.out.println("[연결 오류]\n" + e.getStackTrace());
        }
    }

    public void closeDB() {
        try {
            if (conn != null) {
                conn.close();
                System.out.println("connection 닫기 완료");
            }

            if (stmt != null) {
                stmt.close();
                System.out.println("statement 닫기 완료");
            }

            if (rs != null) {
                rs.close();
                System.out.println("resultset 닫기 완료");
            }
        } catch (SQLException e) {
            System.out.println("[닫기 오류]\n" + e.getStackTrace());
        }
    }

    public int Insert(int user_num, String ID, String PW, boolean status) {
        int resultValue = 0;

        try {
            String queryString = "INSERT INTO product VALUES (" + ID + ", " + PW + ")";

            // ② 연결 [Connection]
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            // ② 연결 [Statement]
            stmt = conn.createStatement();

            // ③ 실행 [CRUD]
            resultValue = stmt.executeUpdate(queryString);
        } catch (SQLException e) {
            System.out.println("[쿼리 오류]\n" + e.getStackTrace());
        } finally {
            // ④ 닫기
            closeDB();
        }

        return resultValue;
    }

    public void productSelectAll() {

        try {
            String queryString = "SELECT * FROM product";

            // ② 연결 [Connection]
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            // ② 연결 [Statement]
            stmt = conn.createStatement();

            // ③ 실행 [CRUD]
            rs = stmt.executeQuery(queryString);

            // 컬럼 정보 가져오기
            ResultSetMetaData resultSetMetaData = rs.getMetaData();

            // 컬럼 출력
            System.out.println(resultSetMetaData.getColumnName(1) + "\t" + resultSetMetaData.getColumnName(2) + "\t" + resultSetMetaData.getColumnName(3));

            while (rs.next()) {
                System.out.println(rs.getInt("user_num") + "\t" + rs.getString("ID") + "\t" + rs.getString("PW") + "\t" + rs.getInt("status"));
            }
        } catch (SQLException e) {
            System.out.println("[쿼리 오류]\n" + e.getStackTrace());
        } finally {
            // ④ 닫기
            closeDB();
        }
    }

    public int productRemove(int user_num) {
        int resultValue = 0;

        try {
            String queryString = "DELETE FROM product WHERE user_num=" + user_num;

            // ② 연결 [Connection]
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            // ② 연결 [Statement]
            stmt = conn.createStatement();

            // ③ 실행 [CRUD]
            resultValue = stmt.executeUpdate(queryString);
        } catch (SQLException e) {
            System.out.println("[쿼리 오류]\n" + e.getStackTrace());
        } finally {
            // ④ 닫기
            closeDB();
        }

        return resultValue;
    }

    //업데이트는 게시물에 대해서만 가능? (회원정보에 대한 업데이트를 구현할 것인가?)
    public int productUpdate(int user_num, String Head, String Body) {
        int resultValue = 0;

        try {
            String queryString = "UPDATE product SET Head=" + Head + ", Body='" + Body + "' WHERE user_num=" + user_num;

            // ② 연결 [Connection]
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            // ② 연결 [Statement]
            stmt = conn.createStatement();

            // ③ 실행 [CRUD]
            resultValue = stmt.executeUpdate(queryString);
        } catch (SQLException e) {
            System.out.println("[쿼리 오류]\n" + e.getStackTrace());
        } finally {
            // ④ 닫기
            closeDB();
        }

        return resultValue;

    }

    public void productSelectOne(int user_num) {
        try {
            String queryString = "SELECT * FROM product WHERE user_num=" + user_num;

            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            // ② 연결 [Statement]
            stmt = conn.createStatement();

            // ③ 실행 [CRUD]
            rs = stmt.executeQuery(queryString);

            System.out.println("user_num" + "\t" + "ID" + "\t" + "PW" + "\t" + "Status");

            while (rs.next()) {
                System.out.println(rs.getInt("user_num") + "\t" + rs.getString("ID") + "\t" + rs.getInt("PW") + rs.getInt("status"));
            }
        } catch (SQLException e) {
            System.out.println("[쿼리 오류]\n" + e.getStackTrace());
        } finally {
            // ④ 닫기
            closeDB();
        }

    }

    public void productSearch(String SearchKeyword) {

        try {
            String queryString = "SELECT * FROM product WHERE ID LIKE '%" + SearchKeyword + "%'";

            // ② 연결 [Connection]
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            // ② 연결 [Statement]
            stmt = conn.createStatement();

            // ③ 실행 [CRUD]
            rs = stmt.executeQuery(queryString);

            System.out.println("user_num" + "\t" + "ID" + "\t" + "PW" + "\t" + "status");

            while (rs.next()) {
                System.out.println(rs.getInt("user_num") + "\t" + rs.getString("ID") + "\t" + rs.getString("PW") + "\t" + rs.getInt("status"));
            }
        } catch (SQLException e) {
            System.out.println("[쿼리 오류]\n" + e.getStackTrace());
        } finally {
            // ④ 닫기
            closeDB();
        }

    }

}


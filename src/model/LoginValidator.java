package model;

import java.sql.SQLException;

public class LoginValidator {
    private String id;
    private String pw;
    private Database db;

    public LoginValidator(String id, String pw) {
        this.id = id;
        this.pw = pw;
    }

    // makes instance of an user when is valid, returns null if invalid
    public User validate() {
        User user = null;
        db = new UserDB();
        db.connect();
        db.search(this.id);
        try {
            if(db.rs.next()) {
                String id = db.rs.getString("id");
                String pw = db.rs.getString("pw");
                if(!id.equals(this.id)) {
                    return null;
                } else if(!pw.equals(this.pw)) {
                    return null;
                } else {
                    String username = db.rs.getString("username");
                    String imageSource = db.rs.getString("picture");
                    String hp = db.rs.getString("HP");
                    char gender = db.rs.getString("gender").charAt(0);

                    user = new User(this.id, username, this.pw, imageSource, hp, gender);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.closeDB();
        }

        return user;
    }
}
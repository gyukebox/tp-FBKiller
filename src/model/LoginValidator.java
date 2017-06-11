package model;

import java.sql.SQLException;

public class LoginValidator {
    private String idInput;
    private String pwInput;
    private Database db;

    public LoginValidator(String idInput, String pwInput) {
        this.idInput = idInput;
        this.pwInput = pwInput;
    }

    public boolean isValid() {
        boolean valid = true;
        db = new UserDB();
        db.connect();
        db.search(this.idInput);
        try {
            if(db.rs.next()) {
                String id = db.rs.getString("id");
                String pw = db.rs.getString("pw");
                if(!id.equals(this.idInput)) {
                    valid = false;
                } else if(!pw.equals(this.pwInput)) {
                    valid = false;
                } else {
                    // status to 1
                    db.update(this.idInput);
                }
            } else {
                valid = false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            valid = false;
        } finally {
            db.closeDB();
        }

        return valid;
    }
}
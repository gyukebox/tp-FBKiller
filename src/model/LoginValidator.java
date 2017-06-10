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
        db = new UserDB();
        db.connect();
        db.search(this.idInput);
        try {
            String id = db.rs.getString("id");
            if (!id.equals(this.idInput)) {
                return false;
            } else {
                String pw = db.rs.getString("pw");
                if (!pw.equals(this.pwInput)) {
                    return false;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            db.closeDB();
        }

        return true;
    }
}
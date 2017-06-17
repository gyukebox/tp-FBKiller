package model;

import java.sql.SQLException;

public class RegisterValidator {
    private User user;
    private Database db;

    public RegisterValidator(User user) {
        this.user = user;
        db = new UserDB();
    }

    private boolean isValidId(String id) {
        boolean valid = true;
        BannedListDB bandb = new BannedListDB();
        bandb.connect();
        bandb.selectAll();

        try {
            while (bandb.rs.next()) {
                String bannedWord = bandb.rs.getString("word");
                if (id.contains(bannedWord)) {
                    valid = false;
                    break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            bandb.closeDB();
        }
        return valid;
    }

    private boolean isValidPw(String pw) {
        if (!hasUpperCase(pw)) {
            return false;
        }
        if (!hasNumbers(pw)) {
            return false;
        }
        if (!hasSpecialCharacter(pw)) {
            return false;
        }
        if (pw.length() < 8) {
            return false;
        }

        return true;
    }

    private boolean hasUpperCase(String str) {
        return !str.equals(str.toLowerCase());
    }

    private boolean hasNumbers(String str) {
        return str.matches(".*\\d+.*");
    }

    private boolean hasSpecialCharacter(String str) {
        return !str.matches("[A-Za-z0-9 ]*");
    }

    private boolean isValidHp(String hp) {
        if (!hp.substring(0, 3).equals("010")) {
            return false;
        }
        if (hp.length() != 11) {
            return false;
        }
        return true;
    }

    private boolean isDuplicate(String id) {
        db.selectAll();
        try {
            while (db.rs.next()) {
                String storedID = db.rs.getString("ID");
                if (id.equals(storedID)) {
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean confirm() {
        if (!isValidId(this.user.getId())) {
            return false;
        }
        if (isDuplicate(this.user.getId())) {
            return false;
        }
        if (!isValidHp(this.user.getPhoneNumber())) {
            return false;
        }
        if (!isValidPw(this.user.getPassword())) {
            return false;
        }

        return true;
    }

    public void submit() {
        db.insert(user);
        db.closeDB();
    }
}

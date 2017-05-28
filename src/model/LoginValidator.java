package model;

public class LoginValidator {
    private Database db;
    private String idInput;
    private String pwInput;

    public LoginValidator(String idInput, String pwInput) {
        this.idInput = idInput;
        this.pwInput = pwInput;
    }

    public boolean isValid(String idInput, String pwInput) {
        return false;
    }
}

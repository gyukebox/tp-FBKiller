package model;

public class LoginValidator {
    private String idInput;
    private String pwInput;

    public LoginValidator(String idInput, String pwInput) {
        this.idInput = idInput;
        this.pwInput = pwInput;
    }

    public boolean isValid(String idInput, String pwInput) {
        //Database db = Database.getInstance();

        return false;
    }
}

package model;

public class SignupValidator implements Runnable {
    private String idInput;
    private String pwInput;
    private Database db;
    private Thread thr;

    @Override
    public void run() {

    }

    public boolean isValidId(String id) {
        return false;
    }

    public boolean isDuplicate(String ID) {
        return false;
    }

    public void confirm(String id, String pw) {

    }
}

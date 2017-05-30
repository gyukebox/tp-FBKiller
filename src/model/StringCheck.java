package model;

public class StringCheck extends AdCheck {
    private java.util.ArrayList<String> bannedWords;
    private WordFilter filter;

    public boolean check(String str) {
        return false;
    }

    public boolean ban(Article article) {
        return false;
    }
}

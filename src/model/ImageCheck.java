package model;

public class ImageCheck extends AdCheck {
    private java.awt.Image image;

    public boolean adultContent(String url) {
        return false;
    }

    public boolean isRacist(String url) {
        return false;
    }

    public boolean banned(String url) {
        return false;
    }

    public boolean ban(Article article) {
        return false;
    }
}

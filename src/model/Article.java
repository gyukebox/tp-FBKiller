package model;

public class Article {
    public Database db;
    private AdCheck check;

    public Article() {
    }

    public boolean submit() {
        //1. title == null? body == null?
        //2. ad? (AdCheck class의 메소드를 실행)
        //db connection & insertion
        //4. 다시 ArticleRequest 로 보냄

        return false; //temp
    }

    //수정시에는 웹 화면에서 수정전 데이터가 보여진다
    public void edit() {
        this.submit();
    }

    public void delete() {
        //1. 고냥 DB에서 지운다
        //2. 다시 ArticleRequest로 보냄
    }
}

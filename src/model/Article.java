package model;

public class Article {
    private String title;
    private String body;
    private String userid;
    private Database db;
    private AdCheck check;

    public Article() {

    }

    public Article(String title, String body, String userid) {
        this.title = title;
        this.body = body;
        this.userid = userid;
    }

    public boolean submit() {
        boolean valid = true;
        //1. title == null? body == null?
        if (title == null || body == null) {
            return false;
        }
        //2. ad? (AdCheck class의 메소드를 실행)


        //db connection & insertion
        db = new ArticleDB();
        db.connect();
        db.insert(title, body, null);
        db.closeDB();

        return valid; //temp
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

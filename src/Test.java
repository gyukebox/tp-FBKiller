// contains test code for servlets

import model.Article;
import model.User;
import model.UserDB;

public class Test {
    public static void main(String[] args) {
        User testUser = new User("test", "테스트", "test", "test.jpg", "01011111111", 'M');

        // "토토" 가 금지어이므로 금지어를 포함한 글이 필터링 되는지 테스트
        Article article = new Article("테스트 용", "토토 사이트", testUser);

        UserDB db = new UserDB();
        db.connect();
        article.submit();
        db.closeDB();
    }
}


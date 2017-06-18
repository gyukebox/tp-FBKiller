package controller;

import model.Article;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/Article")
@MultipartConfig
public class ArticleRequest extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("subject");
        String body = req.getParameter("body");
        HttpSession session = req.getSession();
        User author = (User) session.getAttribute("user");

        Article article = new Article(title, body, author);


        article.submit();
        resp.sendRedirect("/timeline.jsp");
    }
}
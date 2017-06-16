package controller;

import model.RegisterValidator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/Register")
public class RegisterRequest extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("ID");
        String pw = req.getParameter("PW");
        String hp = req.getParameter("HP");
        char gender;
        if (req.getParameter("gender").equals("남")) {
            gender = 'M';
        } else {
            gender = 'W';
        }

        RegisterValidator validator = new RegisterValidator(id, pw, hp, gender);

        if (validator.confirm()) {
            //redirect to timeline
            System.out.println("sign up successfully done");
            resp.sendRedirect("/timeline.jsp");
        } else {
            //redirect to error page
            System.out.println("sign up failed");
            resp.sendRedirect("/login.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}

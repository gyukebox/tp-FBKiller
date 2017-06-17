package controller;

import model.RegisterValidator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.nio.file.Paths;

@WebServlet("/Register")
public class RegisterRequest extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("ID");
        String username = req.getParameter("name");
        String pw = req.getParameter("PW");
        String hp = req.getParameter("HP");
        Part filePart = req.getPart("profilepic");
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

        //test
        System.out.println(fileName);

        char gender;
        if (req.getParameter("gender").equals("남")) {
            gender = 'M';
        } else {
            gender = 'W';
        }

        req.getRequestDispatcher("/Fileupload").include(req, resp);
        RegisterValidator validator = new RegisterValidator(id, username, pw, fileName, hp, gender);

        if (validator.confirm()) {
            //redirect to timeline
            System.out.println("sign up successfully done");
            resp.sendRedirect("/index.jsp");
        } else {
            //redirect to error page
            System.out.println("sign up failed");
            resp.sendRedirect(req.getHeader("referer"));
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}

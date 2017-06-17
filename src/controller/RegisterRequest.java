package controller;

import model.RegisterValidator;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.nio.file.Paths;

@WebServlet("/Register")
@MultipartConfig
public class RegisterRequest extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("ID");
        String username = req.getParameter("name");
        username = new String(username.getBytes("8859_1"), "utf-8");
        String pw = req.getParameter("PW");
        String hp = req.getParameter("HP");
        String genderInput = req.getParameter("gender");
        char gender;

        // test
        System.out.println(id + " " + username + " " + pw + " " + hp + " " + genderInput);

        if (genderInput.equals("남")) {
            gender = 'M';
        } else {
            gender = 'W';
        }

        User user = new User(id, username, pw, null, hp, gender);
        RegisterValidator validator = new RegisterValidator(user);

        if (validator.confirm()) {

            /*
            req.getRequestDispatcher("/Fileupload").include(req, resp);
            Part filePart = req.getPart("profilepic");
            String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
            System.out.println(fileName); //test

            user.setProfilePicture(fileName);
            */
            validator.submit();

            System.out.println("sign up successfully done");
            resp.sendRedirect("/index.jsp");
        } else {
            //redirect to error page
            System.out.println("sign up failed");
            resp.sendRedirect(req.getHeader("referer"));
        }
    }
}

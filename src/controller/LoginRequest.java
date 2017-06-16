package controller;

import model.LoginValidator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/Login")
public class LoginRequest extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String pw = request.getParameter("pw");

        LoginValidator validator = new LoginValidator(id, pw);
        if (validator.isValid()) {
            HttpSession session = request.getSession();
            session.setAttribute("id", id);
            session.setAttribute("pw", pw);
            response.sendRedirect("/timeline.jsp");
        } else {
            System.out.println("id or password is incorrect");
            this.doPost(request, response);
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        request.setAttribute("error", "Incorrect ID or Password");
        RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
        rd.include(request, response);
    }
}

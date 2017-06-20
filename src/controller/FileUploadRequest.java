package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.nio.file.Paths;

@WebServlet("/Fileupload")
@MultipartConfig
public class FileUploadRequest extends HttpServlet {
    private final String filePath = "/Users/gyukebox/school/2-1/ca/teamproject/teamproject/web/files/";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Part filePart = req.getPart("image");
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        if(!fileName.equals("")) {
            filePart.write(filePath + fileName);
        }

        HttpSession session = req.getSession();
        session.setAttribute("filename", fileName);

        resp.sendRedirect(req.getHeader("referer"));
    }
}

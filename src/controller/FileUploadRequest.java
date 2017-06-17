package controller;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

@WebServlet("/Fileupload")
public class FileUploadRequest extends HttpServlet {
    private final String filePath = "/Users/gyukebox/school/2-1/ca/teamproject/teamproject/files/";
    private final int maxFileSize = 1024 * 1024 * 512;
    private final int maxMemorySize = 1024 * 1024 * 4;
    private boolean isMultiPart;
    private File file;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // do file upload
        isMultiPart = ServletFileUpload.isMultipartContent(req);
        if (!isMultiPart) {
            // error handling?
        }

        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(maxMemorySize);
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setSizeMax(maxFileSize);

        try {
            List fileItems = upload.parseRequest(req);
            for (Object object : fileItems) {
                FileItem item = (FileItem) object;
                if (!item.isFormField()) {
                    String filename = item.getName();
                    if (filename.lastIndexOf("/") >= 0) {
                        file = new File(filePath + filename.substring(filename.lastIndexOf("/")));
                    } else {
                        file = new File(filePath + filename.substring(filename.lastIndexOf("/") + 1));
                    }
                    item.write(file);
                }
            }
        } catch (Exception e) {
            System.out.println("file upload failed");
            e.printStackTrace();
        }

        resp.sendRedirect(req.getHeader("referer"));
    }
}

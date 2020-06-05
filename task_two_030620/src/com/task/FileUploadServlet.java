package com.task;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@WebServlet(name = "FileUploadServlet", urlPatterns = {"/FileUploadServlet"},
        initParams = { @WebInitParam(name = "path", value = "C://Uploads/") })
@MultipartConfig
public class FileUploadServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        Part part = request.getPart("file");

        if (part.getSize() == 0) {
            response.getOutputStream().print("\n\nFile is null!");
        } else {
            try (InputStream fileInputStream = part.getInputStream();
                 ServletOutputStream servletOutputStream = response.getOutputStream()) {

                ServletConfig sc = getServletConfig();
                String path = sc.getInitParameter("path");

                String fileName = part.getSubmittedFileName();

                Files.copy(fileInputStream, Paths.get(path + fileName),
                        StandardCopyOption.REPLACE_EXISTING);

                servletOutputStream.print("\n\nFile successfully uploaded: " + path + fileName + "\n");

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

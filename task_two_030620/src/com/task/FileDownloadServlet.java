package com.task;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@WebServlet(name = "FileDownloadServlet", urlPatterns = {"/FileDownloadServlet"})
public class FileDownloadServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String appPath = getClass().getResource("/").getPath();

        File file = new File( appPath + "/resources/test_file.txt");

        try (FileInputStream fileInputStream = new FileInputStream(file);
             ServletOutputStream servletOutputStream = response.getOutputStream()) {

            byte[] out = new byte[1024];
            while (fileInputStream.read(out) != -1) {
                servletOutputStream.write(out);
            }

            } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

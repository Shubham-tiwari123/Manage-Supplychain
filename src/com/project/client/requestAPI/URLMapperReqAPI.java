package com.project.client.requestAPI;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "URLMapperReqAPI", urlPatterns = {"/dashboard","/prepare_block","/prepare_block2",
        "/prepare_block4","/prepare_block3"})
public class URLMapperReqAPI extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if((request.getRequestURL().toString()).equals("http://localhost:8081/dashboard"))
            request.getRequestDispatcher("/dashboard.jsp").forward(request,response);
        else if((request.getRequestURL().toString()).equals("http://localhost:8081/prepare_block"))
            request.getRequestDispatcher("/prepareblock.jsp").forward(request,response);
        else if((request.getRequestURL().toString()).equals("http://localhost:8081/prepare_block2"))
            request.getRequestDispatcher("/prepareblock2.jsp").forward(request,response);
        else if((request.getRequestURL().toString()).equals("http://localhost:8081/prepare_block3"))
            request.getRequestDispatcher("/prepareblock3.jsp").forward(request,response);
        else if((request.getRequestURL().toString()).equals("http://localhost:8081/prepare_block4"))
            request.getRequestDispatcher("/prepareblock4.jsp").forward(request,response);
    }
}

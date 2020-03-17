package com.project.server.requestAPI;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "URLMapperReqAPI",urlPatterns = {"/dashboard","/team","/database",
        "/sign_up","/sign_in","/detail_view"})

public class URLMapperReqAPI extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if((request.getRequestURL().toString()).equals("http://localhost:8082/dashboard"))
            request.getRequestDispatcher("/dashboard.jsp").forward(request,response);
        else if((request.getRequestURL().toString()).equals("http://localhost:8082/team"))
            request.getRequestDispatcher("/team.jsp").forward(request,response);
        else if((request.getRequestURL().toString()).equals("http://localhost:8082/database"))
            request.getRequestDispatcher("/database.jsp").forward(request,response);
        else if((request.getRequestURL().toString()).equals("http://localhost:8082/sign_up"))
            request.getRequestDispatcher("/").forward(request,response);
        else if((request.getRequestURL().toString()).equals("http://localhost:8082/sign_in"))
            request.getRequestDispatcher("/").forward(request,response);
        else if((request.getRequestURL().toString()).equals("http://localhost:8082/detail_view"))
            request.getRequestDispatcher("/detailview.jsp").forward(request,response);
    }
}

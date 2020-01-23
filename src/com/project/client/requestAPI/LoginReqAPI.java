package com.project.client.requestAPI;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LoginReqAPI",urlPatterns = {"/dashboard"})

public class LoginReqAPI extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("userEmail");
        String pass = request.getParameter("userPass");

        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("fjhghfgdfdsasertgv");
        request.getRequestDispatcher("/dashboard.jsp").forward(request,response);
    }
}

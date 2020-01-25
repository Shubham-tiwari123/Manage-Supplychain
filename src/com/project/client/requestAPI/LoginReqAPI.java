package com.project.client.requestAPI;

import com.project.client.services.CommonFunction;
import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LoginReqAPI",urlPatterns = {"/dashboard","/login-user"})

public class LoginReqAPI extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response){
        try {
            CommonFunction commonFunction = new CommonFunction();

            String email = request.getParameter("email");
            String pass = request.getParameter("pass");
            System.out.println("value" + email + "   " + pass);

            Cookie cookie;
            //check if keys or not exists
            if (commonFunction.checkIfKeysExists()) {
                cookie = new Cookie("keyStatus","true");
            }else{
                cookie = new Cookie("keyStatus","false");
            }
            response.addCookie(cookie);
        }catch (Exception e){
            System.out.println(e);
        }

    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("fjhghfgdfdsasertgv");
        request.getRequestDispatcher("/dashboard.jsp").forward(request,response);
    }
}

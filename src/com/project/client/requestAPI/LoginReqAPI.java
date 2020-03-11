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
import java.io.PrintWriter;

@WebServlet(name = "LoginReqAPI",urlPatterns = {"/login"})

public class LoginReqAPI extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response){
        try {
            CommonFunction commonFunction = new CommonFunction();

            String email = request.getParameter("email");
            String pass = request.getParameter("pass");
            System.out.println("value" + email + "   " + pass);

            Cookie cookie;
            JSONObject jsonObject = new JSONObject();
            //check if keys or not exists
            if (commonFunction.checkIfKeysExists()) {
                cookie = new Cookie("loginStatus","true");
                jsonObject.put("statusCode",200);
            }else{
                cookie = new Cookie("keyStatus","false");
                jsonObject.put("statusCode",400);
            }
            response.addCookie(cookie);
            PrintWriter printWriter = response.getWriter();
            printWriter.println(jsonObject.toString());
        }catch (Exception e){
            System.out.println(e);
        }

    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
}

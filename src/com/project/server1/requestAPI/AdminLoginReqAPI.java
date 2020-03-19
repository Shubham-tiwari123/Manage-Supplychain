package com.project.server1.requestAPI;

import com.project.server1.utils.ConstantClass;
import org.json.simple.JSONObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@WebServlet(name = "AdminLoginReqAPI", urlPatterns = {"/admin_login"})
public class AdminLoginReqAPI extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response){
        try {
            String email = request.getParameter("email");
            String pass = request.getParameter("pass");
            System.out.println("value" + email + "   " + pass);

            Cookie cookie;
            JSONObject jsonObject = new JSONObject();

            if (email.equals(ConstantClass.ADMIN_LOGIN_EMAIL) &&
                    pass.equals(ConstantClass.ADMIN_LOGIN_PASS)) {
                cookie = new Cookie("adminLoginStatus","true");
                jsonObject.put("statusCode",200);
            }else{
                cookie = new Cookie("adminLoginStatus","false");
                jsonObject.put("statusCode",400);
            }
            response.addCookie(cookie);
            PrintWriter printWriter = response.getWriter();
            printWriter.println(jsonObject.toString());
        }catch (Exception e){
            System.out.println(e);
        }

    }
}

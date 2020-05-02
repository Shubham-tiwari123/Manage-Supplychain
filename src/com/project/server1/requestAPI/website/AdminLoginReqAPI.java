package com.project.server1.requestAPI.website;

import com.project.server1.utils.ConstantClass;
import org.json.simple.JSONObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "AdminLoginReqAPI")
public class AdminLoginReqAPI extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            System.out.println("Server hit");
            String email = request.getParameter("email");
            String pass = request.getParameter("pass");
            System.out.println("value" + email + "   " + pass);

            Cookie cookie;
            JSONObject jsonObject = new JSONObject();

            if (email.equals(ConstantClass.ADMIN_LOGIN_EMAIL) &&
                    pass.equals(ConstantClass.ADMIN_LOGIN_PASS)) {
                cookie = new Cookie("adminLoginStatus","true");
                jsonObject.put("statusCode",ConstantClass.SUCCESSFUL);
            }else{
                cookie = new Cookie("adminLoginStatus","false");
                jsonObject.put("statusCode",ConstantClass.FAILED);
            }
            response.addCookie(cookie);
            PrintWriter printWriter = response.getWriter();
            printWriter.println(jsonObject.toString());
        }catch (Exception e){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("statusCode",ConstantClass.BAD_REQUEST);
            PrintWriter printWriter = response.getWriter();
            printWriter.println(jsonObject.toString());
            System.out.println(e);
        }

    }
}

package com.project.server1.responseAPI.android;

import org.json.simple.JSONObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "LoginUserResAPI")
public class LoginUserResAPI extends HttpServlet {
    public void sendResponse(HttpServletResponse response,int statusCode,String userDetails) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("statusCode",statusCode);
        if(userDetails!=null)
            jsonObject.put("userDetails",userDetails);
        PrintWriter writer = response.getWriter();
        writer.println(jsonObject.toString());
    }
}

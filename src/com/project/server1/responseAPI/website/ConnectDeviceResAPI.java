package com.project.server1.responseAPI.website;

import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ConnectDeviceResAPI")
public class ConnectDeviceResAPI extends HttpServlet {

    public void sendResponse(HttpServletResponse response,int statusCode) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("statusCode",statusCode);
        PrintWriter writer = response.getWriter();
        writer.println(jsonObject.toString());
    }
}

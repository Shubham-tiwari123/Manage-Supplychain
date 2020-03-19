package com.project.server1.responseAPI;

import org.json.simple.JSONObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "AcceptBlockResAPI")
public class AcceptBlockResAPI extends HttpServlet {

    private static int statusCode;

    public void sendResponse(int statusCode, HttpServletResponse response)
            throws IOException {
        AcceptBlockResAPI.statusCode = statusCode;
        doPost(response);
    }

    private void doPost(HttpServletResponse response) throws IOException {
        System.out.println("Sending response to client");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("statusCode",statusCode);
        PrintWriter writer = response.getWriter();
        writer.print(jsonObject.toString());
    }
}

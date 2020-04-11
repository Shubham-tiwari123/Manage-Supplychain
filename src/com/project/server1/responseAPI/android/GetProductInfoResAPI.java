package com.project.server1.responseAPI.android;

import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "GetProductInfoResAPI")
public class GetProductInfoResAPI extends HttpServlet {
    public void sendResponse(HttpServletResponse response, int statusCode, String productData)
            throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("statusCode",statusCode);
        if(productData!=null)
            jsonObject.put("productData",productData);
        PrintWriter writer = response.getWriter();
        writer.println(jsonObject.toString());
    }
}

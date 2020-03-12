package com.project.client.requestAPI;

import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "GetBlockIDReqAPI",urlPatterns = {"/get-id"})

public class GetBlockIDReqAPI extends HttpServlet {
    static int count=4;
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter writer = response.getWriter();

        JSONObject jsonObject = new JSONObject();
        if (count%2==0){
            jsonObject.put("statusCode",200);
            jsonObject.put("productID",123456789);
        }else{
            jsonObject.put("statusCode",400);
        }
        count++;
        writer.println(jsonObject.toString());
    }
}

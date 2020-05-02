package com.project.server1.requestAPI.website;

import org.json.simple.JSONObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@WebServlet(name = "ConnectDeviceReqAPI")
public class ConnectDeviceReqAPI extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response){
        try {
            System.out.println("Server hit");
            PrintWriter writer = response.getWriter();
            SocketThread socketThread = new SocketThread(response);
            socketThread.start();
            JSONObject resObject = new JSONObject();
            System.out.println("preparing response");
            resObject.put("status", 200);
            System.out.println("Sending socket status.....");
            writer.println(resObject);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

package com.project.server.requestAPI;

import com.project.server.services.ConnectToDevice;
import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

@WebServlet(name = "ConnectDeviceResAPI",urlPatterns = {"/connect-device"})
public class ConnectDeviceReqAPI extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response){
        try {
            System.out.println("Server hit");
            PrintWriter writer = response.getWriter();
            SocketThread socketThread = new SocketThread();
            socketThread.start();
            JSONObject resObject = new JSONObject();
            resObject.put("status", 200);
            System.out.println("Sending socket status.....");
            writer.println(resObject.toString());
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}

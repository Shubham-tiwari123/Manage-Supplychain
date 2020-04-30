package com.project.client.responseAPI;

import com.project.client.requestAPI.GetPublicKeysReqAPI;
import com.project.client.utils.ConstantClass;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;

@WebServlet(name = "ConnectToServerResAPI")
public class ConnectToServerResAPI extends HttpServlet {

    public void readResponse(HttpServletResponse response, HttpServletRequest request, int statusCode)
            throws IOException {
        System.out.println("reading response");
        try {
            JSONObject object = new JSONObject();
            if(statusCode==ConstantClass.SUCCESSFUL){
                GetPublicKeysReqAPI reqAPI = new GetPublicKeysReqAPI();
                reqAPI.doGet(request,response);
            }else{
                object.put("statusCode",ConstantClass.FAILED);
                PrintWriter writer = response.getWriter();
                writer.println(object.toJSONString());
            }
        } catch (Exception e) {
            System.out.println("Something went wrong try again......");
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("statusCode", ConstantClass.BAD_REQUEST);
            PrintWriter printWriter = response.getWriter();
            printWriter.println(jsonObject.toString());
            e.printStackTrace();
        }
    }

}

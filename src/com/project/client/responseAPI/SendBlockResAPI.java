package com.project.client.responseAPI;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;

@WebServlet(name = "SendBlockResAPI")
public class SendBlockResAPI extends HttpServlet {

    private static int statusCode;

    public void readResponse(HttpServletResponse response, HttpURLConnection conn){
        System.out.println("reading response");
        try {
            InputStream inputStream = conn.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuffer output = new StringBuffer();
            String s = "";
            while ((s = bufferedReader.readLine()) != null)
                output.append(s);
            System.out.println("data:\n"+output.toString());
            JSONParser jsonParser = new JSONParser();
            JSONObject object = (JSONObject) jsonParser.parse(output.toString());
            long status = (long) object.get("status");

            JSONObject object1 = new JSONObject();
            if(status==200){
                statusCode = 200;
                object.put("statusCode",statusCode);
            }else{
                statusCode = 404;
                object.put("statusCode",statusCode);
            }
            PrintWriter writer = response.getWriter();
            writer.println(object.toJSONString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void doPost(HttpServletResponse response) throws ServletException, IOException {
        PrintWriter writer = response.getWriter();
        writer.println(statusCode);
    }
}

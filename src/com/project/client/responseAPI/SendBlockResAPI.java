package com.project.client.responseAPI;

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

@WebServlet(name = "SendBlockResAPI")
public class SendBlockResAPI extends HttpServlet {

    public void readResponse(HttpServletResponse response, HttpURLConnection conn) throws IOException {
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
            long status = (long) object.get("statusCode");

            JSONObject object1 = new JSONObject();
            int statusCode;
            if(status==200){
                statusCode = ConstantClass.SUCCESSFUL;
            }else{
                statusCode = ConstantClass.FAILED;
            }
            object1.put("statusCode", statusCode);
            System.out.println("statussss:"+statusCode);
            PrintWriter writer = response.getWriter();
            writer.println(object1.toJSONString());
        } catch (Exception e) {
            System.out.println("Something went wrong try again......");
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("statusCode",ConstantClass.BAD_REQUEST);
            PrintWriter printWriter = response.getWriter();
            printWriter.println(jsonObject.toString());
            e.printStackTrace();
        }
    }
}

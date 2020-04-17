package com.project.client.requestAPI;

import com.project.client.responseAPI.GetBlockIDResAPI;
import com.project.client.responseAPI.SendBlockResAPI;
import com.project.client.utils.ConstantClass;
import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "GetBlockIDReqAPI")

public class GetBlockIDReqAPI extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String blockNumber = request.getParameter("blockNumber");
        JSONObject result = new JSONObject();
        result.put("blockNumber",blockNumber);
        result.put("signature","signature");
        try {
            /*URL url = new URL(ConstantClass.GET_PRODUCT_ID_URL1);*/
            URL url;
            if(ConstantClass.SERVER1_STATUS) {
                System.out.println("trying server1 getID:"+ConstantClass.SERVER1_TRY);
                url = new URL(ConstantClass.GET_PRODUCT_ID_URL1);
            }
            else {
                System.out.println("trying server2 getID: "+ConstantClass.SERVER2_TRY);
                url = new URL(ConstantClass.GET_PRODUCT_ID_URL2);
            }
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            OutputStream output = conn.getOutputStream();
            OutputStreamWriter outputWriter = new OutputStreamWriter(output, StandardCharsets.UTF_8);
            outputWriter.write(result.toString());
            outputWriter.flush();
            outputWriter.close();
            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                System.out.println("server hit");
                GetBlockIDResAPI resAPI = new GetBlockIDResAPI();
                resAPI.readResponse(response, conn);
            }
        }catch (Exception e){
            if(ConstantClass.SERVER1_TRY<2) {
                if(ConstantClass.SERVER1_TRY==1) {
                    System.out.println("Switching to server 2");
                    ConstantClass.SERVER1_STATUS = false;
                    ConstantClass.SERVER2_STATUS = true;
                    ConstantClass.SERVER2_TRY=0;
                }
                ConstantClass.SERVER1_TRY = ConstantClass.SERVER1_TRY + 1;
            }
            else if(ConstantClass.SERVER2_TRY<2 && ConstantClass.SERVER1_TRY==2){
                if(ConstantClass.SERVER2_TRY==1){
                    System.out.println("Switching to server 1");
                    ConstantClass.SERVER2_STATUS=false;
                    ConstantClass.SERVER1_STATUS=true;
                    ConstantClass.SERVER1_TRY=0;
                }
                ConstantClass.SERVER2_TRY = ConstantClass.SERVER2_TRY+1;
            }
            System.out.println("Something went wrong try again......");
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("statusCode",ConstantClass.FAILED);
            PrintWriter printWriter = response.getWriter();
            printWriter.println(jsonObject.toString());
            e.printStackTrace();
        }
    }
}

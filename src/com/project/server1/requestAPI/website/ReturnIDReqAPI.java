package com.project.server1.requestAPI.website;

import com.project.server1.dao.SqlDB;
import com.project.server1.responseAPI.website.ReturnIDResAPI;
import com.project.server1.services.CommonFunctions;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet(name = "ReturnIDReqAPI")

public class ReturnIDReqAPI extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("server hit get id");
        CommonFunctions commonFunctions = new CommonFunctions();
        StringBuilder buffer = new StringBuilder();
        BufferedReader reader = request.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            buffer.append(line);
        }
        String data = buffer.toString();
        System.out.println("data4:-" + data);
        JSONParser parser = new JSONParser();
        try {
            JSONObject jSONObject = (JSONObject) parser.parse(data);
            String pcNumber = (String) jSONObject.get("blockNumber");
            String signature = (String) jSONObject.get("signature");
            SqlDB sqlDB = new SqlDB();
            long productId;
            if(pcNumber.equals("block1")){
                productId = sqlDB.createNewProductID();
            }else {
                 productId = sqlDB.getProductID(pcNumber);
            }
            ReturnIDResAPI resAPI = new ReturnIDResAPI();
            resAPI.sendResponse(response,productId);
        }catch (Exception e){
            System.out.println("exception e");
            ReturnIDResAPI resAPI = new ReturnIDResAPI();
            resAPI.sendResponse(response,-1);
        }

    }
}

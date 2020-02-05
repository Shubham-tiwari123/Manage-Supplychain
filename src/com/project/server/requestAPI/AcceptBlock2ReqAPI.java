package com.project.server.requestAPI;

import com.project.server.entity.ClientKeys;
import com.project.server.entity.DeserializeValues;
import com.project.server.services.AcceptBlock;
import com.project.server.services.CommonFunctions;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "AcceptBlock2ReqAPI", urlPatterns = {"/second-block"})
public class AcceptBlock2ReqAPI extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("server hit2");
        AcceptBlock acceptBlock = new AcceptBlock();
        CommonFunctions commonFunctions = new CommonFunctions();
        StringBuilder buffer = new StringBuilder();
        BufferedReader reader = request.getReader();
        String line;
        while((line = reader.readLine())!= null) {
            buffer.append(line);
        }
        String data = buffer.toString();
        System.out.println("data:-"+data);
        JSONParser parser = new JSONParser();

        try {
            JSONObject jSONObject = (JSONObject) parser.parse(data);
            String encryptedString = (String) jSONObject.get("encryptedData");
            String signature = (String) jSONObject.get("signature");

            DeserializeValues deserializeValues = commonFunctions.convertJsonToJava(encryptedString,
                    DeserializeValues.class);

            ClientKeys clientKeys = acceptBlock.getClientKeys(signature);
            String decryptString = acceptBlock.decryptData(deserializeValues.getEncryptedData(),clientKeys);
            System.out.println("dec:"+decryptString);
            jSONObject = (JSONObject) parser.parse(decryptString);
            String currentHashBlock = (String) jSONObject.get("currentBlockHash");
            System.out.println("curr:"+currentHashBlock);
            PrintWriter writer = response.getWriter();
            JSONObject resultObj = new JSONObject();

            if(acceptBlock.verifyData2(decryptString,currentHashBlock)){
                System.out.println("Data is secured");
                resultObj.put("status",200);
            }else{
                System.out.println("Data not secured");
                resultObj.put("status",400);
            }
            writer.print(resultObj);
        }catch (Exception e){
            System.out.println(e);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}

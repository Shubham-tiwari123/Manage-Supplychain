package com.project.server1.requestAPI.android;

import com.project.server1.entity.SendData;
import com.project.server1.entity.ServerKeys;
import com.project.server1.responseAPI.android.GetProductInfoResAPI;
import com.project.server1.services.AndroidFunction;
import com.project.server1.services.CommonFunctions;
import com.project.server1.utils.ConstantClass;
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
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "GetProductInfoReqAPI", urlPatterns = {"/get_android_product"})

public class GetProductInfoReqAPI extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            GetProductInfoResAPI resAPI = new GetProductInfoResAPI();
            AndroidFunction androidFunction = new AndroidFunction();
            CommonFunctions commonFunctions = new CommonFunctions();
            StringBuilder buffer = new StringBuilder();
            BufferedReader reader = request.getReader();
            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            String data = buffer.toString();
            System.out.println("datassss:-" + data);
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(data);
            Long productID = (Long) jsonObject.get("productID");
            ArrayList<ArrayList<byte[]>> getProductInfo = androidFunction.getProductInfo(productID);
            if(getProductInfo.size()>0) {
                ServerKeys serverKeys = androidFunction.getServerKeys();
                List<String> decryptedData = androidFunction.decryptData(getProductInfo, serverKeys);
                if (decryptedData.size() == 5) {
                    SendData sendData = androidFunction.prepareDataToSend(decryptedData);
                    String sendUserData = commonFunctions.convertJavaToJson(sendData);
                    resAPI.sendResponse(response, ConstantClass.SUCCESSFUL, sendUserData);
                }
                else{
                    resAPI.sendResponse(response, ConstantClass.FAILED,null);
                }
            }
            else{
                resAPI.sendResponse(response, ConstantClass.BAD_REQUEST,null);
            }
        }catch (Exception e){
            System.out.println(e);
            GetProductInfoResAPI resAPI = new GetProductInfoResAPI();
            resAPI.sendResponse(response, ConstantClass.BAD_REQUEST,null);
        }
    }
}

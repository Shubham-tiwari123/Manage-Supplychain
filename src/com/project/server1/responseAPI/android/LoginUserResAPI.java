package com.project.server1.responseAPI.android;

import com.project.server1.entity.AndroidUserKeys;
import com.project.server1.services.CommonFunctions;
import org.json.simple.JSONObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "LoginUserResAPI")
public class LoginUserResAPI extends HttpServlet {
    public void sendResponse(HttpServletResponse response,int statusCode,String userDetails,String keys) throws IOException {
        JSONObject jsonObject = new JSONObject();
        CommonFunctions commonFunctions = new CommonFunctions();
        jsonObject.put("statusCode",statusCode);
        JSONObject clientKeys = new JSONObject();
        if(userDetails!=null){
            try {
                AndroidUserKeys userKeys = commonFunctions.convertJsonToJava(keys,AndroidUserKeys.class);
                clientKeys.put("publicKeyModules",String.valueOf(userKeys.getPublicKeyModules()).substring(0,80));
                clientKeys.put("publicKeyExpo",String.valueOf(userKeys.getPublicKeyExpo()));
                clientKeys.put("privateKeyModules",String.valueOf(userKeys.getPrivateKeyModules()).substring(0,80));
                clientKeys.put("privateKeyExpo",String.valueOf(userKeys.getPrivateKeyExpo()).substring(0,80));
            } catch (Exception e) {
                e.printStackTrace();
            }
            jsonObject.put("clientKeys",clientKeys.toString());
        }
            jsonObject.put("userDetails",userDetails);
        PrintWriter writer = response.getWriter();
        writer.println(jsonObject.toString());
    }
}

package com.project.server1.responseAPI.android;

import com.project.server1.entity.AndroidUserKeys;
import com.project.server1.entity.RegisterUser;
import com.project.server1.services.CommonFunctions;
import org.json.simple.JSONObject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "RegisterUserResAPI")
public class RegisterUserResAPI extends HttpServlet {
    public void sendResponse(HttpServletResponse response, int statusCode, String keys)
            throws IOException {
        CommonFunctions commonFunctions = new CommonFunctions();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("statusCode",statusCode);
        JSONObject clientKeys = new JSONObject();
        /*if(keys!=null) {
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
        }*/
        PrintWriter writer = response.getWriter();
        writer.println(jsonObject.toString());
    }
}

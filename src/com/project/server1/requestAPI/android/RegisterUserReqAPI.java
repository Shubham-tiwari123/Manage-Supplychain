package com.project.server1.requestAPI.android;

import com.project.server1.entity.AndroidUserKeys;
import com.project.server1.entity.RegisterUser;
import com.project.server1.responseAPI.android.RegisterUserResAPI;
import com.project.server1.services.AndroidFunction;
import com.project.server1.services.CommonFunctions;
import com.project.server1.utils.ConstantClass;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet(name = "RegisterUserReqAPI", urlPatterns = {"/register_android_user"})
public class RegisterUserReqAPI extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            CommonFunctions commonFunctions = new CommonFunctions();
            RegisterUserResAPI resAPI = new RegisterUserResAPI();
            AndroidFunction androidFunction = new AndroidFunction();
            StringBuilder buffer = new StringBuilder();
            BufferedReader reader = request.getReader();
            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            String data = buffer.toString();
            System.out.println("datassss:-" + data);

            RegisterUser registerUser = commonFunctions.convertJsonToJava(data,RegisterUser.class);
            if(androidFunction.registerUser(registerUser)){
                AndroidUserKeys userKeys = androidFunction.generateKeys(registerUser.getEmail());
                String clientKeys = commonFunctions.convertJavaToJson(userKeys);
                if(androidFunction.storeKeys(registerUser.getEmail(),userKeys))
                    resAPI.sendResponse(response,ConstantClass.SUCCESSFUL,clientKeys);
                else
                    resAPI.sendResponse(response,ConstantClass.FAILED,null);
            }
            else
                resAPI.sendResponse(response,ConstantClass.FAILED,null);

        }catch (Exception e){
            System.out.println(e);
            RegisterUserResAPI resAPI = new RegisterUserResAPI();
            resAPI.sendResponse(response,ConstantClass.BAD_REQUEST,null);
        }
    }

}

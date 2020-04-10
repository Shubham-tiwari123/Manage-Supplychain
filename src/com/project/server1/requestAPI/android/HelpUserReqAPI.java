package com.project.server1.requestAPI.android;

import com.project.server1.entity.RegisterComplain;
import com.project.server1.responseAPI.android.HelpUserResAPI;
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

@WebServlet(name = "HelpUserReqAPI", urlPatterns = {"/submit_complain"})
public class HelpUserReqAPI extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            HelpUserResAPI resAPI = new HelpUserResAPI();
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
            RegisterComplain registerComplain = commonFunctions.convertJsonToJava(data,RegisterComplain.class);
            if(androidFunction.registerComplain(registerComplain))
                resAPI.sendResponse(response,ConstantClass.SUCCESSFUL);
            else
                resAPI.sendResponse(response, ConstantClass.FAILED);
        }catch (Exception e){
            System.out.println(e);
            HelpUserResAPI resAPI = new HelpUserResAPI();
            resAPI.sendResponse(response,ConstantClass.BAD_REQUEST);
        }
    }
}

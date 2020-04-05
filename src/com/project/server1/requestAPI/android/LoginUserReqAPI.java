package com.project.server1.requestAPI.android;

import com.project.server1.responseAPI.android.LoginUserResAPI;
import com.project.server1.utils.ConstantClass;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet(name = "LoginUserReqAPI",urlPatterns = {"/login_android_user"})
public class LoginUserReqAPI extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            LoginUserResAPI resAPI = new LoginUserResAPI();
            StringBuilder buffer = new StringBuilder();
            BufferedReader reader = request.getReader();
            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            String data = buffer.toString();
            System.out.println("datassss:-" + data);
            JSONParser jsonParser = new JSONParser();
            JSONObject jSONObject = (JSONObject) jsonParser.parse(data);
            String email = (String) jSONObject.get("email");
            String pass = (String) jSONObject.get("pass");
            System.out.println("email:"+email+" pass:"+pass);
            if(email.equals("s@gmail.com") && pass.equals("qwe"))
                resAPI.sendResponse(response,ConstantClass.SUCCESSFUL);
            else
                resAPI.sendResponse(response, ConstantClass.FAILED);
        }catch (Exception e){
            System.out.println(e);
            LoginUserResAPI resAPI = new LoginUserResAPI();
            resAPI.sendResponse(response,ConstantClass.BAD_REQUEST);
        }

    }
}

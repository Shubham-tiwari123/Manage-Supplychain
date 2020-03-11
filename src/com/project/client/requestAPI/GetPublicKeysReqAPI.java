package com.project.client.requestAPI;

import com.project.client.dao.Database;
import com.project.client.entity.ClientKeys;
import com.project.client.entity.ServerKeys;
import com.project.client.utils.VariableClass;
import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "GetPublicKeysReqAPI",urlPatterns = {"/get-keys"})
public class GetPublicKeysReqAPI extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response){
        try {
            Database database = new Database();
            ClientKeys clientKeys = database.getClientKeys(VariableClass.STORE_KEYS);
            ServerKeys serverKeys = database.getServerKeys(VariableClass.STORE_KEYS);
            JSONObject object = new JSONObject();

            if(clientKeys!=null || serverKeys!=null) {
                String server = serverKeys.getPublicKeyModules().toString().substring(0, 80);
                String client = clientKeys.getPublicKeyModules().toString().substring(0, 80);
                object.put("PC", client);
                object.put("Server", server);
                object.put("statusCode", 200);
            }
            else {
                object.put("statusCode", 301);
            }
            PrintWriter writer = response.getWriter();
            writer.println(object.toJSONString());
        }catch (Exception e){
            System.out.println(e);
        }
    }
}

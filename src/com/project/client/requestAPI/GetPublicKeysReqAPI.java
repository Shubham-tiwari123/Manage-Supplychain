package com.project.client.requestAPI;

import com.project.client.dao.Database;
import com.project.client.entity.ClientKeys;
import com.project.client.entity.ServerKeys;
import com.project.client.utils.ConstantClass;
import org.json.simple.JSONObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "GetPublicKeysReqAPI")
public class GetPublicKeysReqAPI extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            Database database = new Database();
            ClientKeys clientKeys = database.getClientKeys(ConstantClass.STORE_KEYS);
            ServerKeys serverKeys = database.getServerKeys(ConstantClass.STORE_KEYS);
            JSONObject object = new JSONObject();

            if(clientKeys!=null && serverKeys!=null) {
                String server = serverKeys.getPublicKeyModules().toString().substring(0, 80);
                String client = clientKeys.getPublicKeyModules().toString().substring(0, 80);
                object.put("PC", client);
                object.put("Server", server);
                object.put("statusCode", ConstantClass.SUCCESSFUL);
            }else if (clientKeys==null && serverKeys!=null){
                // delete server keys
                database.deleteServerKeys(ConstantClass.STORE_KEYS);
                object.put("statusCode", ConstantClass.FAILED);
            }else if (clientKeys != null && serverKeys==null){
                // delete client keys
                database.deleteClientKeys(ConstantClass.STORE_KEYS);
                object.put("statusCode", ConstantClass.FAILED);
            }
            else {
                object.put("statusCode", ConstantClass.FAILED);
            }
            PrintWriter writer = response.getWriter();
            writer.println(object.toJSONString());
        }catch (Exception e){
            System.out.println(e);
            JSONObject object = new JSONObject();
            object.put("statusCode", ConstantClass.INTERNAL_SERVER_ERROR);
            PrintWriter writer = response.getWriter();
            writer.println(object.toJSONString());
        }
    }
}

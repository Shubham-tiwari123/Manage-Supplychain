package com.project.server.requestAPI;

import com.project.server.dao.MongoDB;
import com.project.server.entity.ServerKeys;
import com.project.server.services.CommonFunctions;
import com.project.server.services.SendProductInfo;
import com.project.server.utils.ConstantClass;
import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "SendProductChainReqAPI",urlPatterns = {"/product_info"})
public class SendProductChainReqAPI extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        SendProductInfo productInfo = new SendProductInfo();
        StringBuilder buffer = new StringBuilder();
        BufferedReader reader = request.getReader();
        int statusCode;
        String line;
        while((line = reader.readLine())!= null) {
            buffer.append(line);
        }
        String data = buffer.toString();
        System.out.println("Data from client:\n"+data);

        try {
            HashMap<Long,ArrayList<ArrayList<byte[]>>> list = productInfo.getProductInfo(-1);
            ServerKeys serverKeys = productInfo.getServerKeys();
            HashMap<Long, List<String>> stringList = productInfo.decryptData(list,serverKeys);
            List<String> result = productInfo.prepareDataToSend(stringList);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("statusCode",200);
            jsonObject.put("result",result);
            PrintWriter printWriter = response.getWriter();
            printWriter.println(jsonObject.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request,response);
    }
}

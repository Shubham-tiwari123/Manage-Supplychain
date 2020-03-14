package com.project.server.requestAPI;

import com.project.server.dao.MongoDB;
import com.project.server.entity.ServerKeys;
import com.project.server.services.CommonFunctions;
import com.project.server.utils.ConstantClass;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "SendProductChainReqAPI",urlPatterns = {"/product_info"})
public class SendProductChainReqAPI extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        MongoDB mongoDB = new MongoDB();
        CommonFunctions commonFunctions = new CommonFunctions();
        try {
            ArrayList<ArrayList<byte[]>> getAllData= mongoDB.getSpecificData(180,
                    ConstantClass.STORE_DATA_COLLECTION);
            ServerKeys keys = mongoDB.getServerKeys(ConstantClass.STORE_KEYS);
            System.out.println("Decrypting data...and converting to string (file name):"+getClass());
            for(ArrayList<byte[]> val:getAllData){
                StringBuilder builder = new StringBuilder();
                for (byte[] encryptedVal:val){
                    String subString = commonFunctions.decryptData(encryptedVal,keys.getPublicKeyModules(),
                            keys.getPublicKeyExpo());
                    builder.append(subString);
                }
                System.out.println("value:"+builder.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}

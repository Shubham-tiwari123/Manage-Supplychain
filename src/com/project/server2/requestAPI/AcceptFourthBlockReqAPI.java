package com.project.server2.requestAPI;

import com.project.server2.dao.SqlDB;
import com.project.server2.entity.DeserializeValues;
import com.project.server2.entity.ServerKeys;
import com.project.server2.responseAPI.AcceptBlockResAPI;
import com.project.server2.services.AcceptBlock;
import com.project.server2.services.CommonFunctions;
import com.project.server2.utils.ConstantClass;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AcceptFourthBlockReqAPI", urlPatterns = {"/fourth-block"})
public class AcceptFourthBlockReqAPI extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AcceptBlockResAPI resAPI = new AcceptBlockResAPI();
        SqlDB sqlDB = new SqlDB();
        System.out.println("server hit4");
        AcceptBlock acceptBlock = new AcceptBlock();
        CommonFunctions commonFunctions = new CommonFunctions();
        StringBuilder buffer = new StringBuilder();
        BufferedReader reader = request.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            buffer.append(line);
        }
        String data = buffer.toString();
        System.out.println("data4:-" + data);
        JSONParser parser = new JSONParser();

        try {
            JSONObject jSONObject = (JSONObject) parser.parse(data);
            String encryptedString = (String) jSONObject.get("encryptedData");
            String signature = (String) jSONObject.get("signature");

            DeserializeValues deserializeValues = commonFunctions.convertJsonToJava(encryptedString,
                    DeserializeValues.class);

            ServerKeys serverKeys = acceptBlock.getKeysFromDatabase();
            String decryptString = acceptBlock.decryptData(deserializeValues.getEncryptedData(), serverKeys);
            System.out.println("dec:" + decryptString);
            jSONObject = (JSONObject) parser.parse(decryptString);
            String currentHashBlock = (String) jSONObject.get("currentBlockHash");
            Long blockID = (Long) jSONObject.get("blockID");
            System.out.println("curr:" + currentHashBlock + " block:" + blockID);

            int statusCode=0;
            if(acceptBlock.verifyData4(decryptString,currentHashBlock)){
                System.out.println("Data is secured");

                List blockData = acceptBlock.getLastBlockHashDb(blockID);
                String updatedBlock = acceptBlock.updateBlock(blockData, decryptString);
                int listSize = (int) blockData.get(1);
                if(listSize==4) {
                    if (acceptBlock.appendBlockInChain(blockID, updatedBlock)) {
                        statusCode = ConstantClass.SUCCESSFUL;
                        sqlDB.updateBlockStatusTrue(blockID,"block4");
                        System.out.println("data saved");
                    } else {
                        statusCode = ConstantClass.FAILED;
                        System.out.println("not saved");
                    }
                }else{
                    sqlDB.updateBlockStatusFalse(blockID,"block4");
                    statusCode = ConstantClass.FAILED;
                    System.out.println("trying to save block 4 before block three is saved");
                 }

            } else {
                System.out.println("Data not secured");
                statusCode = ConstantClass.BAD_REQUEST;
            }
            resAPI.sendResponse(statusCode, response);
        } catch (Exception e) {
            System.out.println(e);
            resAPI.sendResponse(ConstantClass.FAILED, response);
        }
    }
}

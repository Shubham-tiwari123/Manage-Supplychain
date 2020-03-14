package com.project.server.requestAPI;

import com.project.server.entity.DeserializeValues;
import com.project.server.entity.GenesisBlock;
import com.project.server.entity.ServerKeys;
import com.project.server.responseAPI.AcceptBlockResAPI;
import com.project.server.services.AcceptBlock;
import com.project.server.services.CommonFunctions;
import com.project.server.utils.ConstantClass;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet(name = "AcceptFirstBlockReqAPI",urlPatterns = {"/first-block"})
public class AcceptFirstBlockReqAPI extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        AcceptBlockResAPI resAPI = new AcceptBlockResAPI();
        System.out.println("server hitssss");
        AcceptBlock acceptBlock = new AcceptBlock();
        CommonFunctions commonFunctions = new CommonFunctions();
        StringBuilder buffer = new StringBuilder();
        BufferedReader reader = request.getReader();
        String line;
        while((line = reader.readLine())!= null) {
            buffer.append(line);
        }
        String data = buffer.toString();
        System.out.println("datassss:-"+data);
        JSONParser parser = new JSONParser();
        int statusCode=0;
        try {
            JSONObject jSONObject = (JSONObject) parser.parse(data);
            String encryptedString = (String) jSONObject.get("encryptedData");
            String signature = (String) jSONObject.get("signature");

            DeserializeValues deserializeValues = commonFunctions.convertJsonToJava(encryptedString,
                    DeserializeValues.class);

            ServerKeys serverKeys = acceptBlock.getKeysFromDatabase();
            String decryptString = acceptBlock.decryptData(deserializeValues.getEncryptedData(),serverKeys);
            System.out.println("dec:"+decryptString);
            jSONObject = (JSONObject) parser.parse(decryptString);
            String currentHashBlock = (String) jSONObject.get("currentBlockHash");
            Long blockID = (Long) jSONObject.get("blockID");
            System.out.println("curr:"+currentHashBlock+" block:"+blockID);

            if(acceptBlock.verifyData(decryptString,currentHashBlock)){
                System.out.println("Data is secured");
                //generate genesis block
                //encrypt it using server private key and store it in db
                //encrypt the block also using server private key and store it in db against product-id
                GenesisBlock genesisBlock = acceptBlock.createGenesisBlock(blockID);
                if(acceptBlock.storeGenesisBlock(genesisBlock,blockID)){
                    if(acceptBlock.storeProductBlockA(decryptString,blockID,genesisBlock.getPreviousBlockHash()))
                        statusCode = ConstantClass.SUCCESSFUL;
                    else
                        statusCode = ConstantClass.FAILED;
                }
                resAPI.sendResponse(statusCode,response);
            }else{
                System.out.println("Data not secured");
                resAPI.sendResponse(ConstantClass.FAILED,response);
            }
        }catch (Exception e){
            System.out.println("exception:"+e);
            resAPI.sendResponse(ConstantClass.FAILED,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

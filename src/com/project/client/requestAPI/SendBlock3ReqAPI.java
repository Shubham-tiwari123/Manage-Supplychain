package com.project.client.requestAPI;

import com.project.client.entity.ClientKeys;
import com.project.client.entity.SerializeRecord;
import com.project.client.entity.ServerKeys;
import com.project.client.responseAPI.SendBlockResAPI;
import com.project.client.services.CommonFunction;
import com.project.client.services.TransferBlock;
import com.project.client.utils.ConstantClass;
import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

@WebServlet(name = "SendBlock3ReqAPI", urlPatterns = {"/send-block3"})
public class SendBlock3ReqAPI extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            TransferBlock transferBlock = new TransferBlock();
            CommonFunction commonFunction = new CommonFunction();
            // Prepare block
            String productQun = request.getParameter("productQun");
            String blockId = request.getParameter("blockID");
            String totalBoxes = request.getParameter("totalBoxes");
            String boxNumber = request.getParameter("boxNumber");
            String date = request.getParameter("date");

            long quantity = Long.parseLong(productQun);
            long totalBox = Long.parseLong(totalBoxes);
            long blockID = Long.parseLong(blockId);

            String blockString = transferBlock.prepareBlock3(blockID,quantity,boxNumber,totalBox,date);
            System.out.println("blockString3:-"+blockString);

            //encrypt block using server public key
            ServerKeys keys = transferBlock.getKeysFromDatabase();
            ArrayList<byte[]> encryptedData = transferBlock.encryptBlock(keys,blockString);

            SerializeRecord serializeRecord = new SerializeRecord();
            serializeRecord.setEncryptedData(encryptedData);
            String encryptedBlock = commonFunction.convertJavaToJson(serializeRecord);

            for(byte[] b: encryptedData){
                System.out.println(b);
            }
            // attach the signature of client signature
            JSONObject result = new JSONObject();
            result.put("encryptedData",encryptedBlock);
            result.put("signature","signature");

            URL url;
            if(ConstantClass.SERVER1_STATUS) {
                System.out.println("trying server1 block3:"+ConstantClass.SERVER1_TRY);
                url = new URL(ConstantClass.SEND_BLOCK3_URL1);
            }
            else {
                System.out.println("trying server2 block3: "+ConstantClass.SERVER2_TRY);
                url = new URL(ConstantClass.SEND_BLOCK3_URL2);
            }

            HttpURLConnection conn= (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            OutputStream output =conn.getOutputStream();
            OutputStreamWriter outputWriter = new OutputStreamWriter(output, StandardCharsets.UTF_8);
            outputWriter.write(result.toString());
            outputWriter.flush();
            outputWriter.close();
            if(conn.getResponseCode()== HttpURLConnection.HTTP_OK) {
                System.out.println("server hit");
                SendBlockResAPI resAPI = new SendBlockResAPI();
                resAPI.readResponse(response,conn);
            }
        }
        catch (Exception e){
            if(ConstantClass.SERVER1_TRY<2) {
                if(ConstantClass.SERVER1_TRY==1) {
                    System.out.println("Switching to server 2");
                    ConstantClass.SERVER1_STATUS = false;
                    ConstantClass.SERVER2_STATUS = true;
                    ConstantClass.SERVER2_TRY=0;
                }
                ConstantClass.SERVER1_TRY = ConstantClass.SERVER1_TRY + 1;
            }
            else if(ConstantClass.SERVER2_TRY<2 && ConstantClass.SERVER1_TRY==2){
                if(ConstantClass.SERVER2_TRY==1){
                    System.out.println("Switching to server 1");
                    ConstantClass.SERVER2_STATUS=false;
                    ConstantClass.SERVER1_STATUS=true;
                    ConstantClass.SERVER1_TRY=0;
                }
                ConstantClass.SERVER2_TRY = ConstantClass.SERVER2_TRY+1;
            }
            System.out.println("Something went wrong try again......");
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("statusCode",400);
            PrintWriter printWriter = response.getWriter();
            printWriter.println(jsonObject.toString());
            e.printStackTrace();
        }
    }
}

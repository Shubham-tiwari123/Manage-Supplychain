package com.project.client.requestAPI;

import com.project.client.entity.ClientKeys;
import com.project.client.entity.SerializeRecord;
import com.project.client.responseAPI.SendBlockResAPI;
import com.project.client.services.CommonFunction;
import com.project.client.services.TransferBlock;
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

@WebServlet(name = "SendBlock2ReqAPI", urlPatterns = {"/send-block2","/prepare-block2"})
public class SendBlock2ReqAPI extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response){
        try {
            TransferBlock transferBlock = new TransferBlock();
            CommonFunction commonFunction = new CommonFunction();
            PrintWriter writer = response.getWriter();
            // Prepare block
            String productQun = request.getParameter("productQun");
            String blockId = request.getParameter("blockID");
            String machineNo = request.getParameter("machineNo");
            String temp = request.getParameter("temp");

            long quantity = Long.parseLong(productQun);
            long temperature = Long.parseLong(temp);
            long blockID = Long.parseLong(blockId);
            long machineNumber = Long.parseLong(machineNo);

            String blockString = transferBlock.prepareBlock2(blockID,quantity,temperature,machineNumber);

            String currentBlockHash = transferBlock.calBlockHash(blockString);
            String manipulateBlock = transferBlock.manipulateBlock(blockString,currentBlockHash);
            System.out.println(manipulateBlock);

            //encrypt block using server public key
            ClientKeys keys = transferBlock.getKeysFromDatabase();
            ArrayList<byte[]> encryptedData = transferBlock.encryptBlock(keys,manipulateBlock);

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
            //writer.println(result.toJSONString());

            URL url = new URL("http://localhost:8080/second-block");
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
            }

            SendBlockResAPI resAPI = new SendBlockResAPI();
            resAPI.response(response,conn);
        }
        catch (Exception e){
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/prepareblock2.jsp").forward(request,response);
    }
}
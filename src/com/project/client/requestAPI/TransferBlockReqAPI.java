package com.project.client.requestAPI;

import com.project.client.entity.ClientKeys;
import com.project.client.entity.ServerKeys;
import com.project.client.services.TransferBlock;
import org.json.simple.JSONObject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "TransferBlockReqAPI", urlPatterns = {"/send-block","/prepare-block"})
public class TransferBlockReqAPI extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response){
        try {
            TransferBlock transferBlock = new TransferBlock();
            PrintWriter writer = response.getWriter();
            // Prepare block
            String productQun = request.getParameter("productQun");
            String productName = request.getParameter("productName");
            long quantity = Long.parseLong(productQun);

            String blockString = transferBlock.prepareBlock(quantity, productName);
            String currentBlockHash = transferBlock.calBlockHash(blockString);

            String manipulateBlock = transferBlock.manipulateBlock(blockString,currentBlockHash);

            //encrypt block using server public key
            ServerKeys keys = transferBlock.getKeysFromDatabase();
            ArrayList<byte[]> encryptedBlock = transferBlock.encryptBlock(keys,manipulateBlock);
            // attach the signature of client signature

            JSONObject result = new JSONObject();
            result.put("block",manipulateBlock);
            result.put("encryptedBlock",encryptedBlock);

            writer.println(result.toJSONString());
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/prepareblock.jsp").forward(request,response);
    }
}

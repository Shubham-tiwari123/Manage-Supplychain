package com.example.shipchain.responseAPI;

import com.example.shipchain.entity.SendData;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

public class GetProductInfoResAPI {
    public SendData readResponse(HttpURLConnection conn){
        System.out.println("Reading response");
        Long statusCode = null;
        try {
            InputStream inputStream = conn.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuffer output = new StringBuffer();
            String s = "";
            while ((s = bufferedReader.readLine()) != null)
                output.append(s);
            System.out.println("data:\n" + output.toString());
            JSONObject outputObject = new JSONObject(output.toString());
            statusCode = outputObject.getLong("statusCode");
            System.out.println("statusCode:\n" + statusCode);
            if(statusCode==200){
                String userDetails = outputObject.getString("productData");
                JSONObject jsonObject = new JSONObject(userDetails);
                SendData getData = new SendData();
                getData.setProductID(jsonObject.getLong("productID"));
                getData.setCreationDate(jsonObject.getString("creationDate"));
                getData.setCreationTime(jsonObject.getString("creationTime"));
                getData.setProductName(jsonObject.getString("productName"));
                getData.setSupplierName(jsonObject.getString("supplierName"));
                getData.setMachineNumber(jsonObject.getLong("machineNumber"));
                getData.setTemperature(jsonObject.getLong("temperature"));
                getData.setExporterName(jsonObject.getString("exporterName"));
                return getData;
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return null;
    }
}

package com.example.shipchain.responseAPI;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.HttpURLConnection;

public class RegisterUserResAPI {
    public Long readResponse(HttpURLConnection conn,Context context){
        System.out.println("Reading response");
        Long statusCode = null;
        try {
            InputStream inputStream = conn.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuffer output = new StringBuffer();
            String s = "";
            while ((s = bufferedReader.readLine()) != null)
                output.append(s);
            Log.e("data", String.valueOf(output));
            JSONObject outputObject = new JSONObject(output.toString());
            statusCode = outputObject.getLong("statusCode");
            System.out.println("statusCode:\n" + statusCode);

        }catch (Exception e){
            e.printStackTrace();
        }
        return statusCode;
    }
}

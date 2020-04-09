package com.example.shipchain.responseAPI;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

public class LoginUserResAPI {

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
            System.out.println("data:\n" + output.toString());
            JSONObject outputObject = new JSONObject(output.toString());
            statusCode = outputObject.getLong("statusCode");
            System.out.println("statusCode:\n" + statusCode);
            if(statusCode==200){
                String userDetails = outputObject.getString("userDetails");
                JSONObject jsonObject = new JSONObject(userDetails);
                String number = jsonObject.getString("phoneNumber");
                String email = jsonObject.getString("email");
                String password = jsonObject.getString("password");

                SharedPreferences sharedPreferences = context
                        .getSharedPreferences("LoginUserDetails", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("number",number);
                editor.putString("email",email);
                editor.putString("pass",password);
                editor.apply();

                String clientKeys = outputObject.getString("clientKeys");
                Log.e("clientKeys", clientKeys);
                JSONObject keyObj = new JSONObject(clientKeys);
                String pubMod = String.valueOf(keyObj.get("publicKeyModules"));
                String pubExpo =String.valueOf(keyObj.get("publicKeyExpo"));
                String priMod = String.valueOf(keyObj.get("privateKeyModules"));
                String priExpo =String.valueOf(keyObj.get("privateKeyExpo"));

                SharedPreferences sharedPreferences2 = context
                        .getSharedPreferences("ClientKeys", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor2 = sharedPreferences2.edit();
                editor2.putString("pubMod",pubMod);
                editor2.putString("pubExpo",pubExpo);
                editor2.putString("priMod",priMod);
                editor2.putString("priExpo",priExpo);
                editor2.apply();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return statusCode;
    }
}

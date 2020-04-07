package com.example.shipchain.responseAPI;

import android.content.Context;
import android.content.SharedPreferences;

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
                String number = outputObject.getString("phoneNumber");
                String email = outputObject.getString("email");
                String password = outputObject.getString("password");
                SharedPreferences sharedPreferences = context
                        .getSharedPreferences("LoginUserDetails", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("number",number);
                editor.putString("email",email);
                editor.putString("pass",password);
                editor.apply();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return statusCode;
    }
}

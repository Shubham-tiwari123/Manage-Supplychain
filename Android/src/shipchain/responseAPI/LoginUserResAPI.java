package com.example.shipchain.responseAPI;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.widget.ProgressBar;

import com.example.shipchain.service.WelcomeUser;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

public class LoginUserResAPI {

    public Long readResponse(HttpURLConnection conn){
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
        }catch (Exception e){
            e.printStackTrace();
        }
        return statusCode;
    }
}

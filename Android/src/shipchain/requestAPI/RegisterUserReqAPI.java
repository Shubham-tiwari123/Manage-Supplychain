package com.example.shipchain.requestAPI;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.util.Log;
import androidx.annotation.RequiresApi;
import com.example.shipchain.responseAPI.RegisterUserResAPI;
import com.example.shipchain.service.LoginUser;
import com.example.shipchain.utils.Constant;
import org.json.JSONObject;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class RegisterUserReqAPI extends AsyncTask<List<String>,Void, Void> {

    @SuppressLint("StaticFieldLeak")
    private Context context;
    private ProgressDialog dialog;
    private static Long status;

    public RegisterUserReqAPI(Context context) {
        this.context = context;
        dialog = new ProgressDialog(context);
    }

    @Override
    protected void onPreExecute() {
        this.dialog.setMessage("Please Wait");
        this.dialog.show();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected Void doInBackground(List<String>... params) {
        String email = params[0].get(0);
        String pass = params[0].get(1);
        String phoneNumber = params[0].get(2);
        try {
            JSONObject resultObj = new JSONObject();
            resultObj.put("email",email);
            resultObj.put("pass",pass);
            resultObj.put("phoneNumber",phoneNumber);
            URL url = new URL(Constant.REGISTER_URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            OutputStream output = conn.getOutputStream();
            OutputStreamWriter outputWriter = new OutputStreamWriter(output, StandardCharsets.UTF_8);
            outputWriter.write(resultObj.toString());
            outputWriter.flush();
            outputWriter.close();
            Log.e("Server...","CALLED:"+email+" pass:"+pass);
            if(conn.getResponseCode() == HttpURLConnection.HTTP_OK){
                Log.e("Server...","Server Hit success");
                RegisterUserResAPI resAPI = new RegisterUserResAPI();
                status = resAPI.readResponse(conn);
            }
        } catch (Exception e) {
            System.out.println("exception:\n"+e);
            status = 404L;
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (dialog.isShowing()) {
                    dialog.dismiss();
                    if(status == 200) {
                        new AlertDialog.Builder(context)
                            .setTitle("")
                            .setMessage("Registration Successful...Let's login")
                            .setCancelable(false)
                            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(context, LoginUser.class);
                                    context.startActivity(intent);
                                }
                            }).show();

                    }else{
                        new AlertDialog.Builder(context)
                            .setTitle("")
                            .setMessage("Registration Failed...Try again")
                            .setCancelable(false)
                            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            }).show();
                    }
                }
            }
        }, 2000);

    }
}

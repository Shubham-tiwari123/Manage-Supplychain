package com.example.shipchain.requestAPI;

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

import com.example.shipchain.responseAPI.LoginUserResAPI;
import com.example.shipchain.service.LoginUser;
import com.example.shipchain.service.WelcomeUser;
import com.example.shipchain.utils.Constant;

import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class LoginUserReqAPI extends AsyncTask<List<String>,Void, Void> {

    private Context context;
    private ProgressDialog dialog;
    static Long status;

    public LoginUserReqAPI(Context context) {
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
        try {
            JSONObject resultObj = new JSONObject();
            resultObj.put("email",email);
            resultObj.put("pass",pass);
            URL url = new URL(Constant.LOGIN_URL);
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
                LoginUserResAPI resAPI = new LoginUserResAPI();
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
                        File file = new File(context.getFilesDir(), "text");
                        if(!file.exists()){
                            file.mkdir();
                        }
                        try{
                            File gpxfile = new File(file, "sample");
                            FileWriter writer = new FileWriter(gpxfile);
                            writer.append("true");
                            writer.flush();
                            writer.close();
                            Intent intent = new Intent(context, WelcomeUser.class);
                            context.startActivity(intent);
                        }catch (Exception e){
                            e.printStackTrace();
                        }

                    }else{
                        new AlertDialog.Builder(context)
                                .setTitle("")
                                .setMessage("Invalid Credentials")
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
/* ek bat bolun tumse manoge meri bat
* agar kabhi bhi aapko koi pasand aata hai to pls mujhe mat batana just mujhe block karke move on
* hojana. U know kal tumne bas ek majak kiya tha but wo 2 min mujhe laga jaise meri saans atak
* gai ho. Meri aankh mein aanso aagaye the. I don't know mein kabse itna darne laga. But yes I feel
* scared from loosing you. I really do.
* */
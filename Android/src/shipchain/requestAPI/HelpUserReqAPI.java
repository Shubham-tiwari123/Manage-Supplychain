package com.example.shipchain.requestAPI;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import androidx.annotation.RequiresApi;
import com.example.shipchain.R;
import com.example.shipchain.responseAPI.HelpUserResAPI;
import com.example.shipchain.utils.ConstantClass;
import org.json.JSONObject;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class HelpUserReqAPI extends AsyncTask<List<String>,Void, Void> {

    @SuppressLint("StaticFieldLeak")
    private Context context;
    private View rootView;
    private ProgressDialog dialog;
    private static Long status;

    public HelpUserReqAPI(Context context, View rootView) {
        this.context = context;
        this.rootView = rootView;
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
        String productID = params[0].get(0);
        String shopName = params[0].get(1);
        String shopArea = params[0].get(2);
        String submitDate = params[0].get(3);
        String userName = params[0].get(4);
        try {
            JSONObject resultObj = new JSONObject();
            resultObj.put("productID",productID);
            resultObj.put("shopName",shopName);
            resultObj.put("shopArea",shopArea);
            resultObj.put("submitDate",submitDate);
            resultObj.put("userName",userName);

            URL url = new URL(ConstantClass.SUBMIT_USER_COMPLAIN_URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            OutputStream output = conn.getOutputStream();
            OutputStreamWriter outputWriter = new OutputStreamWriter(output, StandardCharsets.UTF_8);
            outputWriter.write(resultObj.toString());
            outputWriter.flush();
            outputWriter.close();
            if(conn.getResponseCode() == HttpURLConnection.HTTP_OK){
                Log.e("Server...","Server Hit success");
                HelpUserResAPI resAPI = new HelpUserResAPI();
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
                            .setMessage("Complain submitted")
                            .setCancelable(false)
                            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    final EditText productID = rootView.findViewById(R.id.product_id);
                                    final EditText shopName = rootView.findViewById(R.id.shop_name);
                                    final EditText shopArea = rootView.findViewById(R.id.shop_area);
                                    productID.getText().clear();
                                    shopArea.getText().clear();
                                    shopName.getText().clear();
                                    dialog.dismiss();
                                }
                            }).show();
                    }else{
                        new AlertDialog.Builder(context)
                            .setTitle("")
                            .setMessage("Data not submitted...try again")
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

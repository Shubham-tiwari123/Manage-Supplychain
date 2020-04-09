package com.example.shipchain.requestAPI;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.RequiresApi;
import com.example.shipchain.R;
import com.example.shipchain.entity.SendData;
import com.example.shipchain.responseAPI.GetProductInfoResAPI;
import com.example.shipchain.utils.ConstantClass;
import org.json.JSONObject;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class GetProductInfoReqAPI extends AsyncTask<String,Void,Void> {

    private Context context;
    private ProgressDialog dialog;
    private static Long status;
    private static SendData getData;
    private View view;

    public GetProductInfoReqAPI(Context context,View view) {
        this.context = context;
        this.view = view;
        dialog = new ProgressDialog(context);
    }

    @Override
    protected void onPreExecute() {
        this.dialog.setMessage("Getting product information");
        this.dialog.show();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected Void doInBackground(String... params) {
        String productID = params[0];
        try {
            JSONObject resultObj = new JSONObject();
            resultObj.put("productID",Long.valueOf(productID));
            URL url = new URL(ConstantClass.GET_PRODUCT_INFO_URL);
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
                GetProductInfoResAPI resAPI = new GetProductInfoResAPI();
                getData = resAPI.readResponse(conn);
                if(getData==null)
                    status = 400L;
                else
                    status = 200L;
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
                        TextView valueTV = view.findViewById(R.id.set_product_info);
                        valueTV.setText("Product ID : "+getData.getProductID()+"\n\n"+
                                "Product Name : "+getData.getProductName()+"\n\n"+
                                "Date : "+getData.getCreationDate()+"\n\n"+
                                "Time : "+getData.getCreationTime()+"\n\n"+
                                "Temperature : "+getData.getTemperature()+"\n\n"+
                                "Supplier Name : "+getData.getSupplierName()+"\n\n"+
                                "Exporter Name : "+getData.getExporterName());
                        valueTV.setVisibility(View.VISIBLE);
                    }else{
                        new AlertDialog.Builder(context)
                            .setTitle("")
                            .setMessage("Invalid productID..Try again or report us")
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

package com.example.shipchain.service;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import com.example.shipchain.R;
import com.example.shipchain.requestAPI.HelpUserReqAPI;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

public class HelpUser extends Fragment {

    private final static Pattern SHOP_NAME_VALIDATION = Pattern.compile("^[a-z]*$" , Pattern.CASE_INSENSITIVE);
    private final static Pattern PRODUCT_ID_VALIDATION = Pattern.compile("^[0-9]*$");

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view =  inflater.inflate(R.layout.fragment_help,container,false);

        final EditText productID = view.findViewById(R.id.product_id);
        final EditText shopName = view.findViewById(R.id.shop_name);
        final EditText shopArea = view.findViewById(R.id.shop_area);
        TextView purchaseDate = view.findViewById(R.id.purchase_date);
        Button submitForm = view.findViewById(R.id.submit_form);

        Date c = Calendar.getInstance().getTime();
        @SuppressLint("SimpleDateFormat") SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        final String formattedDate = df.format(c);
        purchaseDate.setText(formattedDate);

        submitForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(verifyInputs(productID,shopName,shopArea)){
                    String productId = productID.getText().toString();
                    String name = shopName.getText().toString();
                    String area = shopArea.getText().toString();
                    SharedPreferences sharedPreferences = getContext().
                            getSharedPreferences("LoginUserStatus", Context.MODE_PRIVATE);
                    final String userName = sharedPreferences.getString("email", "");
                    ArrayList<String> params = new ArrayList<>();
                    params.add(productId);
                    params.add(name);
                    params.add(area);
                    params.add(formattedDate);
                    params.add(userName);
                    HelpUserReqAPI reqAPI = new HelpUserReqAPI(getContext(),view);
                    reqAPI.execute(params);
                }
            }
        });

        return view;
    }

    private boolean verifyInputs(final EditText productID, final EditText shopName, final EditText shopArea){
        if (productID.getText().toString().isEmpty()) {
            new AlertDialog.Builder(getContext())
                .setTitle("")
                .setMessage("Enter product id")
                .setCancelable(false)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        productID.requestFocus();
                        dialog.dismiss();
                    }
                }).show();
            return false;
        }
        else if (!PRODUCT_ID_VALIDATION.matcher(productID.getText().toString()).matches()) {
            new AlertDialog.Builder(getContext())
                .setTitle("")
                .setMessage("Product id can have numbers only")
                .setCancelable(false)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        productID.requestFocus();
                        dialog.dismiss();
                    }
                }).show();
            return false;
        }
        else if (shopName.getText().toString().isEmpty()) {
            new AlertDialog.Builder(getContext())
                .setTitle("")
                .setMessage("Enter Shop Name")
                .setCancelable(false)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        shopName.requestFocus();
                        dialog.dismiss();
                    }
                }).show();
            return false;
        }
        else if(!SHOP_NAME_VALIDATION.matcher(shopName.getText().toString()).matches()) {
            new AlertDialog.Builder(getContext())
                .setTitle("")
                .setMessage("Shop Name can have characters only")
                .setCancelable(false)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        shopName.requestFocus();
                        dialog.dismiss();
                    }
                }).show();

            return false;
        }
        else if (shopArea.getText().toString().isEmpty()) {
            new AlertDialog.Builder(getContext())
                .setTitle("")
                .setMessage("Enter Shop Area")
                .setCancelable(false)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        shopArea.requestFocus();
                        dialog.dismiss();
                    }
                }).show();
            return false;
        }
        else if (!SHOP_NAME_VALIDATION.matcher(shopArea.getText().toString()).matches()) {
            new AlertDialog.Builder(getContext())
                .setTitle("")
                .setMessage("Shop Area can have characters only")
                .setCancelable(false)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        shopArea.requestFocus();
                        dialog.dismiss();
                    }
                }).show();

            return false;
        }
        return true;
    }
}

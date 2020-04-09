package com.example.shipchain.service;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.shipchain.R;

import java.math.BigInteger;

public class UserProfile extends Fragment {

    @SuppressLint("SetTextI18n")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_user_profile,container,false);

        SharedPreferences sharedPreferences = getContext().getSharedPreferences("LoginUserDetails",
                Context.MODE_PRIVATE);
        final String phoneNumber = sharedPreferences.getString("number", "");
        final String email = sharedPreferences.getString("email", "");
        final String pass = sharedPreferences.getString("pass", "");

        SharedPreferences sharedPreferences2 = getContext().getSharedPreferences("ClientKeys",
                Context.MODE_PRIVATE);
        String pubMod = sharedPreferences2.getString("pubMod","");
        String pubExpo = sharedPreferences2.getString("pubExpo","");
        String priMod = sharedPreferences2.getString("priMod","");
        String priExpo = sharedPreferences2.getString("priExpo","");

        Log.e("keysss",pubMod+" \n "+pubExpo+" \n"+priMod+" \n "+priExpo);
        TextView userPhone = view.findViewById(R.id.user_phoneNumber);
        TextView userEmail = view.findViewById(R.id.user_email_id);
        TextView userPass = view.findViewById(R.id.user_email_pass);
        TextView pubModT = view.findViewById(R.id.publicKey_mod);
        TextView pubExpoT = view.findViewById(R.id.publicKey_expo);
        TextView priModT = view.findViewById(R.id.privateKey_mod);
        TextView priExpoT = view.findViewById(R.id.privateKey_expo);

        userPhone.setText("Phone : "+phoneNumber);
        userEmail.setText("Email : "+email);
        userPass.setText("Password : "+pass);

        pubModT.setText("PublicKey Mod :\n"+pubMod);
        pubExpoT.setText("PublicKey Expo : "+pubExpo);
        priModT.setText("PrivateKey Mod :\n"+priMod);
        priExpoT.setText("PrivateKey Expo:\n"+priExpo);

        TextView logoutUser = view.findViewById(R.id.logout_user);

        logoutUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getContext().
                        getSharedPreferences("LoginUserStatus", Context.MODE_PRIVATE);

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("LoginStatus",false);
                editor.apply();

                final ProgressDialog dialog = new ProgressDialog(getContext());
                dialog.setMessage("Logging out...");
                dialog.show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (dialog.isShowing()) {
                            dialog.dismiss();
                            Intent intent = new Intent(getActivity(), LoginUser.class);
                            startActivity(intent);
                        }
                    }
                }, 1500);
            }
        });
        return view;
    }
}

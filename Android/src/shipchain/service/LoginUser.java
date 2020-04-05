package com.example.shipchain.service;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.shipchain.R;
import com.example.shipchain.requestAPI.LoginUserReqAPI;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class LoginUser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_user);

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(ContextCompat.getColor(LoginUser.this,
                    R.color.white));
        }

        TextView signUpBtn = findViewById(R.id.sign_up);
        Button loginBtn = findViewById(R.id.login_user_btn);
        final EditText userEmail = findViewById(R.id.user_email);
        final EditText userPass = findViewById(R.id.user_pass);

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginUser.this, RegisterUser.class);
                startActivity(intent);
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(verifyInputs(userEmail,userPass)) {
                    String email = userEmail.getText().toString();
                    String pass = userPass.getText().toString();
                    ArrayList<String> params = new ArrayList<>();
                    params.add(email);
                    params.add(pass);
                    LoginUserReqAPI reqAPI = new LoginUserReqAPI(LoginUser.this);
                    reqAPI.execute(params);
                }
            }
        });
    }

    private boolean verifyInputs(final EditText userEmail, final EditText password){
        if (userEmail.getText().toString().isEmpty()) {
            //userEmail.setError("Enter valid email");
            new AlertDialog.Builder(LoginUser.this)
                    .setTitle("")
                    .setMessage("Enter email id")
                    .setCancelable(false)
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            userEmail.requestFocus();
                            dialog.dismiss();
                        }
                    }).show();

            return false;
        }
        else if (!Patterns.EMAIL_ADDRESS.matcher(userEmail.getText().toString()).matches()) {
            //userEmail.setError("Enter valid email");
            new AlertDialog.Builder(LoginUser.this)
                    .setTitle("")
                    .setMessage("Enter valid email id")
                    .setCancelable(false)
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            userEmail.requestFocus();
                            dialog.dismiss();
                        }
                    }).show();

            return false;
        }
        else if (password.getText().toString().isEmpty()) {
            password.requestFocus();
            //password.setError("Enter password");
            new AlertDialog.Builder(LoginUser.this)
                    .setTitle("")
                    .setMessage("Enter Password")
                    .setCancelable(false)
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            password.requestFocus();
                            dialog.dismiss();
                        }
                    }).show();
            return false;
        }
        return true;
    }
}

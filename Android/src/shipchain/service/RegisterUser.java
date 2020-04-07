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
import com.example.shipchain.requestAPI.RegisterUserReqAPI;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class RegisterUser extends AppCompatActivity {

    private final static Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[0-9])" +     //at least one number
                    "(?=.*[a-z])" +     //at least one small letter
                    "(?=.*[A-Z])" +     //at least one capital letter
                    "(?=.*[@#$%])" +    //at least one special character
                    "(?=\\S+$)" +       //no white spaces
                    ".{6,}" +           //at least 6 characters
                    "$");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(ContextCompat.getColor(RegisterUser.this,
                    R.color.white));
        }

        TextView signInBtn = findViewById(R.id.sign_in);
        Button registerBtn = findViewById(R.id.sign_up_btn);
        final EditText userEmail = findViewById(R.id.register_email);
        final EditText userPass = findViewById(R.id.register_pass);
        final EditText userPhoneNumber = findViewById(R.id.register_number);

        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterUser.this, LoginUser.class);
                startActivity(intent);
            }
        });

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(verifyInputs(userEmail,userPass,userPhoneNumber)) {
                    String email = userEmail.getText().toString();
                    String pass = userPass.getText().toString();
                    String phoneNumber = userPhoneNumber.getText().toString();

                    ArrayList<String> params = new ArrayList<>();
                    params.add(email);
                    params.add(pass);
                    params.add(phoneNumber);
                    RegisterUserReqAPI reqAPI = new RegisterUserReqAPI(RegisterUser.this);
                    reqAPI.execute(params);
                }
            }
        });
    }

    private boolean verifyInputs(final EditText userEmail, final EditText password, final EditText userPhoneNumber){
        if (userEmail.getText().toString().isEmpty()) {
            //userEmail.setError("Enter valid email");
            new AlertDialog.Builder(RegisterUser.this)
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
            new AlertDialog.Builder(RegisterUser.this)
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
            new AlertDialog.Builder(RegisterUser.this)
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
        else if (!PASSWORD_PATTERN.matcher(password.getText().toString()).matches()){
            password.requestFocus();
            //password.setError("Password format incorrect");
            new AlertDialog.Builder(RegisterUser.this)
                    .setTitle("")
                    .setMessage("Incorrect password format. Password should contain\n" +
                            "1 Capital Letter \n" +
                            "1 Digit \n" +
                            "1 Special Character \n" +
                            "1 Small Letter \n" +
                            "Should be at least 6 digit long")
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
        else if (userPhoneNumber.getText().toString().isEmpty()) {
            password.requestFocus();
            //password.setError("Enter password");
            new AlertDialog.Builder(RegisterUser.this)
                    .setTitle("")
                    .setMessage("Enter Phone Number")
                    .setCancelable(false)
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            userPhoneNumber.requestFocus();
                            dialog.dismiss();
                        }
                    }).show();
            return false;
        }
        else if (userPhoneNumber.getText().toString().length()!=10){
            password.requestFocus();
            //password.setError("Password format incorrect");
            new AlertDialog.Builder(RegisterUser.this)
                    .setTitle("")
                    .setMessage("Incorrect phone number")
                    .setCancelable(false)
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            userPhoneNumber.requestFocus();
                            dialog.dismiss();
                        }
                    }).show();
            return false;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}

package com.example.shipchain.service;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.shipchain.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(ContextCompat.getColor(MainActivity.this,
                    R.color.white));
        }

        TextView signInBtn = findViewById(R.id.sign_in);
        Button signUpBtn = findViewById(R.id.getting_started);

        /*File fileEvents = new File(LoginUser.getFilesDir()+"/text/sample");
        StringBuilder text = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileEvents));
            String line;
            while ((line = br.readLine()) ! = null) {
                text.append(line);
                text.append('\n');
            }
            br.close();
        } catch (IOException e) { }
        String result = text.toString();*/

        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginUser.class);
                startActivity(intent);
            }
        });

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegisterUser.class);
                startActivity(intent);
            }
        });

        /*new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(MainActivity.this, WelcomeUser.class);
                startActivity(i);
                finish();
            }
        }, 1300);*/

    }
}

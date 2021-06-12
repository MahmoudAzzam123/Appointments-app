package com.app.projectandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreenActivity extends AppCompatActivity {
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        sp = getSharedPreferences("shared",MODE_PRIVATE);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(sp.getString("token",null) != null){
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                }
                else {
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                }
            }
        }, 3000);
    }
}

package com.shashank.admin.ncrb_new_admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreen extends AppCompatActivity {

    private static int SPLASH_TIME_OUT= 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        SharedPreferences sp=getSharedPreferences("USER_DATA1" , Context.MODE_PRIVATE);
        String userID= sp.getString("userID" , null);

        if(userID!=null){
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i= new Intent(SplashScreen.this,AdminPage.class);
                    startActivity(i);
                    finish();
                }
            },SPLASH_TIME_OUT);
        }
        else{
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i= new Intent(SplashScreen.this,MainActivity.class);
                    startActivity(i);
                    finish();
                }
            },SPLASH_TIME_OUT);
        }

    }
}
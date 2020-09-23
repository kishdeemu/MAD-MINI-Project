package com.techMinions.hotelmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        Thread splash_screen = new Thread(){
            @Override
            public void run() {
                try{
                    sleep(5000);
                }catch(Exception e){
                    e.printStackTrace();
                }finally {
                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                }
            }
        };
        splash_screen.start();
    }
}
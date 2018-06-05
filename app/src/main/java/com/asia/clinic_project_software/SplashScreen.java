package com.asia.clinic_project_software;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {
    SharedPreferences share ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

//        SharedPreferences share = getSharedPreferences("login", MODE_PRIVATE);
//        SharedPreferences.Editor editor = share.edit();
//        editor.putBoolean("loggedIn", false);
//        Boolean loged=share.getBoolean("loggedIn",false);
//        editor.apply();
        share= this.getSharedPreferences("login", MODE_PRIVATE);

        new Thread(){
            @Override
            public void run() {
                try {
                    sleep(3000);

                    boolean logged = share.getBoolean("loggedIn", false);
                    if(logged == true){
                        startActivity(new Intent(SplashScreen.this, MainActivity.class));
                        finish();
                    }
                    else {
                        startActivity(new Intent(SplashScreen.this, Login.class));
                        finish();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}

package com.example.kareem.fci_scu_project.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.kareem.fci_scu_project.R;
import com.example.kareem.fci_scu_project.classes.User;
import com.google.gson.Gson;

import static com.example.kareem.fci_scu_project.Helpers.Constants.KEY_USER_DATA;
import static com.example.kareem.fci_scu_project.Helpers.Constants.KEY_USER_ROLE;
import static com.example.kareem.fci_scu_project.Helpers.Constants.PREF_KEY;
import static com.example.kareem.fci_scu_project.Helpers.Constants.PREF_USER_LOGGED;
import static com.example.kareem.fci_scu_project.Helpers.Constants.USER_DATA;
import static com.example.kareem.fci_scu_project.Helpers.Constants.USER_ROLE;

public class SplashActivity extends AppCompatActivity {

    private ImageView splashLogo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();
        splashLogo = findViewById(R.id.splash_logo);
        init();
    }

    private void init() {
        Animation animation = AnimationUtils.loadAnimation(this,R.anim.splash_logo_anim);
        splashLogo.startAnimation(animation);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                process();
            }
        },2000);

    }

    private void process() {
        SharedPreferences sharedpreferences = getSharedPreferences(PREF_KEY, Context.MODE_PRIVATE);
        boolean isUserLogged = sharedpreferences.getBoolean(PREF_USER_LOGGED, false);
        if (isUserLogged){
            String userJsonData = sharedpreferences.getString(KEY_USER_DATA, "");
            USER_DATA = new Gson().fromJson(userJsonData, User.class);
            USER_ROLE = sharedpreferences.getString(KEY_USER_ROLE,"");
            Log.i("fff", "getSubjects: "+USER_ROLE);
        }

//
//        String lang = sharedpreferences.getString(KEY_LANGUAGE_CODE, "en");
//        ApplicationClass applicationClass = (ApplicationClass) getApplication();
//        applicationClass.setLangKey(lang);
//
//        if (lang.equals(KEY_LANGUAGE_ARABIC)) {
//            CommonsMethods.setLangaugeAct(KEY_LANGUAGE_ARABIC, SplashActivity.this,
//                    SplashActivity.this, false);
//        }
        Intent mainIntent;
        if (isUserLogged) {
            mainIntent = new Intent(SplashActivity.this, HomeActivity.class);
        } else {
            mainIntent = new Intent(SplashActivity.this, LoginActivity.class);
        }

        startActivity(mainIntent);
        finish();

    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Exit")
                .setMessage("Are You Sure?")
                .setNegativeButton("No", null)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SplashActivity.super.onBackPressed();
                    }
                })
                .show();
    }
}

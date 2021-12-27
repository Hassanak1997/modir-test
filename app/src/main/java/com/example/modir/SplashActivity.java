package com.example.modir;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;


public class SplashActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ImageView iconLogo = findViewById(R.id.img_logo);
        iconLogo.setAnimation(AnimationUtils.loadAnimation(this, R.anim.fade_in));

        getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        getWindow().setNavigationBarColor(getResources().getColor(R.color.white));


        new Handler().postDelayed(() -> {
//            startActivity(new SharedPrfManager(SplashActivity.this).isEntered() ?
//                    new Intent(SplashActivity.this, MenuActivity.class) :
            new Intent(SplashActivity.this, MainActivity.class);
            finish();
        }, 3000);

    }
}
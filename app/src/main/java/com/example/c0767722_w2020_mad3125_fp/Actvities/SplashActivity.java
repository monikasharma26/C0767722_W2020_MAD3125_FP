package com.example.c0767722_w2020_mad3125_fp.Actvities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.c0767722_w2020_mad3125_fp.R;

public class SplashActivity extends AppCompatActivity {
    private static int timeout = 3000;
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ActionBar bar = getSupportActionBar();

        bar.hide();
        imageView = findViewById(R.id.imgLogo);
        Animation animation = AnimationUtils.loadAnimation(SplashActivity.this,R.anim.splash_animation);
        imageView.startAnimation(animation);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent = new Intent(SplashActivity.this,HomeActivity.class);
                startActivity(intent);
                finish();

            }
        },timeout);
    }
}

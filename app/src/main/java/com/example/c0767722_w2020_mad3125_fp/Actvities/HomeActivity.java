package com.example.c0767722_w2020_mad3125_fp.Actvities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.c0767722_w2020_mad3125_fp.R;
import com.google.android.material.button.MaterialButton;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class HomeActivity extends AppCompatActivity {

    @InjectView(R.id.btnLogin)
    MaterialButton btnLogin;
    @InjectView(R.id.btnSignUp)
    MaterialButton btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.inject(this);
        ActionBar bar = getSupportActionBar();
        bar.hide();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });

    }
}

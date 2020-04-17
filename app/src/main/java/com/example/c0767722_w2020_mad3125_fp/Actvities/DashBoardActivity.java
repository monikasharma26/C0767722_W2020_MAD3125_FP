package com.example.c0767722_w2020_mad3125_fp.Actvities;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.c0767722_w2020_mad3125_fp.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class DashBoardActivity extends AppCompatActivity {

    @InjectView(R.id.bottomNavView)
    BottomNavigationView bottomNavView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        ButterKnife.inject(this);
        ActionBar bar = getSupportActionBar();
        bar.setDisplayShowTitleEnabled(true);
        bar.setTitle("Dashboard");
        bar.show();
        ArrayList<BottomNavigationView> tabs = new ArrayList<>();

    }
}

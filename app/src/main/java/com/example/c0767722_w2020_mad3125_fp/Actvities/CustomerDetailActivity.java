package com.example.c0767722_w2020_mad3125_fp.Actvities;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.c0767722_w2020_mad3125_fp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class CustomerDetailActivity extends AppCompatActivity {

    @InjectView(R.id.textView)
    TextView currentDate;
    @InjectView(R.id.imageView)
    ImageView imageView;
    @InjectView(R.id.ttlName)
    TextView ttlName;
    @InjectView(R.id.ttlEmail)
    TextView ttlEmail;
    @InjectView(R.id.ttlTaxPayTV)
    TextView ttlTaxPayTV;
    @InjectView(R.id.rec_empdetails)
    RecyclerView recEmpdetails;
    @InjectView(R.id.fab)
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_detail);
        ButterKnife.inject(this);

    }
}

package com.example.c0767722_w2020_mad3125_fp.Actvities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.c0767722_w2020_mad3125_fp.Models.Customer;
import com.example.c0767722_w2020_mad3125_fp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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
    Bundle custBundle;
    Customer customer;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("customerbills");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_detail);
        ButterKnife.inject(this);
        Intent intent = getIntent();
        if (intent.hasExtra("details")) {
            custBundle = intent.getBundleExtra("details");
            customer = (Customer) custBundle.getSerializable("customerDetail");
            ttlName.setText(customer.getFullName());
            ttlEmail.setText(customer.getEmailId());
        }

            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
    }
}

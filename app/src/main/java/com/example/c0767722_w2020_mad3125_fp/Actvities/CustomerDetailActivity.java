package com.example.c0767722_w2020_mad3125_fp.Actvities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.c0767722_w2020_mad3125_fp.Adapters.BillListAdapter;
import com.example.c0767722_w2020_mad3125_fp.Models.Bill;
import com.example.c0767722_w2020_mad3125_fp.Models.Customer;
import com.example.c0767722_w2020_mad3125_fp.Models.Hydro;
import com.example.c0767722_w2020_mad3125_fp.Models.Internet;
import com.example.c0767722_w2020_mad3125_fp.Models.Mobile;
import com.example.c0767722_w2020_mad3125_fp.R;
import com.example.c0767722_w2020_mad3125_fp.ui.ListOfCustomers.ListOfCustomersFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class CustomerDetailActivity extends AppCompatActivity {

    @InjectView(R.id.txtCurdate)
    TextView currentDate;
    @InjectView(R.id.imageView)
    ImageView imageView;
    @InjectView(R.id.ttlName)
    TextView ttlName;
    @InjectView(R.id.ttlEmail)
    TextView ttlEmail;
    @InjectView(R.id.ttlTaxPayTV)
    TextView ttlTaxPayTV;
    @InjectView(R.id.fab)
    FloatingActionButton fab;
    Bundle custBundle;
    Customer customer;
    ArrayList<Bill> billArrayList;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("customerbills");
    @InjectView(R.id.rvBilldetails)
    RecyclerView rvBilldetails;
    String custId;
    int maxId;
    ArrayList<Mobile> mobileList;
    NumberFormat format = NumberFormat.getCurrencyInstance();
    BillListAdapter billListAdapter;
    LinearLayoutManager layoutManager;
    Double totalBillAmount = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_detail);
        populateBills();
        ButterKnife.inject(this);
        final Intent intent = getIntent();
        currentDate.setText(currentDate());
        if (intent.hasExtra("details")) {
            custBundle = intent.getBundleExtra("details");
            customer = (Customer) custBundle.getSerializable("customerDetail");
            custId = customer.getId();
            ttlName.setText(customer.getFullName());
            ttlEmail.setText(customer.getEmailId());
        }
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent iIntent = new Intent(CustomerDetailActivity.this, AddBillActivity.class);
                iIntent.putExtra("custDetails", custId);
                iIntent.putExtra("maxId", maxId);
                Log.d("DDDD", custId);
                startActivity(iIntent);

            }
        });


    }

    public String currentDate() {
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        String formattedDate = df.format(c);

        return formattedDate;
    }

    public void populateBills() {
        billArrayList = new ArrayList<>();
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    HashMap<String, HashMap<String, String>> value = (HashMap<String, HashMap<String, String>>) dataSnapshot.getValue();

                    HashMap<String, String>[] usersMap;
                    usersMap = value.values().toArray(new HashMap[value.size()]);


                    for (int i = 0; i < usersMap.length; i++) {
                        if (usersMap[i].get("customerId").equals(custId)) {
                            if (usersMap[i].get("billType").equals("Mobile")) {
                                billArrayList.add(new Mobile(usersMap[i].get("customerId"),
                                        usersMap[i].get("billId"), usersMap[i].get("billDate"), usersMap[i].get("billType"),
                                        usersMap[i].get("billAmount"),
                                        usersMap[i].get("manufacture"),
                                        usersMap[i].get("planName"), usersMap[i].get("mobileNumber"),
                                        usersMap[i].get("gnbUsed"), usersMap[i].get("mintuesUsed")));

                                totalBillAmount += Double.parseDouble(usersMap[i].get("billAmount"));
                                billListAdapter = new BillListAdapter(billArrayList);
                                layoutManager = new LinearLayoutManager(CustomerDetailActivity.this);
                                rvBilldetails.setLayoutManager(layoutManager);
                                rvBilldetails.setHasFixedSize(true);
                                rvBilldetails.setItemAnimator(new DefaultItemAnimator());
                                rvBilldetails.setAdapter(billListAdapter);

                            } else if (usersMap[i].get("billType").equals("Internet")) {
                                billArrayList.add(new Internet(usersMap[i].get("customerId"), usersMap[i].get("billId"), usersMap[i].get("billDate"),
                                        usersMap[i].get("billType"), usersMap[i].get("billAmount"),
                                        usersMap[i].get("internetGbused"), usersMap[i].get("providerName")));

                                totalBillAmount += Double.parseDouble(usersMap[i].get("billAmount"));
                                billListAdapter = new BillListAdapter(billArrayList);
                                layoutManager = new LinearLayoutManager(CustomerDetailActivity.this);
                                rvBilldetails.setLayoutManager(layoutManager);
                                rvBilldetails.setHasFixedSize(true);
                                rvBilldetails.setItemAnimator(new DefaultItemAnimator());
                                rvBilldetails.setAdapter(billListAdapter);
                            } else if (usersMap[i].get("billType").equals("Hydro")) {
                                billArrayList.add(new Hydro(usersMap[i].get("customerId"), usersMap[i].get("billId"), usersMap[i].get("billDate"),
                                        usersMap[i].get("billType"), usersMap[i].get("billAmount"),
                                        usersMap[i].get("agencyName"), usersMap[i].get("unitsConsumed")));
                                totalBillAmount += Double.parseDouble(usersMap[i].get("billAmount"));
                                billListAdapter = new BillListAdapter(billArrayList);
                                layoutManager = new LinearLayoutManager(CustomerDetailActivity.this);
                                rvBilldetails.setLayoutManager(layoutManager);
                                rvBilldetails.setHasFixedSize(true);
                                rvBilldetails.setItemAnimator(new DefaultItemAnimator());
                                rvBilldetails.setAdapter(billListAdapter);

                            }
                        }
                    }

                    if (totalBillAmount > 0.0) {
                        ttlTaxPayTV.setText("Total Bill: " + format.format(totalBillAmount));
                    } else {
                        ttlTaxPayTV.setText("Customer Does Not Have any Bill ");
                    }

                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("Failed to read value.", error.toException());
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Customer Details");
        actionBar.show();
        populateBills();
    }

    @Override
    public void onBackPressed() {
        Intent back = new Intent(CustomerDetailActivity.this, ListOfCustomersFragment.class);
        startActivity(back);
    }
}

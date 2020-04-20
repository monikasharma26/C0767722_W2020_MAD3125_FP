package com.example.c0767722_w2020_mad3125_fp.Actvities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.c0767722_w2020_mad3125_fp.R;
import com.example.c0767722_w2020_mad3125_fp.ui.AddCustomer.AddCustomerFragment;
import com.example.c0767722_w2020_mad3125_fp.ui.Dashboard.Home;
import com.example.c0767722_w2020_mad3125_fp.ui.ListOfCustomers.ListOfCustomersFragment;
import com.example.c0767722_w2020_mad3125_fp.ui.Profile.ProfileFragment;
import com.example.c0767722_w2020_mad3125_fp.ui.Search.SearchFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class DashBoardActivity extends AppCompatActivity {

    @InjectView(R.id.bottomNavView)
    BottomNavigationView bottomNavView;
    ActionBar actionBar;
    int fragmentId;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("users");
    String userid="";
    String custId;
    Menu menu;
    MenuItem item,aboutus,contactus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        ButterKnife.inject(this);
        actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle("Dashboard");
        openFragment(Home.newInstance("", ""));
        actionBar.setTitle("Home");
        actionBar.show();
        bottomNavView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();
                switch (menuItem.getItemId()) {
                    case R.id.navigation_Customers:
                        handleMenuOption(id);
                        openFragment(ListOfCustomersFragment.newInstance("", ""));
                       actionBar.setTitle("Customer List");
                       return true;
                    case R.id.navigation_home:
                        handleMenuOption(id);
                        openFragment(Home.newInstance("", ""));
                        actionBar.setTitle("Home");
                        return true;
                    case R.id.navigation_notifications:
                        handleMenuOption(id);
                        openFragment(ProfileFragment.newInstance("", ""));
                        actionBar.setTitle("Profile");
                        return true;

                }

                return true;
                }
            });
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()) {
                    for (DataSnapshot snap : dataSnapshot.getChildren()) {
                        userid = (String) snap.child("id").getValue();
                        Integer id = Integer.parseInt(userid);
                        custId = String.valueOf(id + 1);
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        this.menu=menu;
        return super.onPrepareOptionsMenu(menu);
    }
    public void openFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.customer_home_page,menu);
      if(fragmentId == 1)
      {
         MenuItem item = menu.findItem(R.id.btnCusAdd);
         item.setVisible(true);
      }
        return true;
    }
    private void handleMenuOption(int id) {
        item = menu.findItem(R.id.btnCusAdd);

        if (id == R.id.navigation_Customers){
            item.setVisible(true);
        }
        else {
            item.setVisible(false);
            aboutus = menu.findItem(R.id.btnaboutUs);
            aboutus.setVisible(true);
            contactus = menu.findItem(R.id.btnContUs);
            contactus.setVisible(true);


        }
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int resId = item.getItemId();
        switch(resId){
            case R.id.btnCusAdd:
                openFragment(AddCustomerFragment.newInstance(custId, ""));
                actionBar.setTitle("Add Customer");
                item.setVisible(false);
                break;
            case R.id.btnContUs:
                item.setVisible(true);
                Intent i = new Intent(DashBoardActivity.this,ContactUsActivity.class);
                actionBar.setTitle("Contact Us");
                startActivity(i);
                break;
            case R.id.btnaboutUs:
                actionBar.setTitle("About Us");
                startActivity(new Intent(DashBoardActivity.this,AboutUsActivity.class));

                break;
            }

    return true;
    }

}

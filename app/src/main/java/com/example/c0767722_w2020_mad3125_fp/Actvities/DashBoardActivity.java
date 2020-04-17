package com.example.c0767722_w2020_mad3125_fp.Actvities;

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
import com.example.c0767722_w2020_mad3125_fp.ui.ListOfCustomers.ListOfCustomersFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class DashBoardActivity extends AppCompatActivity {

    @InjectView(R.id.bottomNavView)
    BottomNavigationView bottomNavView;
    ActionBar actionBar;
    int fragmentId;
    Menu menu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        ButterKnife.inject(this);
        actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle("Dashboard");
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
                    case R.id.navigation_Serach:
                        handleMenuOption(id);
                        //openFragment(ListOfCustomersFragment.newInstance("", ""));
                        actionBar.setTitle("Search");
                        return true;
                    case R.id.navigation_home:
                        handleMenuOption(id);
                        //openFragment(ListOfCustomersFragment.newInstance("", ""));
                        actionBar.setTitle("Home");
                        return true;
                    case R.id.navigation_notifications:
                        handleMenuOption(id);
                        //openFragment(ListOfCustomersFragment.newInstance("", ""));
                        actionBar.setTitle("Profile");
                        return true;

                }

                return true;
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
        MenuItem item = menu.findItem(R.id.btnCusAdd);
        if (id == R.id.navigation_Customers){
            item.setVisible(true);
        }
        else {
            item.setVisible(false);
        }
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
    return true
    }

}

package com.example.c0767722_w2020_mad3125_fp.ui.ListOfCustomers;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.c0767722_w2020_mad3125_fp.R;
import com.example.c0767722_w2020_mad3125_fp.ui.AddCustomer.AddCustomerFragment;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import butterknife.InjectView;

public class ListOfCustomersFragment extends Fragment {


    @InjectView(R.id.rvlistCustomers)
    RecyclerView rvlistCustomers;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("users");
    String userid="";
    String custId;
    public ListOfCustomersFragment() {
        // Required empty public constructor
    }

    public static ListOfCustomersFragment newInstance(String param1, String param2) {
        ListOfCustomersFragment fragment = new ListOfCustomersFragment();
        Bundle args = new Bundle();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snap: dataSnapshot.getChildren()) {
                    //Log.e(snap.getKey(),snap.getChildrenCount() + "");
                    //For Value geting of id
                    userid = (String) snap.child("id").getValue();
                    Integer id = Integer.parseInt(userid);
                    custId = String.valueOf(id+1);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_customerlist, container, false);

    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int resId = item.getItemId();
        switch(resId){
            case R.id.btnCusAdd:
                AddCustomerFragment.newInstance(custId, "");

        }

        return true;
    }
    @Override
    public void onResume() {
        super.onResume();
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        ActionBar actionBar = activity.getSupportActionBar();
        if(actionBar!=null) {
            actionBar.setTitle("List Of Customer");
        }

    }

}


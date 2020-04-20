package com.example.c0767722_w2020_mad3125_fp.ui.Profile;


import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.c0767722_w2020_mad3125_fp.Actvities.LoginActivity;
import com.example.c0767722_w2020_mad3125_fp.R;
import com.example.c0767722_w2020_mad3125_fp.Shared.DataBaseManager;
import com.example.c0767722_w2020_mad3125_fp.ui.Search.SearchFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class ProfileFragment extends Fragment {
    private View myFragmentView;
    private Button logoutUser;
    private TextView heading;
    FirebaseAuth mAuth;
    FirebaseUser user;
    //FirebaseUser user = .getCurrentUser();
    //FirebaseUser user = firebaseAuth.getCurrentUser();
    private ArrayAdapter myAdapter;
   // StorageReference storageDPRef;
    String checker = "";
  //  StorageTask uploadTask;


    private DataBaseManager loginTimeDetailsManager;

    private final static String TABLE_NAME = "users";
    //sql string to create the table
    private static final String tableCreatorString =
            "CREATE TABLE "+ TABLE_NAME + " (id text primary key, loginTime text);";

    public ProfileFragment() {
        // Required empty public constructor
    }

    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //just change the fragment_dashboard
        //with the fragment you want to inflate
        //like if the class is HomeFragment it should have R.layout.home_fragment
        //if it is DashboardFragment it should have R.layout.fragment_dashboard
        myFragmentView = inflater.inflate(R.layout.fragment_profile, container, false); mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();

        //storageDPRef = FirebaseStorage.getInstance().getReference().child("Profile Pictures");
        logoutUser = myFragmentView.findViewById(R.id.logoutUser);
        logoutUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getContext(), LoginActivity.class));
                getActivity().finish();
            }
        });

        return myFragmentView;
    }



}

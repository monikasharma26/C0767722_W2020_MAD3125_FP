package com.example.c0767722_w2020_mad3125_fp.ui.Dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.c0767722_w2020_mad3125_fp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import butterknife.InjectView;


public class Home extends Fragment {


    @InjectView(R.id.txt_noe_ed)
    TextView txtNoeEd;
    @InjectView(R.id.alert_img)
    ImageView alertImg;
    @InjectView(R.id.txt_nov_ed)
    TextView txtNovEd;
    @InjectView(R.id.ll_dashtotal)
    LinearLayout llDashtotal;
    @InjectView(R.id.textdateandtime)
    TextView textdateandtime;
    private ArrayList<Integer> ImagesArray;
    ViewPager imgpager;
    TextView numberemp, datetime;
    Integer empcount;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("users");


    public static Home newInstance(String param1, String param2) {
        Home fragment = new Home();
        Bundle args = new Bundle();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View layout = inflater.inflate(R.layout.fragment_homemain, container, false);
        init(layout);
        return layout;

    }

    private void init(View view) {
        txtNovEd  = view.findViewById(R.id.txt_nov_ed);
        txtNoeEd  =view.findViewById( R.id.txt_noe_ed);
        textdateandtime =view.findViewById(R.id.textdateandtime);
        Calendar c = Calendar.getInstance();

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MMMM-dd \n hh:mm:ss aa");
        String formattedDate = df.format(c.getTime());

        // Now we display formattedDate value in TextView
        textdateandtime.setText(formattedDate);
       myRef.addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
              long s =  dataSnapshot.getChildrenCount();
              txtNovEd.setText(String.valueOf(s));
           }

           @Override
           public void onCancelled(@NonNull DatabaseError databaseError) {

           }
       });

    }
}

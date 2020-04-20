package com.example.c0767722_w2020_mad3125_fp.ui.Search;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.c0767722_w2020_mad3125_fp.R;
import com.example.c0767722_w2020_mad3125_fp.ui.AddCustomer.AddCustomerFragment;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

import butterknife.ButterKnife;
import butterknife.InjectView;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class SearchFragment extends Fragment {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("users");
    TextInputEditText txtCUSTNAme;
    Button searchImage;
    TextView customerName;
    TextView customerAddress;
    ScrollView scrollViewSearch;
    String nameToSearch;

    private Button search;
    String customerNameSearch;
    public SearchFragment() {
        // Required empty public constructor
    }

    public static SearchFragment newInstance(String param1, String param2) {
        SearchFragment fragment = new SearchFragment();
        Bundle args = new Bundle();
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_search, container, false);
        txtCUSTNAme = layout.findViewById(R.id.txtCUSTNAme);
        searchImage = layout.findViewById(R.id.searchImage);
        customerName = layout.findViewById(R.id.customerName);
        customerAddress = layout.findViewById(R.id.customerAddress);
        scrollViewSearch = layout.findViewById(R.id.scrollViewSearch);
        scrollViewSearch.setVisibility(View.INVISIBLE);
        searchImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchCustomer();
            }
        });

        return layout;
    }
    private void searchCustomer() {
        final String customerNameSearch = txtCUSTNAme.getText().toString().trim();
        if (customerNameSearch.isEmpty()) {
            showAlert("Enter Customer Name", this.getContext());
        } else {
            nameToSearch = txtCUSTNAme.getText().toString().trim();
            // Read from the database
            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.
                    if (dataSnapshot.exists()) {
                        HashMap<String, HashMap<String, String>> value = (HashMap<String, HashMap<String, String>>) dataSnapshot.getValue();

                        HashMap<String, String>[] usersMap;
                        usersMap = value.values().toArray(new HashMap[value.size()]);


                        for (int i = 0; i < usersMap.length; i++) {
                            if (usersMap[i].get("firstName").equals(customerNameSearch)) {

                                String name = (String) dataSnapshot.child("fullName").getValue();
                                customerName.setText("Name : " + name);
                                String email = (String) dataSnapshot.child("emailId").getValue();
                                customerAddress.setText("Address : " + email);
                                scrollViewSearch.setVisibility(View.VISIBLE);
                            }
                            else {
                                scrollViewSearch.setVisibility(View.INVISIBLE);
                                showAlert("No customer record found associated with this number " + nameToSearch, getContext());
                            }
                        }
                    }
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    // Failed to read value
                    Log.w(TAG, "Failed to read value.", error.toException());
                }
            });
        }
    }
        private void showAlert(String message, Context context){
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(
                    context);
            alertDialog.setTitle("ERROR");
            alertDialog.setMessage(message);
            alertDialog.setPositiveButton("OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // Write your code here to execute after dialog
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
        }
}


package com.example.c0767722_w2020_mad3125_fp.ui.AddCustomer;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.c0767722_w2020_mad3125_fp.Actvities.LoginActivity;
import com.example.c0767722_w2020_mad3125_fp.Actvities.SignUpActivity;
import com.example.c0767722_w2020_mad3125_fp.Models.Customer;
import com.example.c0767722_w2020_mad3125_fp.R;
import com.example.c0767722_w2020_mad3125_fp.ui.ListOfCustomers.ListOfCustomersFragment;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;

import butterknife.InjectView;


public class AddCustomerFragment extends Fragment {

    private EditText txtCustId;
    private EditText txtFname;
    private EditText txtLName;
    private EditText txtEmail;
    private MaterialButton btnssave;
    private MaterialButton btnCancel;
    String custId, fName, lname, email;
    Customer customer;
    Array list;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("users");
    String userid="";
    private void init(View view) {
        txtCustId = view.findViewById(R.id.txtCustId);
        txtFname = view.findViewById(R.id.txtFname);
        txtLName = view.findViewById(R.id.txtLName);
        txtEmail = view.findViewById(R.id.txtEmail);
        btnssave = view.findViewById(R.id.btnssave);
        btnCancel = view.findViewById(R.id.btnCancel);
      

    }
    public AddCustomerFragment() {
        // Required empty public constructor
    }

    public static AddCustomerFragment newInstance(String param1, String param2) {
        AddCustomerFragment fragment = new AddCustomerFragment();
        Bundle args = new Bundle();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //save();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View layout = inflater.inflate(R.layout.fragment_addcustomer, container, false);
        init(layout);
        txtCustId.setText(custId);
        save();
        return layout;

    }

    private void save() {
        btnssave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validation()) {
                   //txtCustId.getText().toString();
                    fName = txtFname.getText().toString();
                    lname = txtLName.getText().toString();
                    email = txtEmail.getText().toString();
                    savingData();
                    showAlert("Customer Added Successfully");
                }
            }

        });
    }
    private void showAlert(String message) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
        alertDialogBuilder.setTitle("Alert!");
        alertDialogBuilder.setMessage(message);
        alertDialogBuilder.setIcon(R.drawable.ic_action_alerts);
        alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                getActivity().onBackPressed();
                dialog.dismiss();
            }
        });
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }

    private boolean validation() {
        if (txtFname.getText().toString().trim().length() == 0) {
            txtFname.setError("Enter First Name");
            return false;
        } else if (txtLName.getText().toString().trim().length() == 0) {
            txtLName.setError("Enter Last name");
            return false;
        } else if (txtEmail.getText().toString().trim().length() == 0) {
            txtEmail.setError("Enter Email Address");
            return false;
        } else if (!isValidEmail(txtEmail.getText().toString())) {
            txtEmail.setError("Enter Valid Email");
            return false;
        } else {
            return true;
        }
    }

    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

    public void savingData() {
        customer = new Customer(custId, fName, lname, email);
        myRef.push().setValue(customer);
    }
}

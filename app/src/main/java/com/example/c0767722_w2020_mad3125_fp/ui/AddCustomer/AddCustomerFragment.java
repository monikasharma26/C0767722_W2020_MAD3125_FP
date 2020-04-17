package com.example.c0767722_w2020_mad3125_fp.ui.AddCustomer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;

import com.example.c0767722_w2020_mad3125_fp.R;

import butterknife.InjectView;


public class AddCustomerFragment extends Fragment {

    @InjectView(R.id.txtCustId)
    EditText txtCustId;
    @InjectView(R.id.txtFname)
    EditText txtFname;
    @InjectView(R.id.txtLName)
    EditText txtLName;
    @InjectView(R.id.txtEmail)
    EditText txtEmail;
    @InjectView(R.id.idicon)
    ImageView idicon;
    @InjectView(R.id.btnSave)
    AppCompatTextView btnSave;
    @InjectView(R.id.btnCancel)
    AppCompatTextView btnCancel;

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

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        return inflater.inflate(R.layout.fragment_addcustomer, container, false);
    }
}

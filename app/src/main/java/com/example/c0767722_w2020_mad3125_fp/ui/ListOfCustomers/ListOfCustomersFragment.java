package com.example.c0767722_w2020_mad3125_fp.ui.ListOfCustomers;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.c0767722_w2020_mad3125_fp.R;

import butterknife.InjectView;

public class ListOfCustomersFragment extends Fragment {


    @InjectView(R.id.rvlistCustomers)
    RecyclerView rvlistCustomers;

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

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        return inflater.inflate(R.layout.fragment_customerlist, container, false);
    }
}


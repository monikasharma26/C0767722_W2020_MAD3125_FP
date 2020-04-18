package com.example.c0767722_w2020_mad3125_fp.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.c0767722_w2020_mad3125_fp.Models.Customer;
import com.example.c0767722_w2020_mad3125_fp.R;

import java.util.ArrayList;

public class CustomerListAdapter extends RecyclerView.Adapter<CustomerListAdapter.CustomerViewHolder> {

    private ArrayList<Customer> customersList;


    public CustomerListAdapter(ArrayList<Customer> customersList) {
        this.customersList = customersList;
    }


    @NonNull
    @Override
    public CustomerListAdapter.CustomerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_customerlist,
                parent,
                false);
        CustomerViewHolder myCustomerViewHolder = new CustomerViewHolder(mView);
        return myCustomerViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerListAdapter.CustomerViewHolder holder, int position) {
        Customer mCustomer = this.customersList.get(position);
        holder.txtname.setText(mCustomer.getFullName());
        holder.txtEmail.setText(mCustomer.getEmailId());
    }

    @Override
    public int getItemCount() {
        return 0;
    }
    public class CustomerViewHolder extends RecyclerView.ViewHolder{

        private  TextView txtname,txtEmail;
        private ImageView imgflag;
        public CustomerViewHolder(@NonNull View itemView) {
            super(itemView);
            txtname = itemView.findViewById(R.id.txtName);
            txtEmail = itemView.findViewById(R.id.txtEmail);
            imgflag = itemView.findViewById(R.id.imgAttraction);
        }
    }
}

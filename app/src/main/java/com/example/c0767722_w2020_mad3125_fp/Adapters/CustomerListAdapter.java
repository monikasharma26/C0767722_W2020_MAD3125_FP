package com.example.c0767722_w2020_mad3125_fp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.c0767722_w2020_mad3125_fp.Actvities.CustomerDetailActivity;
import com.example.c0767722_w2020_mad3125_fp.Models.Customer;
import com.example.c0767722_w2020_mad3125_fp.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CustomerListAdapter extends RecyclerView.Adapter<CustomerListAdapter.CustomerViewHolder> {

    private ArrayList<Customer> customersList;
    private Context context;

    public CustomerListAdapter(ArrayList<Customer> customersList) {
        this.customersList = customersList;
    }
    public CustomerListAdapter(Context context) {
        this.context = context;
    }
    public void setMyaaraylist(ArrayList<Customer> myaaraylist) {
        this.customersList = myaaraylist;
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
    public void onBindViewHolder(@NonNull CustomerListAdapter.CustomerViewHolder holder, final int position) {
        Customer mCustomer = this.customersList.get(position);
         holder.imgflag.setImageResource(R.drawable.emp);
        holder.txtname.setText(mCustomer.getFirstName() +" "+ mCustomer.getLastName());
        holder.txtEmail.setText(mCustomer.getEmailId());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Customer customer  = customersList.get(position);
                Bundle bcust = new Bundle();
                bcust.putSerializable("customerDetail",(Serializable) customer);
                Intent iIntent = new Intent(v.getContext(), CustomerDetailActivity.class);
                iIntent.putExtra("details", bcust);
                v.getContext().startActivity(iIntent);

            }

        });
    }

    @Override
    public int getItemCount() { return  customersList.size();}


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

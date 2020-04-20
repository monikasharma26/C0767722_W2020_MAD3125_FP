package com.example.c0767722_w2020_mad3125_fp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.c0767722_w2020_mad3125_fp.Models.Bill;
import com.example.c0767722_w2020_mad3125_fp.Models.Customer;
import com.example.c0767722_w2020_mad3125_fp.R;

import java.util.ArrayList;

public class BillListAdapter extends RecyclerView.Adapter<BillListAdapter.BillListViewHolder> {

    private ArrayList<Bill> billList;
    private Context context;
    public BillListAdapter(ArrayList<Bill> billList) {
        this.billList = billList;
    }

    public BillListAdapter(Context context) {
        this.context = context;
    }

    public void setMyaaraylist(ArrayList<Bill> myaaraylist) {
        this.billList = billList;
    }


    @NonNull
    @Override
    public BillListAdapter.BillListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_billlist,
                parent,
                false);
        BillListAdapter.BillListViewHolder myBillListViewHolder = new BillListAdapter.BillListViewHolder(mView);
        return myBillListViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BillListAdapter.BillListViewHolder holder, int position) {
        Bill mBill = this.billList.get(position);
        if(mBill.getBillType().toString().equals("Internet"))
        {
            holder.img_bill.setBackgroundResource(R.drawable.internet);
        }
        else if (mBill.getBillType().toString().equals("Hydro"))
        {
            holder.img_bill.setBackgroundResource(R.drawable.hydro);
        }
        else if (mBill.getBillType().toString().equals("Mobile")) {
            holder.img_bill.setBackgroundResource(R.drawable.mobile);

        }
        holder.txtDate.setText(mBill.getBillDate());
        holder.txtBillType.setText(mBill.getBillType());
        holder.txtBillAmount.setText(mBill.getBillAmount());

    }

    @Override
    public int getItemCount() {
        return  billList.size();
    }

    public class BillListViewHolder extends RecyclerView.ViewHolder {

        TextView txtDate, txtBillType, txtBillAmount;
        LinearLayout ll_vcard;
        ImageView img_bill;

        public BillListViewHolder(View view) {
            super(view);

            ll_vcard = (LinearLayout) view.findViewById(R.id.ll_vcard);
            txtDate = (TextView) view.findViewById(R.id.txtDate);
            txtBillType = (TextView) view.findViewById(R.id.txtBillType);
            txtBillAmount = (TextView) view.findViewById(R.id.txtbillamount);
            img_bill = (ImageView) view.findViewById(R.id.img_bill);
        }
    }
}
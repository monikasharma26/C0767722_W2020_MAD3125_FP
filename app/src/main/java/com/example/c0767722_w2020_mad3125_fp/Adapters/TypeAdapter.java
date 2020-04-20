package com.example.c0767722_w2020_mad3125_fp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.c0767722_w2020_mad3125_fp.Actvities.AddBillActivity;
import com.example.c0767722_w2020_mad3125_fp.R;

import java.util.ArrayList;


public class TypeAdapter extends BaseAdapter {

    Context context;
    ArrayList<String> countryNames = new ArrayList<String>();
    LayoutInflater inflter;
    int z= 0 ;

    public TypeAdapter(Context applicationContext,  ArrayList<String> countryNames, int i) {
        this.context = applicationContext;
        this.countryNames = countryNames;
        inflter = (LayoutInflater.from(applicationContext));
        this.z = i;
    }

    @Override
    public int getCount() {
        return countryNames.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup)
    {
        view = inflter.inflate(R.layout.custom_spinner_items, null);
        TextView names = (TextView) view.findViewById(R.id.spinner_data);
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.ll_spinner);

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if(z == 0)
                {
                    ((AddBillActivity) context).setdata(countryNames.get(i),z,i);
                }
            }

        });

        names.setText(countryNames.get(i));
        return view;
    }
}



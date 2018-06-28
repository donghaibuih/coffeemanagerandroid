package com.example.donghai.myapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class AdapterListOrder extends ArrayAdapter<OrderList> {

    private List<OrderList> data;
    private int layoutID;
    private Context context;


    public AdapterListOrder(@NonNull Context context, int resource, @NonNull List<OrderList> objects) {
        super(context, resource, objects);
        this.context = context;
        this.layoutID = resource;
        this.data = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(this.layoutID,parent,false);
        TextView itemOrderName = view.findViewById(R.id.itemOrderName);
        TextView itemOrderPrice = view.findViewById(R.id.itemOrderPrice);
        TextView itemOrderNum = view.findViewById(R.id.itemOrderNum);
        TextView itemOrderTotalPrice = view.findViewById(R.id.itemOrderTotalPrice);

        OrderList item = getItem(position);

        itemOrderName.setText(item.getNameItemOrder());
        itemOrderPrice.setText(""+item.getPriceItemOrder());
        itemOrderNum.setText(""+item.getNum());
        itemOrderTotalPrice.setText(""+item.getTotalPrice());


        return view;



    }
}

package com.example.donghai.myapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class ListViewBillAdapter extends ArrayAdapter<Bill> {
    private Context context;
    private int layoutID;
    private List<Bill> data;

    public ListViewBillAdapter(@NonNull Context context, int resource, @NonNull List<Bill> objects) {
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

        Bill bill = getItem(position);

        TextView date = view.findViewById(R.id.billDate);
        TextView totalPrice = view.findViewById(R.id.billTotalPrice);
        TextView maker = view.findViewById(R.id.billMaker);
        ImageView image = view.findViewById(R.id.billImage);

        date.setText(bill.getDate());
        totalPrice.setText(bill.getTotalPrice()+"'");
        maker.setText(bill.getEmployee()+ "");
        image.setImageResource(R.drawable.bill);

        return view;
    }
}

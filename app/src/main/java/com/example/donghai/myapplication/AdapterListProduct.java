package com.example.donghai.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class AdapterListProduct extends ArrayAdapter<Product> {
    private Context context;
    private int layoutID;
    private List<Product> data;

    public AdapterListProduct(@NonNull Context context, int resource, @NonNull List<Product> objects) {

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

        TextView productName = view.findViewById(R.id.productName);
        TextView productPrice = view.findViewById(R.id.productPrice);
        ImageView productImage = view.findViewById(R.id.imageProduct);
        Product item = getItem(position);
        productName.setText(item.getName());
        productPrice.setText(item.getPrice()+"");
        byte[] imagebyte = item.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(imagebyte,0,imagebyte.length);
        productImage.setImageBitmap(bitmap);


        return view;
    }
}

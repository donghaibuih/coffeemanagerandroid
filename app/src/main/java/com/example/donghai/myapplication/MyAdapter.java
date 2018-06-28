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
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


public class MyAdapter extends ArrayAdapter<Product> {

    private Context c;
    private List<Product> data;
    private int layout;

    public MyAdapter(@NonNull Context context, int resource, @NonNull List<Product> objects) {
        super(context, resource, objects);
        this.c = context;
        this.layout = resource;
        this.data = objects;

    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(c);
        View view = inflater.inflate(this.layout,parent,false);

        ImageView image = view.findViewById(R.id.hinhSanPham);
        TextView productName = view.findViewById(R.id.tenSanPham);
        TextView productPrice = view.findViewById(R.id.giaSanPham);


        Product product = data.get(position);
        byte[] imagev = product.getImage();
        productName.setText(product.getName());
        productPrice.setText(""+product.getPrice());
        Bitmap bitmap = BitmapFactory.decodeByteArray(imagev,0,imagev.length);
        image.setImageBitmap(bitmap);
        return view;
    }
}
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

public class AdapterEmployee extends ArrayAdapter<Employee> {
    private Context context;
    private int layoutID;
    private List<Employee> data;

    public AdapterEmployee(@NonNull Context context, int resource, @NonNull List<Employee> objects) {
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

        TextView ten = view.findViewById(R.id.txt_tenNhanVien);
        TextView diachi = view.findViewById(R.id.txt_diaChi);
        TextView soDienThoai = view.findViewById(R.id.txt_soDienThoai);
        TextView viTri = view.findViewById(R.id.txt_viTri);

        Employee nhanvien = data.get(position);
        ten.setText(nhanvien.getName()) ;
        diachi.setText(nhanvien.getAddress());
        soDienThoai.setText(nhanvien.getPhoneNumber());
        viTri.setText(nhanvien.getPosition());

        return view;
    }
}

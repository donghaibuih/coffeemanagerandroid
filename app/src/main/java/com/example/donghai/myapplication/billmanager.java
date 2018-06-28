package com.example.donghai.myapplication;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class billmanager extends AppCompatActivity {

    private ListView listBill;
    private ArrayList<Bill> data;
    private Context context;
    private ListViewBillAdapter adapter;
    private AccountDB database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_billmanager);
        context = this;
        init();
        listBill.setAdapter(adapter);

    }

    private void init() {
        database = new AccountDB(context);
        listBill = findViewById(R.id.listviewbill);
        data = database.getAllBill();
        adapter = new ListViewBillAdapter(context,R.layout.bill_layout,data);
    }
}

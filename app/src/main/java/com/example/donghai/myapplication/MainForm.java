package com.example.donghai.myapplication;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainForm extends AppCompatActivity implements View.OnClickListener {

    private ImageView btnOrder;
    private ImageView btnEmployee;
    private ImageView btnBill;
    private ImageView btnProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_form);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        init();

    }

    private void init() {
        btnOrder = findViewById(R.id.btn_MainOrder);
        btnEmployee = findViewById(R.id.btn_MainEmployee);
        btnProduct = findViewById(R.id.btnMainProduct);
        btnBill = findViewById(R.id.btnMainBill);
        btnOrder.setOnClickListener(this);
        btnEmployee.setOnClickListener(this);
        btnProduct.setOnClickListener(this);
        btnBill.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == btnOrder.getId())
        {
            Intent intent = new Intent(this,OrderFood.class);
            startActivity(intent);
        }
        if(view.getId() == btnEmployee.getId()){
            Intent intent = new Intent(this,EmployeeManage.class);
            startActivity(intent);
        }
        if(view.getId() == btnProduct.getId()){
            Intent intent = new Intent(this,ProductManager.class);
            startActivity(intent);
        }
        if(view.getId() == btnBill.getId()){
            Intent intent = new Intent(this,billmanager.class);
            startActivity(intent);

        }

    }
}

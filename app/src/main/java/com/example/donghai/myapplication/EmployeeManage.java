package com.example.donghai.myapplication;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class EmployeeManage extends AppCompatActivity implements View.OnClickListener {

    private EditText employeeName;
    private EditText employeeAddress;
    private EditText employeePhoneNumber;
    private EditText employeePosition;

    private Button btncreateEmployee;
    private Button btndeleteEmployee;
    private Button btnupdateEmployee;

    private AccountDB database;

    private  String txt_employeeName;
    private  String txt_employeeAddress;
    private  String txt_employeePhoneNumber;
    private  String txt_employeePosition;

    private AdapterEmployee employeeAdapter;
    private ArrayList<Employee> dataList;
    private ListView listEmployee;
    private Employee curentEmployee;

    private EditText accountName;
    private EditText accountPassWords;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_manage);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        init();
        createListEmployee();

        listEmployee.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                try {


                    Employee curentEmploy = dataList.get(i);
                    curentEmployee = curentEmploy;
                    employeeName.setText(curentEmploy.getName());
                    employeeAddress.setText(curentEmploy.getAddress());
                    employeePhoneNumber.setText(curentEmploy.getPhoneNumber());
                    employeePosition.setText(curentEmploy.getPosition());
                }
                catch (Exception e){
                    e.printStackTrace();
                }


            }
        });


    }


    private void createListEmployee(){


        dataList = new ArrayList<Employee>();
        dataList = database.getAllEmployee();
        employeeAdapter = new AdapterEmployee(this,R.layout.listemployee_layout,dataList);
        listEmployee.setAdapter(employeeAdapter);

    }

    private void init() {
        database = new AccountDB(this);
        accountName = findViewById(R.id.txt_accountName);
        accountPassWords = findViewById(R.id.txt_accountPassWords);
        employeeName = findViewById(R.id.txt_employeeName);
        employeeAddress = findViewById(R.id.txt_employeeAdress);
        employeePhoneNumber = findViewById(R.id.txt_employeePhoneNumber);
        btncreateEmployee  = findViewById(R.id.btn_createEmployee);
        employeePosition = findViewById(R.id.txt_employeePosition);
        btndeleteEmployee = findViewById(R.id.btn_deleteEmployee);
        btnupdateEmployee = findViewById(R.id.btn_updateEmployee);
        listEmployee = findViewById(R.id.listEmployee);



        btncreateEmployee.setOnClickListener(this);
        btnupdateEmployee.setOnClickListener(this);
        btndeleteEmployee.setOnClickListener(this);





    }



    @Override
    public void onClick(View view) {
        if(view.getId() == btncreateEmployee.getId())
        {
            txt_employeeName = employeeName.getText().toString();
            txt_employeeAddress = employeeAddress.getText().toString();
            txt_employeePhoneNumber = employeePhoneNumber.getText().toString();
            txt_employeePosition = employeePosition.getText().toString();


            try {
                Employee nhanVien = new Employee(txt_employeeName, txt_employeeAddress, txt_employeePhoneNumber, txt_employeePosition);
                Account account = new Account(accountName.getText().toString(),accountPassWords.getText().toString());
                database.insertAccount(account);
                if(database.inserEmployee(nhanVien)==true) {

                    createListEmployee();
                }

            }
            catch (Exception e){
                e.printStackTrace();
            }

        }
        if(view.getId() == btndeleteEmployee.getId()){


            database.deleteEmployee(curentEmployee.getPhoneNumber());
            createListEmployee();


        }
        if(view.getId() == btnupdateEmployee.getId()){
            try{

                Employee employee = new Employee();
                employee.setName(employeeName.getText().toString());
                employee.setAddress(employeeAddress.getText().toString());
                employee.setPhoneNumber(employeePhoneNumber.getText().toString());
                employee.setPosition(employeePosition.getText().toString());


                database.updateEmployee(employee);
                createListEmployee();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}

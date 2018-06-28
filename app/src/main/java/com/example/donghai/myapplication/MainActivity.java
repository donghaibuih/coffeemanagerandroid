package com.example.donghai.myapplication;



import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public Context context = this;
    private EditText uname;
    private EditText pass;
    private TextView btnlogin;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        init();









    }

    private void init() {
        uname = findViewById(R.id.txt_userName);
        pass = findViewById(R.id.txt_passWords);
        btnlogin = findViewById(R.id.btn_login);
        btnlogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == btnlogin.getId()) {
            Account acc = new Account(uname.getText().toString(),pass.getText().toString());
            CheckLogin check = new CheckLogin(context);
            check.execute(acc);

            try {
                Boolean checklogin = check.get();
                if(checklogin == true)
                {
                    Toast.makeText(context,"Welcome " +  acc.getName() ,Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context,MainForm.class);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(context,"Account is not exist" ,Toast.LENGTH_SHORT).show();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }




        }

    }
}

class CheckLogin extends AsyncTask<Account,Boolean,Boolean> {

    public Context context;
    Boolean check;




    public CheckLogin(Context c){

        this.context = c;
    }


    @Override
    protected Boolean doInBackground(Account... accounts) {





        Account acc = accounts[0];
        AccountDB db = new AccountDB(context);
        if(db.checkUser(acc) == true) {
            check = true;
            Log.e("dangnhap","thanhcong");



        }
        else {
            check = false;
            Log.e("dangnhap","thatbai");

        }


        return check;
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);
    }
}
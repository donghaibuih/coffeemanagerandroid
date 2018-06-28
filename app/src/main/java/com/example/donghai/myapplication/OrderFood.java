package com.example.donghai.myapplication;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class OrderFood extends AppCompatActivity implements View.OnClickListener {

    private GridView gridsanpham;
    private ListView listdatmon;
    private ArrayList<Product> listProduct;
    private MyAdapter myAdapter;
    private ArrayList<OrderList> orderList;
    private AdapterListOrder adapterListOrder;
    private Context context;
    private ArrayList<BillDetail> curentListBillDetail;
    private Bill curentBill;
    private AccountDB database;
    private int curentBillDetailID;
    private float curentBillDetailPrice;
    private String currentBillDetailProductName;
    private int curentBillDetailProductNumber;
    private BillDetail curentBillDetail;
    private Button btn_checkOut;
    private  LinearLayout layout;
    private LinearLayout layouthoadon;
    private ListView listhoadon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_food);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        context = this;
        init();
        listProduct = database.getALlProduct();
        myAdapter = new MyAdapter(this,R.layout.product,listProduct);
        gridsanpham.setAdapter(myAdapter);
        layout = findViewById(R.id.layoutchinh);
        layout.setVisibility(View.VISIBLE);
        layouthoadon = findViewById(R.id.layouthoadon);
        layouthoadon.setVisibility(View.INVISIBLE);


    }

    public static String getCurrentDateTimeMS() {
        Date dNow = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyMMddhhmmssMs");
        String datetime = ft.format(dNow);
        return datetime;
    }
    private void init() {

        database = new AccountDB(context);
        listProduct = new ArrayList<Product>();
        gridsanpham = findViewById(R.id.gridProduct);
        listdatmon = findViewById(R.id.listOrder);
        orderList = new ArrayList<OrderList>();
        curentBill = new Bill();
        curentListBillDetail = new ArrayList<BillDetail>();
        curentBillDetail = new BillDetail();
        btn_checkOut = findViewById(R.id.btncheckOut);
        btn_checkOut.setOnClickListener(this);



        gridsanpham.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                adapterListOrder = new AdapterListOrder(context,R.layout.listorder_layout,orderList);
                boolean check = false;

                if(orderList!=null)
                    for(int j = 0;j<=orderList.size()-1;j++) {
                        if (listProduct.get(i).getName().equals(orderList.get(j).getNameItemOrder())) {
                            orderList.get(j).setNum(orderList.get(j).getNum() + 1);
                            adapterListOrder.notifyDataSetChanged();

                          // curentBillDetail.setBillID(curentBill.getId());
                          // curentBillDetail.setProductName(listProduct.get(i).getName());
                           //curentBillDetail.setNumber(orderList.get(j).getNum() + 1);
                           //curentBillDetail.setPrice(listProduct.get(i).getPrice());
                           //database.insertBillDetail(curentBillDetail);
                         //  curentListBillDetail.add(curentBillDetail);


                            check = true;

                        }
                    }
                    if(check==false) {
                        orderList.add(new OrderList(listProduct.get(i).getName(),listProduct.get(i).getPrice(),1));
                      //  curentBillDetail.setBillID(curentBill.getId());
                      //  curentBillDetail.setProductName(listProduct.get(i).getName());
                       // curentBillDetail.setNumber(1);
                      //  curentBillDetail.setPrice(listProduct.get(i).getPrice());
                      //  database.insertBillDetail(curentBillDetail);
                       // curentListBillDetail.add(curentBillDetail);
                        adapterListOrder.notifyDataSetChanged();
                    }





                  listdatmon.setAdapter(adapterListOrder);









            }
        });
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == btn_checkOut.getId())
        {
            float totalPrice = 0;
            String billID = getCurrentDateTimeMS();
            curentBill.setId(billID);
            database.insertBill(curentBill);

            for(int i=0;i<= orderList.size() - 1 ; i++){
                curentBillDetail.setBillID(curentBill.getId());
                curentBillDetail.setProductName(orderList.get(i).getNameItemOrder());
                curentBillDetail.setPrice(orderList.get(i).getPriceItemOrder());
                curentBillDetail.setNumber(orderList.get(i).getNum());
                database.insertBillDetail(curentBillDetail);
                totalPrice = totalPrice + orderList.get(i).getTotalPrice();
            }
            curentBill.setTotalPrice(totalPrice);
            curentBill.setDate(Calendar.getInstance().getTime().toString());
            database.updateBill(curentBill);
            layout.setVisibility(View.INVISIBLE);
            layout.getLayoutParams().height=0;
            layout.getLayoutParams().width=0;
            layouthoadon.setVisibility(View.VISIBLE);
            listhoadon = findViewById(R.id.listhoadon);
            listhoadon.setAdapter(adapterListOrder);
            TextView pricetong = findViewById(R.id.txt_totalPrice);
            pricetong.setText(totalPrice+"");
            TextView ngayhientai = findViewById(R.id.curentdate);
            ngayhientai.setText(curentBill.getDate());


        }
    }
}

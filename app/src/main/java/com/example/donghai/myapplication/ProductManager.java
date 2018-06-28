package com.example.donghai.myapplication;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ProductManager extends AppCompatActivity implements View.OnClickListener {

    private Button imagePick;
    final int REQUEST_CODE_GALLERY = 999;
    ImageView imageView;
    private Bitmap picture;
    private String duongdan;
    private Context context;
    private Button btn_createProduct;
    private EditText productName;
    private EditText productPrice;
    private AccountDB database;
    private ListView listViewProduct;
    private ArrayList<Product> dataList;
    private Button btn_updateProduct;
    private Button btn_deleteProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_manager);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        context = this;
        init();
        createListProduct();


    }

    private void createListProduct(){

        dataList = new ArrayList<Product>();
        dataList = database.getALlProduct();
        AdapterListProduct adapter = new AdapterListProduct(this,R.layout.layout_listproduct,dataList);
        listViewProduct.setAdapter(adapter);

    }

    private void init() {
        database = new AccountDB(this);
        btn_deleteProduct = findViewById(R.id.btn_deleteProduct);
        btn_updateProduct = findViewById(R.id.btn_updateProduct);
        listViewProduct = findViewById(R.id.listProduct);
        imageView = findViewById(R.id.productImage);
        imagePick = findViewById(R.id.btn_imagepick);
        productName = findViewById(R.id.txt_productName);
        productPrice = findViewById(R.id.txt_productPrice);
        imagePick.setOnClickListener(this);
        btn_createProduct = findViewById(R.id.btn_createProduct);
        btn_createProduct.setOnClickListener(this);
        btn_deleteProduct.setOnClickListener(this);
        btn_updateProduct.setOnClickListener(this);
        listViewProduct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Product product = dataList.get(i);
                productName.setText(product.getName());
                productPrice.setText(product.getPrice()+"");
                byte[] imagebyte = product.getImage();
                Bitmap bitmap = BitmapFactory.decodeByteArray(imagebyte,0,imagebyte.length);
                imageView.setImageBitmap(bitmap);

            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == REQUEST_CODE_GALLERY){
            if(grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CODE_GALLERY);
            }
            else {
                Toast.makeText(getApplicationContext(), "You don't have permission to access file location!", Toast.LENGTH_SHORT).show();
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK && data != null){
            Uri uri = data.getData();

            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);

                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imageView.setImageBitmap(bitmap);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);

    }


    public static byte[] imageViewToByte(ImageView image) {
        Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }


    @Override
    public void onClick(View view) {
        if(view.getId() ==imagePick.getId() )
        {
            ActivityCompat.requestPermissions(
                    ProductManager.this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    REQUEST_CODE_GALLERY
            );
        }
        if(view.getId() == btn_createProduct.getId())
        {
            Product product = new Product();
            product.setName(productName.getText().toString());
            product.setPrice(Float.parseFloat(productPrice.getText().toString()));
            product.setImage(imageViewToByte(imageView));
            database.insertProduct(product);
            createListProduct();


        }
        if(view.getId() == btn_deleteProduct.getId()){

            database.deleteProduct(productName.getText().toString());
            createListProduct();

        }
        if(view.getId() == btn_updateProduct.getId()){


            Product product = new Product();
            product.setName(productName.getText().toString());
            product.setPrice(Float.parseFloat(productPrice.getText().toString()));
            product.setImage(imageViewToByte(imageView));
            database.updateProduct(product);

            createListProduct();


        }

    }
}

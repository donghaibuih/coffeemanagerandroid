package com.example.donghai.myapplication;

public class BillDetail {

    private int id;
    private String billID;
    private int number;
    private float price;

    private String productName;




    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BillDetail(int id, String billID, int number, float price, String productName) {

        this.id = id;
        this.billID = billID;
        this.number = number;
        this.price = price;
        this.productName = productName;
    }

    public BillDetail() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBillID() {
        return billID;
    }

    public void setBillID(String billID) {
        this.billID = billID;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}

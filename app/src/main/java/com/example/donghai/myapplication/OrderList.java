package com.example.donghai.myapplication;

public class OrderList {

    private String nameItemOrder;
    private float priceItemOrder;
    private float totalPrice;
    private int num;




    public OrderList(String nameItemOrder, float priceItemOrder, int num) {

        this.nameItemOrder = nameItemOrder;
        this.priceItemOrder = priceItemOrder;
        this.totalPrice = totalPrice;
        this.num = num;

    }
    public OrderList(){}



    public String getNameItemOrder() {
        return nameItemOrder;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
    public void setNameItemOrder(String nameItemOrder) {
        this.nameItemOrder = nameItemOrder;
    }

    public float getPriceItemOrder() {
        return priceItemOrder;
    }

    public void setPriceItemOrder(float priceItemOrder) {
        this.priceItemOrder = priceItemOrder;
    }

    public float getTotalPrice() {
        totalPrice  = num * priceItemOrder;
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }


}

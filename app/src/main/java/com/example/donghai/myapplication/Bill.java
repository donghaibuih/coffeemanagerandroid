package com.example.donghai.myapplication;

public class Bill {
    private String id;
    private String date;
    private String employee;
    private float totalPrice;


    public Bill(String id, String date, String employee, float totalPrice) {
        this.id = id;
        this.date = date;
        this.employee = employee;
        this.totalPrice = totalPrice;
    }
    public Bill(){}




    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEmployee() {
        return employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

}

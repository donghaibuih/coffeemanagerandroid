package com.example.donghai.myapplication;

public class Employee {






    private String name;
    private String address;
    private String phoneNumber;
    private String position;

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Employee( String name, String address, String phoneNumber, String position) {

        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.position = position;
    }

    public Employee(){}
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }




}

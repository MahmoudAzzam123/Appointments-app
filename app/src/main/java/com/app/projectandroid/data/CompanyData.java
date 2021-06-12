package com.app.projectandroid.data;

public class CompanyData {
    private String name;
    private String address;
    private String Number;
    private String begWork;
    private String endWork;


    public CompanyData() {
    }

    public CompanyData(String name, String address, String Number, String begWork, String endWork) {
        this.name = name;
        this.address = address;
        this.Number = Number;
        this.begWork = begWork;
        this.endWork = endWork;
    }

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

    public String getNumber() {
        return Number;
    }

    public void setNumber(String number) {
        this.Number = number;
    }

    public String getBegWork() {
        return begWork;
    }

    public void setBegWork(String begWork) {
        this.begWork = begWork;
    }

    public String getEndWork() {
        return endWork;
    }

    public void setEndWork(String endWork) {
        this.endWork = endWork;
    }
}

package com.app.projectandroid.data;

public class UserData {
    private String fName;
    private String lName;
    private String email;
    private String addres;
    private String gender;
    private String number;

    public UserData() {
    }

    public UserData(String fName, String lName, String email, String addres, String gender, String number) {
        this.fName = fName;
        this.lName = lName;
        this.email = email;
        this.addres = addres;
        this.gender = gender;
        this.number = number;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddres() {
        return addres;
    }

    public void setAddres(String addres) {
        this.addres = addres;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

}

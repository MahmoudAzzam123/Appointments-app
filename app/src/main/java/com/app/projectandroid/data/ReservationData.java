package com.app.projectandroid.data;

import java.io.Serializable;

public class ReservationData implements Serializable {
    private String name;
    private String date;
    private String time;
    private String token;

    public ReservationData() {
    }

    public ReservationData(String name, String date, String time, String token) {
        this.name = name;
        this.date = date;
        this.time = time;
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

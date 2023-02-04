package com.app.a2401962453_uasmobileprogramming.model;

import android.annotation.SuppressLint;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CinemaTime {
    @SerializedName("time")
    @Expose
    private String time;
    @SerializedName("hour")
    @Expose
    private int hour;
    @SerializedName("minute")
    @Expose
    private int minute;

    public CinemaTime(int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
        setTime(hour, minute);
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    @SuppressLint("DefaultLocale")
    public void setTime(int hour, int minute) {
        this.time =  String.format("%02d", hour%12) + ":" + String.format("%02d", minute) + " " + ((hour>=12) ? "PM" : "AM");
    }
}

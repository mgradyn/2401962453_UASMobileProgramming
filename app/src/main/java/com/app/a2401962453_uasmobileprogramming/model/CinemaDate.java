package com.app.a2401962453_uasmobileprogramming.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CinemaDate {
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("day")
    @Expose
    private String day;
    @SerializedName("fullDate")
    @Expose
    private Date fullDate;

    private final List<String> days=  Arrays.asList(
            "SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT"
    );

    public CinemaDate(Date fullDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fullDate);
        this.date = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
        this.day = days.get(calendar.get(Calendar.DAY_OF_WEEK) - 1);
        this.fullDate = fullDate;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public Date getFullDate() {
        return fullDate;
    }

    public void setFullDate(Date fullDate) {
        this.fullDate = fullDate;
    }
}

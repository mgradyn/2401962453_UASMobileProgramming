package com.app.a2401962453_uasmobileprogramming.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Entity
public class Ticket implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @SerializedName("uid")
    @Expose
    @ColumnInfo(name = "uid")
    private String uid;

    @SerializedName("movieName")
    @Expose
    @ColumnInfo(name = "movieName")
    private String movieName;

    @SerializedName("cinemaLocation")
    @Expose
    @ColumnInfo(name = "cinemaLocation")
    private String cinemaLocation;

    @SerializedName("roomType")
    @Expose
    @ColumnInfo(name = "roomType")
    private String roomType;

    @SerializedName("roomPrice")
    @Expose
    @ColumnInfo(name = "roomPrice")
    private String roomPrice;

    @SerializedName("name")
    @Expose
    @ColumnInfo(name = "name")
    private String name;

    @SerializedName("email")
    @Expose
    @ColumnInfo(name = "email")
    private String email;

    @SerializedName("fullDate")
    @Expose
    @ColumnInfo(name = "fullDate")
    private String fullDate;

    @SerializedName("time")
    @Expose
    @ColumnInfo(name = "time")
    private String time;

    public Ticket(String movieName, String cinemaLocation, String roomType, String roomPrice, String fullDate, String time, String name, String email) {
        this.uid = UUID.randomUUID().toString();
        this.movieName = movieName;
        this.cinemaLocation = cinemaLocation;
        this.roomType = roomType;
        this.roomPrice = roomPrice;
        this.fullDate = fullDate;
        this.time = time;
        this.name = name;
        this.email = email;
    }

    protected Ticket(Parcel in) {
        id = in.readInt();
        uid = in.readString();
        movieName = in.readString();
        cinemaLocation = in.readString();
        roomType = in.readString();
        roomPrice = in.readString();
        name = in.readString();
        email = in.readString();
        fullDate = in.readString();
        time = in.readString();
    }

    public static final Creator<Ticket> CREATOR = new Creator<Ticket>() {
        @Override
        public Ticket createFromParcel(Parcel in) {
            return new Ticket(in);
        }

        @Override
        public Ticket[] newArray(int size) {
            return new Ticket[size];
        }
    };

    public int getId() {
        return id;
    }

    public String getUid() {
        return uid;
    }

    public String getCinemaLocation() {
        return cinemaLocation;
    }

    public void setCinemaLocation(String cinemaLocation) {
        this.cinemaLocation = cinemaLocation;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public String getRoomPrice() {
        return roomPrice;
    }

    public void setRoomPrice(String roomPrice) {
        this.roomPrice = roomPrice;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getFullDate() {
        return fullDate;
    }

    public void setFullDate(String fullDate) {
        this.fullDate = fullDate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(uid);
        parcel.writeString(movieName);
        parcel.writeString(cinemaLocation);
        parcel.writeString(roomType);
        parcel.writeString(roomPrice);
        parcel.writeString(name);
        parcel.writeString(email);
        parcel.writeString(fullDate);
        parcel.writeString(time);
    }
}

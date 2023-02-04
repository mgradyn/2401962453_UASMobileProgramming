package com.app.a2401962453_uasmobileprogramming.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CinemaRoom {
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("price")
    @Expose
    private Double price;

    public CinemaRoom(String name, String image, Double price) {
        this.name = name;
        this.image = image;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Double getPrice() {
        return price;
    }
}

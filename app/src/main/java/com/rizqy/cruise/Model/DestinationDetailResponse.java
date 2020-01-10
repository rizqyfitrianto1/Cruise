package com.rizqy.cruise.Model;

import com.google.gson.annotations.SerializedName;

public class DestinationDetailResponse {
    @SerializedName( "title" )
    private String title;

    @SerializedName( "ship" )
    private String ship;

    @SerializedName( "date" )
    private String date;

    @SerializedName( "price" )
    private String price;

    @SerializedName( "visiting" )
    private String visiting;

    @SerializedName( "photo" )
    private String photo;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShip() {
        return ship;
    }

    public void setShip(String ship) {
        this.ship = ship;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getVisiting() {
        return visiting;
    }

    public void setVisiting(String visiting) {
        this.visiting = visiting;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}

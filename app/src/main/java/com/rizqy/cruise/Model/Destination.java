package com.rizqy.cruise.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Destination {
    @SerializedName( "id" )
    @Expose
    private String id;

    @SerializedName( "title" )
    @Expose
    private String title;

    @SerializedName( "ship" )
    @Expose
    private String ship;

    @SerializedName( "date" )
    @Expose
    private String date;

    @SerializedName( "price" )
    @Expose
    private String price;

    @SerializedName( "visiting" )
    @Expose
    private String visiting;

    @SerializedName( "photo" )
    @Expose
    private String photo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

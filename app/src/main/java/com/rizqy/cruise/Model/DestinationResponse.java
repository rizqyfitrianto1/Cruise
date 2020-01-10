package com.rizqy.cruise.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class DestinationResponse {

    @SerializedName("destination")
    @Expose
    private List<Destination> destinations =  null;

    public List<Destination> getDestinations(){
        return destinations;
    }
}

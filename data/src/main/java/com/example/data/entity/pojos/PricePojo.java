package com.example.data.entity.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by a630703 on 22/04/2016.
 */
public class PricePojo {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("price")
    @Expose
    private Double price;

    /**
     *
     * @return
     * The type
     */
    public String getType() {
        return type;
    }

    /**
     *
     * @param type
     * The type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     *
     * @return
     * The price
     */
    public Double getPrice() {
        return price;
    }

    /**
     *
     * @param price
     * The price
     */
    public void setPrice(Double price) {
        this.price = price;
    }

}

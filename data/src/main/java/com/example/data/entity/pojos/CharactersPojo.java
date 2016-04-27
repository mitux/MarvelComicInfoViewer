package com.example.data.entity.pojos;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by a630703 on 22/04/2016.
 */
public class CharactersPojo {

    @SerializedName("available")
    @Expose
    private Integer available;
    @SerializedName("collectionURI")
    @Expose
    private String collectionURI;
    @SerializedName("items")
    @Expose
    private List<ItemCharacterPojo> items = new ArrayList<ItemCharacterPojo>();
    @SerializedName("returned")
    @Expose
    private Integer returned;

    /**
     *
     * @return
     * The available
     */
    public Integer getAvailable() {
        return available;
    }

    /**
     *
     * @param available
     * The available
     */
    public void setAvailable(Integer available) {
        this.available = available;
    }

    /**
     *
     * @return
     * The collectionURI
     */
    public String getCollectionURI() {
        return collectionURI;
    }

    /**
     *
     * @param collectionURI
     * The collectionURI
     */
    public void setCollectionURI(String collectionURI) {
        this.collectionURI = collectionURI;
    }

    /**
     *
     * @return
     * The items
     */
    public List<ItemCharacterPojo> getItems() {
        return items;
    }

    /**
     *
     * @param items
     * The items
     */
    public void setItems(List<ItemCharacterPojo> items) {
        this.items = items;
    }

    /**
     *
     * @return
     * The returned
     */
    public Integer getReturned() {
        return returned;
    }

    /**
     *
     * @param returned
     * The returned
     */
    public void setReturned(Integer returned) {
        this.returned = returned;
    }


}

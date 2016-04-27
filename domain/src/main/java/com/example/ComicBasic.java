package com.example;

/**
 * Created by a630703 on 25/04/2016.
 */
public class ComicBasic {

    private final int id;

    public ComicBasic(int id){
        this.id = id;
    }

    private String thumbnail;

    public int getId() {
        return id;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

}

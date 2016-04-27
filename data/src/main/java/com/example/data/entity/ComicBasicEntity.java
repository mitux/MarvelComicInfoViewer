package com.example.data.entity;

/**
 * Created by a630703 on 22/04/2016.
 */
public class ComicBasicEntity {
    private final int id;

    public ComicBasicEntity(int id){
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

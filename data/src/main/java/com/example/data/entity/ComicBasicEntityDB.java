package com.example.data.entity;

import io.realm.RealmObject;

/**
 * Created by a630703 on 25/04/2016.
 */
public class ComicBasicEntityDB extends RealmObject {

    private int id;

    public ComicBasicEntityDB(){

    }

    public ComicBasicEntityDB(int id){
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

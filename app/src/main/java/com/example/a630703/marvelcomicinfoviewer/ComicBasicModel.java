package com.example.a630703.marvelcomicinfoviewer;

/**
 * Created by a630703 on 27/04/2016.
 */
public class ComicBasicModel {

    private final int id;

    public ComicBasicModel(int id){
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

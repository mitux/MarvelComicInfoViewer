package com.example.data.entity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by a630703 on 25/04/2016.
 */
public class ComicEntityDB extends RealmObject {

    private int id;

    public ComicEntityDB(){

    }

    public ComicEntityDB(int id){
        this.id = id;
    }

    private long timestamp;

    private String title;
    private String description;
    private String format;
    private String thumbnail;
    private RealmList<RealmString> creators = new RealmList<RealmString>();
    private RealmList<RealmString> characters = new RealmList<RealmString>();
    private String url;
    private String date;
    private Double price;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public RealmList<RealmString> getCreators() {
        return creators;
    }

    public void setCreators(RealmList<RealmString> creators) {
        this.creators = creators;
    }

    public RealmList<RealmString> getCharacters() {
        return characters;
    }

    public void setCharacters(RealmList<RealmString> characters) {
        this.characters = characters;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setTimestamp(long timestamp){
        this.timestamp = timestamp;
    }

    public long getTimestamp(){
        return timestamp;
    }
}

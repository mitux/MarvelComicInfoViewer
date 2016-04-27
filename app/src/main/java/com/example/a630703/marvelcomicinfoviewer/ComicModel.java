package com.example.a630703.marvelcomicinfoviewer;

import java.util.ArrayList;

/**
 * Created by a630703 on 27/04/2016.
 */
public class ComicModel {

    private final int id;

    public ComicModel(int id){
        this.id = id;
    }

    private String title;
    private String description;
    private String format;
    private String thumbnail;
    private ArrayList<String> creators = new ArrayList<>();
    private ArrayList<String> characters = new ArrayList<>();
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

    public ArrayList<String> getCreators() {
        return creators;
    }

    public void setCreators(ArrayList<String> creators) {
        this.creators = creators;
    }

    public ArrayList<String> getCharacters() {
        return characters;
    }

    public void setCharacters(ArrayList<String> characters) {
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

}

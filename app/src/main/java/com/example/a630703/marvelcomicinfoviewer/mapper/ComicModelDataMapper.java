package com.example.a630703.marvelcomicinfoviewer.mapper;

import com.example.Comic;
import com.example.a630703.marvelcomicinfoviewer.model.ComicModel;

/**
 * Created by a630703 on 27/04/2016.
 */
public class ComicModelDataMapper {

    public ComicModelDataMapper(){

    }

    public ComicModel transform(Comic comic){
        ComicModel comicModel = new ComicModel(comic.getId());

        comicModel.setUrl(comic.getUrl());
        comicModel.setTitle(comic.getTitle());
        comicModel.setPrice(comic.getPrice());
        comicModel.setCharacters(comic.getCharacters());
        comicModel.setCreators(comic.getCreators());
        comicModel.setDate(comic.getDate());
        comicModel.setDescription(comic.getDescription());
        comicModel.setFormat(comic.getFormat());
        comicModel.setThumbnail(comic.getThumbnail());

        return comicModel;
    }

}

package com.example.data.entity.mapper;

import com.example.Comic;
import com.example.data.entity.ComicEntity;

/**
 * Created by a630703 on 25/04/2016.
 */
public class ComicEntityDataMapper {

    public ComicEntityDataMapper(){

    }

    public Comic transform(ComicEntity comicEntity){
        Comic comic = new Comic(comicEntity.getId());

        comic.setUrl(comicEntity.getUrl());
        comic.setTitle(comicEntity.getTitle());
        comic.setPrice(comicEntity.getPrice());
        comic.setCharacters(comicEntity.getCharacters());
        comic.setCreators(comicEntity.getCreators());
        comic.setDate(comicEntity.getDate());
        comic.setDescription(comicEntity.getDescription());
        comic.setFormat(comicEntity.getFormat());
        comic.setThumbnail(comicEntity.getThumbnail());

        return comic;
    }

}

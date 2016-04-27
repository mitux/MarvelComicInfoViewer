package com.example.data.entity.mapper;

import com.example.ComicBasic;
import com.example.data.entity.ComicBasicEntity;

import java.util.ArrayList;

/**
 * Created by a630703 on 25/04/2016.
 */
public class ComicBasicEntityDataMapper {

    public ComicBasicEntityDataMapper(){

    }

    public ComicBasic transform(ComicBasicEntity comicBasicEntity){
        ComicBasic comicBasic = new ComicBasic(comicBasicEntity.getId());
        comicBasic.setThumbnail(comicBasicEntity.getThumbnail());

        return comicBasic;
    }

    public ArrayList<ComicBasic> transform (ArrayList<ComicBasicEntity> comicBasicEntities){
        ArrayList<ComicBasic> comicBasics = new ArrayList<>();

        for (ComicBasicEntity comicBasicEntity : comicBasicEntities){
            comicBasics.add(transform(comicBasicEntity));
        }

        return comicBasics;
    }

}

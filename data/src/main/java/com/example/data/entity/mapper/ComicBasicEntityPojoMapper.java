package com.example.data.entity.mapper;

import com.example.data.entity.ComicBasicEntity;
import com.example.data.entity.pojos.ComicPojo;
import com.example.data.entity.pojos.ResultPojo;

import java.util.ArrayList;

/**
 * Created by a630703 on 22/04/2016.
 */
public class ComicBasicEntityPojoMapper {

    public ComicBasicEntityPojoMapper() {

    }

    public ComicBasicEntity transformComicBasicEntity(ComicPojo comicPojo){
        ComicBasicEntity comicBasicEntity = new ComicBasicEntity(comicPojo.getId());

        String thumbnailPath = comicPojo.getThumbnail().getPath();
        String thumbnailExtension = comicPojo.getThumbnail().getExtension();
        comicBasicEntity.setThumbnail(thumbnailPath+"."+thumbnailExtension);

        return comicBasicEntity;
    }

    public ArrayList<ComicBasicEntity> transformComicBasicList(ArrayList<ComicPojo> comicPojos){
        ArrayList<ComicBasicEntity> comicBasicEntities = new ArrayList<>();

        for (ComicPojo cP : comicPojos){
            comicBasicEntities.add(this.transformComicBasicEntity(cP));
        }

        return comicBasicEntities;
    }

}

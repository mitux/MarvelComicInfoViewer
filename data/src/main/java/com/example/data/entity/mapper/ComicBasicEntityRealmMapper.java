package com.example.data.entity.mapper;

import com.example.data.entity.ComicBasicEntity;
import com.example.data.entity.ComicBasicEntityDB;

/**
 * Created by a630703 on 25/04/2016.
 */
public class ComicBasicEntityRealmMapper {

    public ComicBasicEntityRealmMapper(){

    }

    public ComicBasicEntity transformToComicBasicEntity(ComicBasicEntityDB comicBasicEntityDB){
        ComicBasicEntity comicBasicEntity = new ComicBasicEntity(comicBasicEntityDB.getId());

        comicBasicEntity.setThumbnail(comicBasicEntityDB.getThumbnail());

        return comicBasicEntity;
    }

    public ComicBasicEntityDB transformToComicBasicEntityDB(ComicBasicEntity comicBasicEntity){
        ComicBasicEntityDB comicBasicEntityDB = new ComicBasicEntityDB(comicBasicEntity.getId());

        comicBasicEntityDB.setThumbnail(comicBasicEntity.getThumbnail());

        return comicBasicEntityDB;
    }

}

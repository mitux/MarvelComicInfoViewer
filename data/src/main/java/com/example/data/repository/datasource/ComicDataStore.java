package com.example.data.repository.datasource;

import com.example.data.entity.ComicBasicEntity;
import com.example.data.entity.ComicEntity;

import java.util.ArrayList;

/**
 * Created by a630703 on 25/04/2016.
 */
public interface ComicDataStore {

    interface ComicDetailsCallback{
        void onComicLoaded(ComicEntity comicEntity);
    }

    interface ComicListCallback{
        void onComicListLoaded(ArrayList<ComicBasicEntity> comicEntities);
    }

    void getComicEntityList(ComicListCallback comicListCallback);

    void getComicEntityDetails(int id, ComicDetailsCallback comicDetailsCallback);

}

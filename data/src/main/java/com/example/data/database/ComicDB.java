package com.example.data.database;

import com.example.data.entity.ComicEntity;

/**
 * Created by a630703 on 25/04/2016.
 */
public interface ComicDB {

    interface ComicDBCallback{
        void onComicEntityDBLoaded(ComicEntity comicEntity);
    }

    void get (int id, ComicDBCallback comicDBCallback);

    void put (ComicEntity comicEntity);

    boolean isInDB(int id);

    boolean requireUpdate(int id);

    void deleteAll();

}

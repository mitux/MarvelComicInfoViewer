package com.example.data.database;


import com.example.data.entity.ComicBasicEntity;

import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * Created by a630703 on 25/04/2016.
 */
public interface ComicListDB {

    interface ComicDBCallback{
        void onComicListDBLoaded(ArrayList<ComicBasicEntity> comicEntity);
    }

    void setTimestamp();

    boolean requireUpdate();

    void get (ComicDBCallback comicDBCallback);

    void put (ComicBasicEntity comicBasicEntity);

    boolean isInDB();

    void deleteAll();

}

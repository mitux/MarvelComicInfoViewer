package com.example.data.repository.datasource;

import com.example.data.database.ComicDB;
import com.example.data.database.ComicListDB;
import com.example.data.entity.ComicBasicEntity;
import com.example.data.entity.ComicEntity;

import java.util.ArrayList;

/**
 * Created by a630703 on 25/04/2016.
 */
public class DBComicDataStore implements ComicDataStore {

    private final ComicDB comicDB;
    private final ComicListDB comicListDB;

    public DBComicDataStore(ComicDB comicDB, ComicListDB comicListDB){

        this.comicDB = comicDB;
        this.comicListDB = comicListDB;
    }

    @Override
    public void getComicEntityList(final ComicListCallback comicListCallback) {
        comicListDB.get(new ComicListDB.ComicDBCallback(){

            @Override
            public void onComicListDBLoaded(ArrayList<ComicBasicEntity> comicBasicEntities) {
                comicListCallback.onComicListLoaded(comicBasicEntities);
            }
        });
    }

    @Override
    public void getComicEntityDetails(int id, final ComicDetailsCallback comicDetailsCallback) {
        comicDB.get(id, new ComicDB.ComicDBCallback(){

            @Override
            public void onComicEntityDBLoaded(ComicEntity comicEntity) {
                comicDetailsCallback.onComicLoaded(comicEntity);
            }
        });
    }

}

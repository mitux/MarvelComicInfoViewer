package com.example.data.repository.datasource;

import com.example.data.database.ComicDB;
import com.example.data.database.ComicListDB;
import com.example.data.entity.ComicBasicEntity;
import com.example.data.entity.ComicEntity;
import com.example.data.net.RestApiImpl;

import java.util.ArrayList;

/**
 * Created by a630703 on 25/04/2016.
 */
public class CloudComicDataStore implements ComicDataStore {

    private final RestApiImpl restApiImp;
    private final ComicDB comicDB;
    private final ComicListDB comicListDB;

    public CloudComicDataStore(RestApiImpl restApiImp, ComicDB comicDB, ComicListDB comicListDB){

        this.restApiImp = restApiImp;
        this.comicDB = comicDB;
        this.comicListDB = comicListDB;
    }

    @Override
    public void getComicEntityList(final ComicListCallback comicListCallback) {
        this.restApiImp.getComicList(new RestApiImpl.ComicListCallback(){


            @Override
            public void onComicListLoaded(ArrayList<ComicBasicEntity> comicBasicEntities) {
                comicListCallback.onComicListLoaded(comicBasicEntities);
                putComicListInDB(comicBasicEntities);
            }
        });
    }

    void putComicListInDB(ArrayList<ComicBasicEntity> comicBasicEntities){
        for (ComicBasicEntity comicBasicEntity : comicBasicEntities){
            this.comicListDB.put(comicBasicEntity);
            comicListDB.setTimestamp();
        }
    }

    @Override
    public void getComicEntityDetails(int id, final ComicDetailsCallback comicDetailsCallback) {
        this.restApiImp.getComicById(id, new RestApiImpl.ComicDetailsCallback(){

            @Override
            public void onComicEntityLoaded(ComicEntity comicEntity) {
                comicDetailsCallback.onComicLoaded(comicEntity);
                putComicEntityInDB(comicEntity);
            }
        });
    }

    void putComicEntityInDB(ComicEntity comicEntity){
        this.comicDB.put(comicEntity);
    }

}

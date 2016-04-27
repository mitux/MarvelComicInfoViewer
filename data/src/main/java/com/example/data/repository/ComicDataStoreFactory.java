package com.example.data.repository;


import android.content.Context;
import android.util.Log;

import com.example.data.database.ComicDB;
import com.example.data.database.ComicListDB;
import com.example.data.net.RestApiImpl;
import com.example.data.repository.datasource.CloudComicDataStore;
import com.example.data.repository.datasource.ComicDataStore;
import com.example.data.repository.datasource.DBComicDataStore;

/**
 * Created by a630703 on 25/04/2016.
 */
public class ComicDataStoreFactory {

    private final Context context;
    private final ComicDB comicDB;
    private final ComicListDB comicListDB;

    public ComicDataStoreFactory(Context context, ComicDB comicDB, ComicListDB comicListDB){
        this.context = context;
        this.comicDB = comicDB;
        this.comicListDB = comicListDB;
    }

    public ComicDataStore create(int id){
        ComicDataStore comicDataStore;
        if (this.comicDB.isInDB(id)){
            if (this.comicDB.requireUpdate(id)){
                comicDataStore = createCloudStore();
            }else {
                comicDataStore = new DBComicDataStore(comicDB, comicListDB);
            }
        } else{
            comicDataStore = createCloudStore();
        }
        return comicDataStore;
    }

    public ComicDataStore create(){
        ComicDataStore comicDataStore;
        if (this.comicListDB.isInDB()){
            if (this.comicListDB.requireUpdate()){
                this.comicListDB.deleteAll();
                comicDataStore = createCloudStore();
                Log.i("FACTORY", "Loaded from cloud (UPDATE)");
            } else {
                comicDataStore = new DBComicDataStore(comicDB,comicListDB);
                Log.i("FACTORY", "Loaded from database");
            }
        } else{
            comicDataStore = createCloudStore();
            Log.i("FACTORY", "Loaded from cloud");
        }
        return comicDataStore;
    }

    private ComicDataStore createCloudStore() {
        ComicDataStore comicDataStore;
        RestApiImpl restApiImp = new RestApiImpl(this.context);
        comicDataStore = new CloudComicDataStore(restApiImp, comicDB, comicListDB);
        return comicDataStore;
    }

}

package com.example.data.database;

import android.content.Context;

import com.example.data.entity.ComicBasicEntity;
import com.example.data.entity.ComicBasicEntityDB;
import com.example.data.entity.mapper.ComicBasicEntityRealmMapper;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmQuery;
import io.realm.RealmResults;

/**
 * Created by a630703 on 25/04/2016.
 */
public class ComicListDBImpl implements ComicListDB {

    private Realm realm;
    private final ComicBasicEntityRealmMapper comicBasicEntityRealmMapper;
    private final Context context;
    private final RealmConfiguration realmConfig;

    public ComicListDBImpl (Context context, ComicBasicEntityRealmMapper comicBasicEntityRealmMapper){
        realmConfig = new RealmConfiguration.Builder(context).deleteRealmIfMigrationNeeded().build();

        this.comicBasicEntityRealmMapper = comicBasicEntityRealmMapper;
        this.context = context;
    }

    private long timestamp;

    @Override
    public void setTimestamp() {
        this.timestamp = System.currentTimeMillis();
    }


    public long getTimestampDiff() {
        long timeOldMili = timestamp;
        long currentTimeMili = System.currentTimeMillis();
        long diff = currentTimeMili - timeOldMili;
        long diffSeconds = diff / 1000;
        return diffSeconds;
    }

    @Override
    public boolean requireUpdate() {
        boolean requireUpdate = false;
        if (getTimestampDiff()>10*60){
            requireUpdate = true;
        }
        return requireUpdate;
    }

    @Override
    public void get(ComicDBCallback comicDBCallback) {
        realm.getInstance(realmConfig);
        RealmResults<ComicBasicEntityDB> comicBasicEntityDBsResults = realm.where(ComicBasicEntityDB.class).findAll();
        ArrayList<ComicBasicEntity> comicBasicEntities = new ArrayList<>();

        for (ComicBasicEntityDB comicBasicEntityDB : comicBasicEntityDBsResults){
            comicBasicEntities.add(comicBasicEntityRealmMapper.transformToComicBasicEntity(comicBasicEntityDB));
        }

        comicDBCallback.onComicListDBLoaded(comicBasicEntities);
        //realm.close();
    }

    @Override
    public void put(ComicBasicEntity comicBasicEntity) {
        ComicBasicEntityDB comicBasicEntityDB =
                comicBasicEntityRealmMapper.transformToComicBasicEntityDB(comicBasicEntity);

        realm = Realm.getInstance(realmConfig);

        RealmQuery<ComicBasicEntityDB> query = realm.where(ComicBasicEntityDB.class)
                .equalTo("id", comicBasicEntity.getId());

        if (query.count() == 0) {
            realm.beginTransaction();
            realm.copyToRealm(comicBasicEntityDB);
            realm.commitTransaction();
        }

        //realm.close();
    }

    @Override
    public boolean isInDB() {
        realm = Realm.getInstance(realmConfig);

        RealmQuery<ComicBasicEntityDB> query = realm.where(ComicBasicEntityDB.class);

        boolean exist = query.count() != 0;
        //realm.close();
        return exist;
    }

    @Override
    public void deleteAll() {
        realm = Realm.getInstance(realmConfig);

        realm.beginTransaction();
        realm.deleteAll();
        realm.clear(ComicBasicEntityDB.class);
        realm.commitTransaction();

        //realm.close();
    }
}

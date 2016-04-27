package com.example.data.database;

import android.content.Context;

import com.example.data.entity.ComicEntity;
import com.example.data.entity.ComicEntityDB;
import com.example.data.entity.mapper.ComicEntityRealmMapper;

import java.sql.Timestamp;
import java.util.Calendar;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmQuery;

/**
 * Created by a630703 on 25/04/2016.
 */
public class ComicDBImpl implements ComicDB {

    private Realm realm;
    private final ComicEntityRealmMapper comicEntityRealmMapper;
    private final Context context;
    private final RealmConfiguration realmConfig;

    public ComicDBImpl (Context context, ComicEntityRealmMapper comicEntityRealmMapper){
        realmConfig = new RealmConfiguration.Builder(context).build();

        this.comicEntityRealmMapper = comicEntityRealmMapper;
        this.context = context;
    }

    @Override
    public void get(int id, ComicDBCallback comicDBCallback) {
        realm.getInstance(realmConfig);
        ComicEntityDB comicEntityDB = realm.where(ComicEntityDB.class).equalTo("id",id).findFirst();
        ComicEntity comicEntity = comicEntityRealmMapper.transformToComicEntity(comicEntityDB);
        comicDBCallback.onComicEntityDBLoaded(comicEntity);
        realm.close();
    }

    @Override
    public void put(ComicEntity comicEntity) {
        ComicEntityDB comicEntityDB = comicEntityRealmMapper.transformToComicEntityDB(comicEntity);


        realm = Realm.getInstance(realmConfig);

        RealmQuery<ComicEntityDB> query = realm.where(ComicEntityDB.class)
                .equalTo("id", comicEntity.getId());

        long currentTime = System.currentTimeMillis();

        comicEntityDB.setTimestamp(currentTime);

        if (query.count() == 0) {
            realm.beginTransaction();
            realm.copyToRealm(comicEntityDB);
            realm.commitTransaction();
        }

        realm.close();
    }

    @Override
    public boolean isInDB(int id) {
        realm = Realm.getInstance(realmConfig);

        RealmQuery<ComicEntityDB> query = realm.where(ComicEntityDB.class)
                .equalTo("id", id);

        boolean exist = query.count() != 0;
        realm.close();
        return exist;
    }

    @Override
    public boolean requireUpdate(int id) {
        realm = Realm.getInstance(realmConfig);

        boolean requireUpdate = false;

        ComicEntityDB comicEntityDB = realm.where(ComicEntityDB.class)
                .equalTo("id", id).findFirst();

        if (getTimestampDiff(comicEntityDB.getTimestamp())>(10*60)){
            requireUpdate = true;
        }

        realm.close();

        return requireUpdate;
    }

    public long getTimestampDiff(long timestamp) {
        long timeOldMili = timestamp;
        long currentTimeMili = System.currentTimeMillis();
        long diff = currentTimeMili - timeOldMili;
        long diffSeconds = diff / 1000;
        return diffSeconds;
    }

    @Override
    public void deleteAll() {
        realm = Realm.getInstance(realmConfig);

        realm.beginTransaction();
        realm.deleteAll();
        realm.clear(ComicEntityDB.class);
        realm.commitTransaction();

        realm.close();
    }
}

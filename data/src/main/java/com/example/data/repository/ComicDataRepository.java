package com.example.data.repository;

import com.example.Comic;
import com.example.ComicBasic;
import com.example.data.entity.ComicBasicEntity;
import com.example.data.entity.ComicEntity;
import com.example.data.entity.mapper.ComicBasicEntityDataMapper;
import com.example.data.entity.mapper.ComicEntityDataMapper;
import com.example.data.repository.datasource.ComicDataStore;
import com.example.repository.ComicRepository;

import java.util.ArrayList;

/**
 * Created by a630703 on 25/04/2016.
 */
public class ComicDataRepository implements ComicRepository {

    private static ComicDataRepository INSTANCE;

    public static synchronized ComicDataRepository getInstance(ComicDataStoreFactory comicDataStoreFactory
            , ComicEntityDataMapper comicEntityDataMapper, ComicBasicEntityDataMapper comicBasicEntityDataMapper) {
        if (INSTANCE == null){
            INSTANCE = new ComicDataRepository(comicDataStoreFactory,comicEntityDataMapper,
                    comicBasicEntityDataMapper);
        }
        return INSTANCE;
    }


    private final ComicDataStoreFactory comicDataStoreFactory;
    private final ComicEntityDataMapper comicEntityDataMapper;
    private final ComicBasicEntityDataMapper comicBasicEntityDataMapper;

    protected ComicDataRepository(ComicDataStoreFactory comicDataStoreFactory,
                                  ComicEntityDataMapper comicEntityDataMapper,
                                  ComicBasicEntityDataMapper comicBasicEntityDataMapper){
        this.comicDataStoreFactory = comicDataStoreFactory;
        this.comicEntityDataMapper = comicEntityDataMapper;
        this.comicBasicEntityDataMapper = comicBasicEntityDataMapper;
    }


    @Override
    public void getComicById(int id, final ComicDetailsCallback comicDetailsCallback) {
        ComicDataStore comicDataStore = this.comicDataStoreFactory.create(id);
        comicDataStore.getComicEntityDetails(id, new ComicDataStore.ComicDetailsCallback(){

            @Override
            public void onComicLoaded(ComicEntity comicEntity) {
                Comic comic = comicEntityDataMapper.transform(comicEntity);
                comicDetailsCallback.onComicLoaded(comic);
            }
        });
    }

    @Override
    public void getComicList(final ComicListCallback comicListCallback) {
        ComicDataStore comicDataStore = this.comicDataStoreFactory.create();
        comicDataStore.getComicEntityList(new ComicDataStore.ComicListCallback(){

            @Override
            public void onComicListLoaded(ArrayList<ComicBasicEntity> comicEntities) {
                ArrayList<ComicBasic> comicBasics =
                        comicBasicEntityDataMapper.transform(comicEntities);
                comicListCallback.onComicLoaded(comicBasics);
            }
        });
    }
}

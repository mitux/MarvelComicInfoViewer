package com.example.data.entity.mapper;

import com.example.data.entity.ComicEntity;
import com.example.data.entity.ComicEntityDB;
import com.example.data.entity.RealmString;

import java.util.ArrayList;

import io.realm.RealmList;

/**
 * Created by a630703 on 25/04/2016.
 */
public class ComicEntityRealmMapper {

    public ComicEntityRealmMapper(){

    }

    public ComicEntity transformToComicEntity (ComicEntityDB comicEntityDB){
        ComicEntity comicEntity = new ComicEntity(comicEntityDB.getId());

        comicEntity.setDate(comicEntityDB.getDate());
        comicEntity.setThumbnail(comicEntityDB.getThumbnail());
        comicEntity.setUrl(comicEntityDB.getUrl());
        comicEntity.setTitle(comicEntityDB.getTitle());
        comicEntity.setFormat(comicEntityDB.getFormat());
        comicEntity.setPrice(comicEntityDB.getPrice());
        comicEntity.setCharacters(transformRealmToStringArrayList(comicEntityDB.getCharacters()));
        comicEntity.setCreators(transformRealmToStringArrayList(comicEntityDB.getCreators()));
        comicEntity.setDescription(comicEntityDB.getDescription());

        return comicEntity;
    }

    public ComicEntityDB transformToComicEntityDB (ComicEntity comicEntity){
        ComicEntityDB comicEntityDB = new ComicEntityDB(comicEntity.getId());

        comicEntityDB.setDate(comicEntity.getDate());
        comicEntityDB.setThumbnail(comicEntity.getThumbnail());
        comicEntityDB.setUrl(comicEntity.getUrl());
        comicEntityDB.setTitle(comicEntity.getTitle());
        comicEntityDB.setFormat(comicEntity.getFormat());
        comicEntityDB.setPrice(comicEntity.getPrice());
        comicEntityDB.setCharacters(transformToRealmStringList(comicEntity.getCharacters()));
        comicEntityDB.setCreators(transformToRealmStringList(comicEntity.getCreators()));
        comicEntityDB.setDescription(comicEntity.getDescription());

        return comicEntityDB;
    }

    private RealmList<RealmString> transformToRealmStringList (ArrayList<String> strings){
        RealmList<RealmString> realmStrings = new RealmList<>();
        for (String string : strings){
            realmStrings.add(transformToRealmString(string));
        }
        return realmStrings;
    }

    private RealmString transformToRealmString(String string){
        RealmString realmString = new RealmString(string);
        return realmString;
    }

    private ArrayList<String> transformRealmToStringArrayList(RealmList<RealmString> realmStrings){
        ArrayList<String> strings = new ArrayList<>();
        for (RealmString realmString : realmStrings){
            strings.add(transformRealmToString(realmString));
        }
        return strings;
    }

    private String transformRealmToString(RealmString realmString){
        return realmString.getString();
    }

}

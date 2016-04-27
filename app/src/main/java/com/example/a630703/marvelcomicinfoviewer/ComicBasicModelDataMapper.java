package com.example.a630703.marvelcomicinfoviewer;

import com.example.ComicBasic;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by a630703 on 27/04/2016.
 */
public class ComicBasicModelDataMapper {

    public ComicBasicModelDataMapper(){

    }

    ComicBasicModel transform (ComicBasic comicBasic){
        ComicBasicModel comicBasicModel = new ComicBasicModel(comicBasic.getId());
        comicBasicModel.setThumbnail(comicBasic.getThumbnail());
        return comicBasicModel;
    }

    Collection<ComicBasicModel> transform (ArrayList<ComicBasic> comicBasics){
        Collection<ComicBasicModel> comicBasicModels = new ArrayList<>();
        for (ComicBasic comicBasic : comicBasics){
            comicBasicModels.add(transform(comicBasic));
        }

        return comicBasicModels;
    }

}

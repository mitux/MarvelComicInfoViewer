package com.example.repository;

import com.example.Comic;
import com.example.ComicBasic;

import java.util.ArrayList;

/**
 * Created by a630703 on 25/04/2016.
 */
public interface ComicRepository {

    interface ComicDetailsCallback {
        void onComicLoaded(Comic comic);
    }

    interface ComicListCallback {
        void onComicLoaded(ArrayList<ComicBasic> comicBasics);
    }

    void getComicById(int id, ComicDetailsCallback comicDetailsCallback);

    void getComicList(ComicListCallback comicListCallback);

}

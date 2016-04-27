package com.example.interactor;

import com.example.ComicBasic;

import java.util.ArrayList;

/**
 * Created by a630703 on 22/04/2016.
 */
public interface GetComicListUseCase extends Interactor{

    interface Callback {
        void onComicListLoaded(ArrayList<ComicBasic> comicList);
    }

    void execute(Callback callback);
}

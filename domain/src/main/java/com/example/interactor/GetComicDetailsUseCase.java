package com.example.interactor;

import com.example.Comic;

/**
 * Created by a630703 on 25/04/2016.
 */
public interface GetComicDetailsUseCase extends Interactor {

    interface Callback{
        void onComicLoaded(Comic comic);
    }

    void execute(int id, Callback callback);

}

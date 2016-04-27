package com.example.interactor;

import com.example.Comic;
import com.example.ComicBasic;
import com.example.executor.PostExecutionThread;
import com.example.executor.ThreadExecutor;
import com.example.repository.ComicRepository;

import java.util.ArrayList;

/**
 * Created by a630703 on 22/04/2016.
 */
public class GetComicListUseCaseImpl implements GetComicListUseCase {

    private ComicRepository comicRepository;
    private ThreadExecutor threadExecutor;
    private PostExecutionThread postExecutionThread;

    private GetComicListUseCase.Callback callback;

    public GetComicListUseCaseImpl(ComicRepository comicRepository, ThreadExecutor threadExecutor,
                                   PostExecutionThread postExecutionThread){
        this.comicRepository = comicRepository;
        this.threadExecutor = threadExecutor;
        this.postExecutionThread = postExecutionThread;
    }



    @Override
    public void execute(Callback callback) {
        this.callback = callback;
        this.threadExecutor.execute(this);
    }

    @Override
    public void run() {
        this.comicRepository.getComicList(repositoryCallback);
    }

    private final ComicRepository.ComicListCallback repositoryCallback =
            new ComicRepository.ComicListCallback() {

                @Override
                public void onComicLoaded(ArrayList<ComicBasic> comicBasics) {
                    notifyGetComicListSuccessfully(comicBasics);
                }
            };

    private void notifyGetComicListSuccessfully(final ArrayList<ComicBasic> comicBasics){
        this.postExecutionThread.post(new Runnable() {
            @Override
            public void run() {
                callback.onComicListLoaded(comicBasics);
            }
        });
    }
}

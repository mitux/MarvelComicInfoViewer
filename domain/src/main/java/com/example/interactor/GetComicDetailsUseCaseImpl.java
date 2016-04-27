package com.example.interactor;

import com.example.Comic;
import com.example.executor.PostExecutionThread;
import com.example.executor.ThreadExecutor;
import com.example.repository.ComicRepository;

/**
 * Created by a630703 on 25/04/2016.
 */
public class GetComicDetailsUseCaseImpl implements GetComicDetailsUseCase {

    private ComicRepository comicRepository;
    private ThreadExecutor threadExecutor;
    private PostExecutionThread postExecutionThread;

    private GetComicDetailsUseCase.Callback callback;
    private int id = -1;

    public GetComicDetailsUseCaseImpl(ComicRepository comicRepository, ThreadExecutor threadExecutor,
                                      PostExecutionThread postExecutionThread){

        this.comicRepository = comicRepository;
        this.threadExecutor = threadExecutor;
        this.postExecutionThread = postExecutionThread;
    }

    @Override
    public void execute(int id, Callback callback) {
        this.id = id;
        this.callback = callback;
        this.threadExecutor.execute(this);
    }

    @Override
    public void run() {
        this.comicRepository.getComicById(id,repositoryCallback);
    }

    private final ComicRepository.ComicDetailsCallback repositoryCallback =
            new ComicRepository.ComicDetailsCallback() {

                @Override
                public void onComicLoaded(Comic comic) {
                    notifyGetComicDetailsSuccessfully(comic);
                }
            };

    private void notifyGetComicDetailsSuccessfully(final Comic comic){
        this.postExecutionThread.post(new Runnable() {
            @Override
            public void run() {
                callback.onComicLoaded(comic);
            }
        });
    }
}

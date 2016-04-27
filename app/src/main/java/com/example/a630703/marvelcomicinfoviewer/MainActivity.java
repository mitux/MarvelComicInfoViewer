package com.example.a630703.marvelcomicinfoviewer;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.Comic;
import com.example.ComicBasic;
import com.example.data.database.ComicDB;
import com.example.data.database.ComicDBImpl;
import com.example.data.database.ComicListDB;
import com.example.data.database.ComicListDBImpl;
import com.example.data.entity.mapper.ComicBasicEntityDataMapper;
import com.example.data.entity.mapper.ComicBasicEntityRealmMapper;
import com.example.data.entity.mapper.ComicEntityDataMapper;
import com.example.data.entity.mapper.ComicEntityRealmMapper;
import com.example.data.executor.JobExecutor;
import com.example.data.repository.ComicDataRepository;
import com.example.data.repository.ComicDataStoreFactory;
import com.example.executor.PostExecutionThread;
import com.example.executor.ThreadExecutor;
import com.example.interactor.GetComicDetailsUseCase;
import com.example.interactor.GetComicDetailsUseCaseImpl;
import com.example.interactor.GetComicListUseCase;
import com.example.interactor.GetComicListUseCaseImpl;
import com.example.repository.ComicRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.ComicListView)
    ListView comicListView;
    @Bind(R.id.swiperefresh)
    SwipeRefreshLayout swipeRefresh;

    GetComicDetailsUseCase getComicDetailsUseCase = null;
    GetComicListUseCase getComicListUseCase = null;
    ComicDB comicDB;
    ComicListDB comicListDB;
    ComicBasicModelDataMapper comicBasicModelDataMapper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        ThreadExecutor threadExecutor = JobExecutor.getInstance();
        PostExecutionThread postExecutionThread = UIThread.getInstance();

        comicBasicModelDataMapper = new ComicBasicModelDataMapper();

        ComicEntityRealmMapper comicEntityRealmMapper = new ComicEntityRealmMapper();
        comicDB = new ComicDBImpl(this.getApplicationContext(),comicEntityRealmMapper);

        ComicBasicEntityRealmMapper comicBasicEntityRealmMapper = new ComicBasicEntityRealmMapper();
        comicListDB = new ComicListDBImpl(this.getApplicationContext(),comicBasicEntityRealmMapper);

        ComicEntityDataMapper comicEntityDataMapper = new ComicEntityDataMapper();
        ComicBasicEntityDataMapper comicBasicEntityDataMapper = new ComicBasicEntityDataMapper();
        ComicDataStoreFactory comicDataStoreFactory = new ComicDataStoreFactory(this.getApplicationContext(), comicDB, comicListDB);

        ComicRepository comicRepository = ComicDataRepository.getInstance(comicDataStoreFactory, comicEntityDataMapper, comicBasicEntityDataMapper);

        getComicDetailsUseCase = new GetComicDetailsUseCaseImpl(comicRepository,threadExecutor,postExecutionThread);
        getComicListUseCase = new GetComicListUseCaseImpl(comicRepository,threadExecutor,postExecutionThread);


        getComicList();

        swipeRefresh.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        Log.i("HOLA", "onRefresh called from SwipeRefreshLayout");

                        // This method performs the actual data-refresh operation.
                        // The method calls setRefreshing(false) when it's finished.
                        getComicList();
                    }
                }
        );

//        ArrayAdapter<Button> arrayAdapter = new ArrayAdapter<Button>(
//                this, android.R.layout.simple_list_item_1,buttons
//        );
//        comicListView.setAdapter(arrayAdapter);
    }

    void getComicList(){
        getComicListUseCase.execute(getComicListCallback);
    }

    GetComicListUseCase.Callback getComicListCallback = new GetComicListUseCase.Callback(){

        @Override
        public void onComicListLoaded(ArrayList<ComicBasic> comicList) {
            if (comicList != null) {
                Collection<ComicBasicModel> comicBasicModels =
                        comicBasicModelDataMapper.transform(comicList);

                ComicAdapter comicAdapter = new ComicAdapter(MainActivity.this, comicBasicModels);
                comicListView.setAdapter(comicAdapter);
                swipeRefresh.setRefreshing(false);
            }
        }
    };
}

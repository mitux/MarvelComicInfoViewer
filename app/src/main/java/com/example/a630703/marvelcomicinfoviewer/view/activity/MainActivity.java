package com.example.a630703.marvelcomicinfoviewer.view.activity;


import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.ComicBasic;
import com.example.a630703.marvelcomicinfoviewer.view.adapter.ComicAdapter;
import com.example.a630703.marvelcomicinfoviewer.R;
import com.example.a630703.marvelcomicinfoviewer.mapper.ComicBasicModelDataMapper;
import com.example.a630703.marvelcomicinfoviewer.model.ComicBasicModel;
import com.example.a630703.marvelcomicinfoviewer.view.UIThread;
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
import com.example.interactor.GetComicListUseCase;
import com.example.interactor.GetComicListUseCaseImpl;
import com.example.repository.ComicRepository;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;
import java.util.Collection;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.ComicListView)
    ListView comicListView;
    @Bind(R.id.swiperefresh)
    SwipeRefreshLayout swipeRefresh;

    GetComicListUseCase getComicListUseCase = null;
    ComicDB comicDB;
    ComicListDB comicListDB;
    ComicBasicModelDataMapper comicBasicModelDataMapper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        setContentView(R.layout.activity_main);

        ActionBar bar = this.getSupportActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ffef0000")));

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
        ComicDataStoreFactory comicDataStoreFactory = new ComicDataStoreFactory(
                this.getApplicationContext(), comicDB, comicListDB);

        ComicRepository comicRepository = ComicDataRepository.getInstance(comicDataStoreFactory,
                comicEntityDataMapper, comicBasicEntityDataMapper);


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
                initListView();
                swipeRefresh.setRefreshing(false);
            }
        }
    };

    private void initListView() {
        comicListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int itemId = (int) parent.getItemIdAtPosition(position);
                Intent myIntent = new Intent(MainActivity.this, ComicDetailsActivity.class);
                myIntent.putExtra("id", itemId); //Optional parameters
                MainActivity.this.startActivity(myIntent);
            }
        });
    }
}

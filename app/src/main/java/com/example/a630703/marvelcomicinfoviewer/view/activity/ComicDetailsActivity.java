package com.example.a630703.marvelcomicinfoviewer.view.activity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.Comic;
import com.example.a630703.marvelcomicinfoviewer.R;
import com.example.a630703.marvelcomicinfoviewer.mapper.ComicModelDataMapper;
import com.example.a630703.marvelcomicinfoviewer.model.ComicModel;
import com.example.a630703.marvelcomicinfoviewer.view.UIThread;
import com.example.data.database.ComicDB;
import com.example.data.database.ComicListDB;
import com.example.data.database.ComicListDBImpl;
import com.example.data.entity.mapper.ComicBasicEntityDataMapper;
import com.example.data.entity.mapper.ComicBasicEntityRealmMapper;
import com.example.data.entity.mapper.ComicEntityDataMapper;
import com.example.data.executor.JobExecutor;
import com.example.data.repository.ComicDataRepository;
import com.example.data.repository.ComicDataStoreFactory;
import com.example.executor.PostExecutionThread;
import com.example.executor.ThreadExecutor;
import com.example.interactor.GetComicDetailsUseCase;
import com.example.interactor.GetComicDetailsUseCaseImpl;
import com.example.repository.ComicRepository;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by a630703 on 27/04/2016.
 */
public class ComicDetailsActivity extends AppCompatActivity {

    @Bind(R.id.thumbnailDetails)
    ImageView thumbnail;
    @Bind(R.id.titleTv)
    TextView titleTV;
    @Bind(R.id.charactersTv)
    TextView charactersTv;
    @Bind(R.id.creatorsTv)
    TextView creatorsTv;
    @Bind(R.id.urlTv)
    TextView urlTv;
    @Bind(R.id.dateTv)
    TextView dateTv;
    @Bind(R.id.formatTv)
    TextView formatTv;
    @Bind(R.id.descriptionTv)
    TextView descriptionTv;
    @Bind(R.id.priceTv)
    TextView priceTv;

    private int id;
    private ComicDB comicDB;
    private ComicListDB comicListDB;
    ComicModelDataMapper comicModelDataMapper;
    private boolean isDescriptionLarge = false;
    private boolean isCharactersLarge = false;
    private boolean isCreatorsLarge = false;
    private String largeDescription;
    private String largeCharacters;
    private String largeCreators;

    GetComicDetailsUseCase getComicDetailsUseCase;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        setContentView(R.layout.comic_details_activity);

        ActionBar bar = this.getSupportActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ffef0000")));

        ButterKnife.bind(this);

        this.initializeActivity(savedInstanceState);

        ThreadExecutor threadExecutor = JobExecutor.getInstance();
        PostExecutionThread postExecutionThread = UIThread.getInstance();

        comicModelDataMapper = new ComicModelDataMapper();

        ComicBasicEntityRealmMapper comicBasicEntityRealmMapper = new ComicBasicEntityRealmMapper();
        comicListDB = new ComicListDBImpl(this.getApplicationContext(),comicBasicEntityRealmMapper);

        ComicEntityDataMapper comicEntityDataMapper = new ComicEntityDataMapper();
        ComicBasicEntityDataMapper comicBasicEntityDataMapper = new ComicBasicEntityDataMapper();
        ComicDataStoreFactory comicDataStoreFactory = new ComicDataStoreFactory(
                this.getApplicationContext(), comicDB, comicListDB);

        ComicRepository comicRepository = ComicDataRepository.getInstance(comicDataStoreFactory,
                comicEntityDataMapper, comicBasicEntityDataMapper);

        getComicDetailsUseCase = new GetComicDetailsUseCaseImpl(comicRepository,threadExecutor,postExecutionThread);

        setTextOnClickListeners();

        getComicDetails();

    }

    private void setTextOnClickListeners() {
        descriptionTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isDescriptionLarge){
                    descriptionTv.setText(Html.fromHtml(reduceString(largeDescription)));
                    isDescriptionLarge = false;
                } else{
                    descriptionTv.setText(largeDescription);
                    isDescriptionLarge = true;
                }
            }
        });

        charactersTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isCharactersLarge){
                    charactersTv.setText(Html.fromHtml(reduceString(largeCharacters)));
                    isCharactersLarge = false;
                } else{
                    charactersTv.setText(largeCharacters);
                    isCharactersLarge = true;
                }
            }
        });

        creatorsTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isCreatorsLarge){
                    creatorsTv.setText(Html.fromHtml(reduceString(largeCreators)));
                    isCreatorsLarge = false;
                } else{
                    creatorsTv.setText(largeCreators);
                    isCreatorsLarge = true;
                }
            }
        });
    }

    void getComicDetails(){
        getComicDetailsUseCase.execute(id, getComicDetailsCallback);
    }

    GetComicDetailsUseCase.Callback getComicDetailsCallback = new GetComicDetailsUseCase.Callback(){

        @Override
        public void onComicLoaded(Comic comic) {
            ComicModel comicModel = comicModelDataMapper.transform(comic);
            titleTV.setText(comicModel.getTitle());

            largeDescription = comicModel.getDescription();
            if (largeDescription == null){ largeDescription = "...";}
            descriptionTv.setText(Html.fromHtml(reduceString(largeDescription)));

            dateTv.setText(comicModel.getDate());
            formatTv.setText(comicModel.getFormat());

            largeCharacters = listToString(comicModel.getCharacters());
            if (largeCharacters == null){ largeCharacters = "...";}
            charactersTv.setText(Html.fromHtml(reduceString(largeCharacters)));
            largeCreators = listToString(comicModel.getCreators());
            if (largeCreators == null){ largeCreators = "...";}
            creatorsTv.setText(Html.fromHtml(reduceString(largeCreators)));

            urlTv.setClickable(true);
            urlTv.setMovementMethod(LinkMovementMethod.getInstance());
            String text = "<a href='"+comicModel.getUrl()+"'> "+comicModel.getTitle()+" </a>";
            urlTv.setText(Html.fromHtml(text));

            priceTv.setText(comicModel.getPrice()+"$");

            ImageLoader imageLoader = ImageLoader.getInstance();
            imageLoader.displayImage(comicModel.getThumbnail(),thumbnail);
        }

    };

    private String listToString(ArrayList<String> stringList){
        String result = "";
        for (String part : stringList){
            result += part+", ";
        }
        if (result.length() > 2) {
            result = result.substring(0, result.length() - 2);
        }
        return result;
    }

    private String reduceString(String input){
        String result = input;
        if (input.length() > 50){
            result = input.substring(0,50);
            result += " <html><u>more...";
        }
        return result;
    }

    private void initializeActivity(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            this.id = getIntent().getIntExtra("id", -1);
        } else {
            this.id = savedInstanceState.getInt("id");
        }
    }

}

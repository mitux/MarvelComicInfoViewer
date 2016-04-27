package com.example.a630703.marvelcomicinfoviewer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

/**
 * Created by a630703 on 27/04/2016.
 */
public class ComicDetailsActivity extends AppCompatActivity {

    private int id;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        setContentView(R.layout.comic_details_activity);

        this.initializeActivity(savedInstanceState);


    }

    private void initializeActivity(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            this.id = getIntent().getIntExtra("id", -1);
        } else {
            this.id = savedInstanceState.getInt("id");
        }
    }

}

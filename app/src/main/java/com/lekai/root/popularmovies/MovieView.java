package com.lekai.root.popularmovies;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
/**
 * Created by root on 4/13/17.
 */

public class MovieView extends AppCompatActivity {
    ImageView theImage ;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_view);
        theImage = (ImageView) findViewById(R.id.movie_poster);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE){
            theImage.setMinimumWidth(300);

        }
    }
}

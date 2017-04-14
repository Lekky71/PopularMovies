package com.lekai.root.popularmovies;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class MovieActivity extends AppCompatActivity {
    TextView title ;
    ImageView image ;
    TextView overview;
    TextView userRating;
    TextView date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        title = (TextView) findViewById(R.id.movie_name);
         image = (ImageView) findViewById(R.id.movie_image);
        overview =(TextView) findViewById(R.id.movie_plot);
        userRating = (TextView) findViewById(R.id.movie_rating);
        date = (TextView) findViewById(R.id.movie_date);
        Intent receiveIntent = getIntent();
        String movieName = receiveIntent.getStringExtra("title");
        String posterImage = receiveIntent.getStringExtra("imagePath");
        String plot = receiveIntent.getStringExtra("plot");
        String  ratings = receiveIntent.getStringExtra("ratings");
        String  releaseDate = receiveIntent.getStringExtra("releaseDate");

        title.setText(movieName);
        Picasso.with(this).load(posterImage)
                .resize(400,400)
                .into(image);
        overview.setText("About this movie : "+plot);
        userRating.setText(ratings+"/10");
        date.setText(releaseDate);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Movie Detail");
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#2c3539")));


    }
}

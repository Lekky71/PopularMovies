package com.lekai.root.popularmovies;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lekai.root.popularmovies.Favorites.FavoritesContract;
import com.lekai.root.popularmovies.MovieReviews.Review;
import com.lekai.root.popularmovies.MovieReviews.ReviewAdapter;
import com.lekai.root.popularmovies.MovieReviews.ReviewInfo;
import com.lekai.root.popularmovies.NetworkTools.MovieDBEndpointInterface;
import com.lekai.root.popularmovies.videos.Video;
import com.lekai.root.popularmovies.videos.VideoAdapter;
import com.lekai.root.popularmovies.videos.VideoInfo;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.support.v7.widget.RecyclerView.Adapter;

public class MovieActivity extends AppCompatActivity  {
    TextView title ;
    ImageView image ;
    TextView overview;
    TextView userRating;
    TextView date;
    ArrayList<Video> allVideos;
    ArrayList<Review> allReviews;
    Context context;
    public final static String BASE_URL = "https://api.themoviedb.org/";
    Adapter videoAdapter ;
    RecyclerView videoRecyclerView ;
    LinearLayoutManager videolayoutManager;
    Adapter reviewAdapter;
    RecyclerView reviewRecyclerView;
    LinearLayoutManager reviewlinearLayoutManager;
    ViewPager viewPager;
    Button addToFavorites;
    String movieName  ;
    String posterImage ;
    String plot ;
    String ratings ;
    String releaseDate ;
    String movieId ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        context = getBaseContext();
        title = (TextView) findViewById(R.id.movie_name);
         image = (ImageView) findViewById(R.id.movie_image);
        overview =(TextView) findViewById(R.id.movie_plot);
        userRating = (TextView) findViewById(R.id.movie_rating);
        date = (TextView) findViewById(R.id.movie_date);
        addToFavorites = (Button) findViewById(R.id.add_favorite_button);
        viewPager = (ViewPager) findViewById(R.id.pager);
        Intent receiveIntent = getIntent();
        movieName = receiveIntent.getStringExtra("title");
        posterImage = receiveIntent.getStringExtra("imagePath");
        plot = receiveIntent.getStringExtra("plot");
        ratings = receiveIntent.getStringExtra("ratings");
        releaseDate = receiveIntent.getStringExtra("releaseDate");
        movieId = receiveIntent.getStringExtra("movie_id");
        videoRecyclerView = (RecyclerView) findViewById(R.id.trailers_recyclerview);
        videoRecyclerView.setHasFixedSize(true);
        videolayoutManager = new LinearLayoutManager(this);
        videolayoutManager.setAutoMeasureEnabled(true);
        videoRecyclerView.setLayoutManager(videolayoutManager);
        allVideos = new ArrayList<Video>();
        reviewRecyclerView = (RecyclerView) findViewById(R.id.reviews_recyclerview);
        reviewRecyclerView.setHasFixedSize(true);
        reviewlinearLayoutManager = new LinearLayoutManager(this);
        reviewlinearLayoutManager.setAutoMeasureEnabled(true);
        reviewRecyclerView.setLayoutManager(reviewlinearLayoutManager);
        allReviews = new ArrayList<Review>();
        getEveryVideo(movieId);
        getEveryReview(movieId);
        if(savedInstanceState!=null){
            allVideos = savedInstanceState.getParcelableArrayList("videos");
            allReviews = savedInstanceState.getParcelableArrayList("reviews");
        }
        videoAdapter = new VideoAdapter(allVideos,this);
        videoRecyclerView.setAdapter(videoAdapter);
        reviewAdapter = new ReviewAdapter(allReviews,this);
        reviewRecyclerView.setAdapter(reviewAdapter);
        title.setText(movieName);
        Picasso.with(this).load(posterImage)
                .resize(400,400)
                .placeholder(R.color.colorBlack)
                .into(image);
        overview.setText("About this movie : "+plot);
        userRating.setText(ratings+"/10");
        date.setText(releaseDate);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Movie Detail");
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#2c3539")));

        addToFavorites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues contentValues = new ContentValues();
                contentValues.put(FavoritesContract.FavoritesEntry.MOVIE_TITLE,movieName);
                contentValues.put(FavoritesContract.FavoritesEntry.MOVIE_RATING,ratings);
                contentValues.put(FavoritesContract.FavoritesEntry.MOVIE_DATE,releaseDate);
                contentValues.put(FavoritesContract.FavoritesEntry.COLUMN_MOVIE_ID,movieId);
                contentValues.put(FavoritesContract.FavoritesEntry.MOVIE_IMAGEPATH,posterImage);
                contentValues.put(FavoritesContract.FavoritesEntry.MOVIE_OVERVIEW,plot);
                Uri uri = getContentResolver().insert(FavoritesContract.FavoritesEntry.CONTENT_URI,contentValues);
                if(uri !=null){
                    Toast.makeText(getBaseContext(),"Added to Favorites",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private void getEveryVideo(String word){

        Retrofit retrofit;

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)

                .addConverterFactory(GsonConverterFactory.create())

                .build();

        MovieDBEndpointInterface apiService = retrofit.create(MovieDBEndpointInterface.class);
        Call<VideoInfo> call = apiService.getAllVideos(word);
        call.enqueue(new Callback<VideoInfo>() {
            @Override
            public void onResponse(Call<VideoInfo> call, Response<VideoInfo> response) {
                allVideos.clear();
                allVideos.addAll(response.body().getResults());
                videoAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<VideoInfo> call, Throwable t) {
                String TAG = "failed to connect";
                Log.e(TAG, t.getMessage());
                Toast.makeText(getBaseContext(), "An error occured, Check your internet connection", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getEveryReview(String word){

        Retrofit retrofit;

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)

                .addConverterFactory(GsonConverterFactory.create())

                .build();

        MovieDBEndpointInterface apiService = retrofit.create(MovieDBEndpointInterface.class);
        Call<ReviewInfo> call = apiService.getAllReviews(word);
        call.enqueue(new Callback<ReviewInfo>() {
            @Override
            public void onResponse(Call<ReviewInfo> call, Response<ReviewInfo> response) {
                allReviews.clear();
                allReviews.addAll(response.body().getReviews());
                reviewAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ReviewInfo> call, Throwable t) {
                String TAG = "failed to connect";
                Log.e(TAG, t.getMessage());
                Toast.makeText(getBaseContext(), "An error occured, Check your internet connection", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        ArrayList<Video> myVideos = allVideos;
        ArrayList<Review> myReviews = allReviews;
        outState.putParcelableArrayList("videos",myVideos);
        outState.putParcelableArrayList("reviews",myReviews);
    }
}

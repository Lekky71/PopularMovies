package com.lekai.root.popularmovies;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.lekai.root.popularmovies.NetworkTools.MovieDBEndpointInterface;
import com.lekai.root.popularmovies.movie.Movie;
import com.lekai.root.popularmovies.movie.MoviesInfo;
import com.lekai.root.popularmovies.movie.ResultAdapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    public final static String BASE_URL = "https://api.themoviedb.org/";
    ArrayList<Movie> movies ;
    RecyclerView recyclerView ;
    RecyclerView.LayoutManager mLayoutManager;
    ResultAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.each_movie_recyclerview);

        recyclerView.setHasFixedSize(true);
        mLayoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(mLayoutManager);
        mLayoutManager.setAutoMeasureEnabled(true);
        movies = new ArrayList<Movie>();
//        choice = (TextView) findViewById(R.id.movies_choice);
//        choice.setText(" Showing movies by popular");
        getMoviesPopularity();
        if(savedInstanceState!=null){
            movies = savedInstanceState.getParcelableArrayList("result");
        }
        mAdapter = new ResultAdapter(movies,this);
        recyclerView.setAdapter(mAdapter);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Pop Movies");
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#2c3539")));


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = new MenuInflater(this);
        inflater.inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if(itemId == R.id.popular_sort){
//            choice.setText(" Showing movies by popular");
            getMoviesPopularity();
        }
        if(itemId==R.id.top_rated_sort){
//            choice.setText(" Showing movies by top ratings");

            getMoviesUsingRatings();
        }
        return super.onOptionsItemSelected(item);
    }

    private void getMoviesPopularity(){

        Retrofit retrofit;

        retrofit = new Retrofit.Builder()

                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MovieDBEndpointInterface apiService = retrofit.create(MovieDBEndpointInterface.class);

        Call<MoviesInfo> call = apiService.getMoviesUsingPopularity();
        call.enqueue(new Callback<MoviesInfo>() {
            @Override
            public void onResponse(Call<MoviesInfo> call, Response<MoviesInfo> response) {
                movies.clear();
                movies.addAll(response.body().getResults());
                mAdapter.notifyDataSetChanged();


            }

            @Override
            public void onFailure(Call<MoviesInfo> call, Throwable t) {
                String TAG = "failed to connect";
                Log.e(TAG, t.getMessage());
                Toast.makeText(getBaseContext(), "An error occured: " + t.getMessage(), Toast.LENGTH_SHORT).show();

            }


        });
    }


    private void getMoviesUsingRatings(){

        Retrofit retrofit;

        retrofit = new Retrofit.Builder()

                .baseUrl(BASE_URL)


                .addConverterFactory(GsonConverterFactory.create())

                .build();
        MovieDBEndpointInterface apiService = retrofit.create(MovieDBEndpointInterface.class);

        Call<MoviesInfo> call = apiService.getMoviesUsingRatings();
        call.enqueue(new Callback<MoviesInfo>() {
            @Override
            public void onResponse(Call<MoviesInfo> call, Response<MoviesInfo> response) {
                movies.clear();
                movies.addAll(response.body().getResults());
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<MoviesInfo> call, Throwable t) {
                String TAG = "failed to connect";
                Log.e(TAG, t.getMessage());
                Toast.makeText(getBaseContext(), "An error occured: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        ArrayList<Movie> lastMovies = movies;
        outState.putParcelableArrayList("result",lastMovies);
    }

}

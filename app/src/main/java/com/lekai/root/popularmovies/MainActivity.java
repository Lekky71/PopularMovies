package com.lekai.root.popularmovies;

import android.content.Context;
import android.content.res.Configuration;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.lekai.root.popularmovies.Favorites.FavoriteAdapter;
import com.lekai.root.popularmovies.Favorites.FavoritesContract;
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

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>{
    private static final String TAG = MainActivity.class.getSimpleName();
    public final static String BASE_URL = "https://api.themoviedb.org/";
    ArrayList<Movie> movies ;
    RecyclerView recyclerView ;
    RecyclerView.LayoutManager mLayoutManager;
    ResultAdapter mAdapter;
    FavoriteAdapter favoriteAdapter;
    int orientation ;
    int cells = 2;
    private static final int TASK_LOADER_ID = 0;
    ItemTouchHelper slider;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.each_movie_recyclerview);
        orientation = this.getResources().getConfiguration().orientation;
        recyclerView.setHasFixedSize(true);
        context = getBaseContext();
        if(orientation == Configuration.ORIENTATION_LANDSCAPE){
            cells =4;
        }
        mLayoutManager = new GridLayoutManager(this,cells);
        recyclerView.setLayoutManager(mLayoutManager);
        mLayoutManager.setAutoMeasureEnabled(true);
        movies = new ArrayList<Movie>();
        getMoviesPopularity();
        if(savedInstanceState!=null){
            movies = savedInstanceState.getParcelableArrayList("result");
            getSupportLoaderManager().initLoader(TASK_LOADER_ID, null, this);
        }
        mAdapter = new ResultAdapter(movies,this);
        favoriteAdapter = new FavoriteAdapter(this);
        recyclerView.setAdapter(mAdapter);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Pop Movies");
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#2c3539")));



        getSupportLoaderManager().initLoader(TASK_LOADER_ID, null, this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // re-queries for all tasks
        getSupportLoaderManager().restartLoader(TASK_LOADER_ID, null, this);
    }
    public void makeSlider(){

            slider = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
                @Override
                public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                    return false;
                }
                @Override
                public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                    if(recyclerView.getAdapter()==favoriteAdapter) {

                        int id = (int) viewHolder.itemView.getTag();
                        String stringId = Integer.toString(id);
                        Uri uri = FavoritesContract.FavoritesEntry.CONTENT_URI;
                        uri = uri.buildUpon().appendPath(stringId).build();
                        getContentResolver().delete(uri, null, null);
                        getSupportLoaderManager().restartLoader(TASK_LOADER_ID, null, MainActivity.this);
                    } else{

                    }
                }
            });
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
            recyclerView.setAdapter(mAdapter);
            getMoviesPopularity();
            slider.attachToRecyclerView(null);
        }
        if(itemId==R.id.top_rated_sort){
            recyclerView.setAdapter(mAdapter);
            getMoviesUsingRatings();
            slider.attachToRecyclerView(null);

        }
        if(itemId==R.id.my_favorites_sort){
            recyclerView.setAdapter(favoriteAdapter);
            makeSlider();
            slider.attachToRecyclerView(recyclerView);
            Snackbar.make(recyclerView,"You can swipe left or right to delete",Snackbar.LENGTH_SHORT).show();
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
                Toast.makeText(getBaseContext(), "An error occured, Check your internet connection", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(getBaseContext(), "An error occured, Check your internet connection", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        ArrayList<Movie> lastMovies = movies;
        outState.putParcelableArrayList("result",lastMovies);
        getSupportLoaderManager().initLoader(TASK_LOADER_ID, null, this);
    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, final Bundle loaderArgs) {

        return new AsyncTaskLoader<Cursor>(this) {

            // Initialize a Cursor, this will hold all the task data
            Cursor mTaskData = null;

            // onStartLoading() is called when a loader first starts loading data
            @Override
            protected void onStartLoading() {
                if (mTaskData != null) {
                    // Delivers any previously loaded data immediately
                    deliverResult(mTaskData);
                } else {
                    // Force a new load
                    forceLoad();
                }
            }

            // loadInBackground() performs asynchronous loading of data
            @Override
            public Cursor loadInBackground() {
                // Will implement to load data

                // Query and load all task data in the background; sort by priority
                try {
                    return getContentResolver().query(FavoritesContract.FavoritesEntry.CONTENT_URI,
                            null,
                            null,
                            null,
                            null);

                } catch (Exception e) {
                    Log.e(TAG, "Failed to asynchronously load data.");
                    e.printStackTrace();
                    return null;
                }
            }

            // deliverResult sends the result of the load, a Cursor, to the registered listener
            public void deliverResult(Cursor data) {
                mTaskData = data;
                super.deliverResult(data);
            }
        };
    }
    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        // Update the data that the adapter uses to create ViewHolders
        favoriteAdapter.swapCursor(data);
    }
    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        favoriteAdapter.swapCursor(null);
    }
}

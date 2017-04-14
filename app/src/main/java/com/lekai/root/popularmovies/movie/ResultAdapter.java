package com.lekai.root.popularmovies.movie;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import com.lekai.root.popularmovies.MainActivity;
import com.lekai.root.popularmovies.MovieActivity;
import com.lekai.root.popularmovies.R;
import com.squareup.picasso.Picasso;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by root on 4/12/17.
 */

public class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.ResultAdapterViewHolder>{
    String [] imagesUrl ;
    Context context ;
    private static ArrayList<Movie> movies ;
    public ResultAdapter(ArrayList<Movie> myMovie,Context myContext){
        context = myContext;
        movies = myMovie;
    }
    public class ResultAdapterViewHolder extends RecyclerView.ViewHolder{
        public ImageView movieImage ;

        public ResultAdapterViewHolder(View view){
            super(view);
            movieImage = (ImageView) view.findViewById(R.id.movie_poster);
        }

    }

    @Override
    public ResultAdapter.ResultAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.movie_view,parent,false);
        return new ResultAdapterViewHolder(view);
    }


//    private URL getImageUrl(String imageUrl) {
//        Uri.Builder builder = new Uri.Builder();
//        String BASE_URL = "http://image.tmdb.org/t/p/";
//        Uri uri = builder.path(BASE_URL)
//                .appendPath("w185")
//                .appendPath(imageUrl)
//                .build();
//        URL url = null ;
//        try{
//            url = new URL(uri.toString());
//        }
//        catch (MalformedURLException me){
//            me.printStackTrace();
//        }
//        return url ;
//    }

    @Override
    public void onBindViewHolder(final ResultAdapterViewHolder holder, final int position) {
//        URL imageUrl = getImageUrl(movies.get(position).getPosterPath());
            final String imageUrl = "http://image.tmdb.org/t/p/w185/"+movies.get(position).getPosterPath();
        Picasso.with(context).load(imageUrl)
//                .resize(600,700)
                .fit()
//                .centerCrop()
                .placeholder(R.color.colorAccent)
                .into(holder.movieImage);
//        Toast.makeText(context, movies.get(position).getPosterPath(),Toast.LENGTH_LONG).show();
        holder.movieImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MovieActivity.class);
                intent.putExtra("title",movies.get(position).getOriginalTitle());
                intent.putExtra("imagePath",imageUrl);
                intent.putExtra("plot",movies.get(position).getOverview());
                intent.putExtra("ratings",movies.get(position).getVoteAverage().toString() );
                intent.putExtra("releaseDate",movies.get(position).getReleaseDate());
                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return movies.size();
    }




}

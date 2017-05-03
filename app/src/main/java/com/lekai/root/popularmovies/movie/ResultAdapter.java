package com.lekai.root.popularmovies.movie;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.lekai.root.popularmovies.MovieActivity;
import com.lekai.root.popularmovies.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


/**
 * Created by root on 4/12/17.
 */

public class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.ResultAdapterViewHolder>{
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
    public ResultAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.movie_view,parent,false);
        return new ResultAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ResultAdapterViewHolder holder, final int position) {
            final String imageUrl = "http://image.tmdb.org/t/p/w185/"+movies.get(position).getPosterPath();
        Picasso.with(context).load(imageUrl)
                .fit()
                .placeholder(R.color.colorBlack)
                .into(holder.movieImage);
        holder.movieImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MovieActivity.class);
                intent.putExtra("title",movies.get(position).getOriginalTitle());
                intent.putExtra("imagePath",imageUrl);
                intent.putExtra("plot",movies.get(position).getOverview());
                intent.putExtra("ratings",movies.get(position).getVoteAverage().toString() );
                intent.putExtra("releaseDate",movies.get(position).getReleaseDate());
                intent.putExtra("video_boolean",movies.get(position).getVideo());
                intent.putExtra("movie_id",movies.get(position).getId().toString());
                context.startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return movies.size();
    }

}

package com.lekai.root.popularmovies.Favorites;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.lekai.root.popularmovies.MovieActivity;
import com.lekai.root.popularmovies.R;
import com.squareup.picasso.Picasso;

/**
 * Created by root on 5/4/17.
 */

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder> {
    Context context;
    Cursor mCursor;
    public FavoriteAdapter(Context myContext){
        context = myContext;
    }
    @Override
    public FavoriteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.favorite_view,parent,false);
        return new FavoriteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FavoriteViewHolder holder, int position) {
        int idIndex = mCursor.getColumnIndex(FavoritesContract.FavoritesEntry._ID);
        int titleIndex = mCursor.getColumnIndex(FavoritesContract.FavoritesEntry.MOVIE_TITLE);
        int ratingIndex = mCursor.getColumnIndex(FavoritesContract.FavoritesEntry.MOVIE_RATING);
        int dateIndex = mCursor.getColumnIndex(FavoritesContract.FavoritesEntry.MOVIE_DATE);
        int movieIdIndex = mCursor.getColumnIndex(FavoritesContract.FavoritesEntry.COLUMN_MOVIE_ID);
        int overviewIndex = mCursor.getColumnIndex(FavoritesContract.FavoritesEntry.MOVIE_OVERVIEW);
        int posterPathIndex = mCursor.getColumnIndex(FavoritesContract.FavoritesEntry.MOVIE_IMAGEPATH);

        mCursor.moveToPosition(position);

        int id = mCursor.getInt(idIndex);
        final String title = mCursor.getString(titleIndex);
        final String ratings = mCursor.getString(ratingIndex);
        final String date = mCursor.getString(dateIndex);
        final String movieId = mCursor.getString(movieIdIndex);
        final String overview = mCursor.getString(overviewIndex);
        String posterPath = mCursor.getString(posterPathIndex);
        System.out.println(posterPath+"this is it");

        holder.itemView.setTag(id);
//        holder.favoriteTitle.setText(title);
//        holder.favoriteRating.setText(ratings);
//        holder.favoriteDate.setText(date);
        final String imageUrl = posterPath;
        Picasso.with(context).load(imageUrl)
                .fit()
                .placeholder(R.color.colorBlack)
                .into(holder.backgroundImage);

        holder.backgroundImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MovieActivity.class);
                intent.putExtra("title",title);
                intent.putExtra("imagePath",imageUrl);
                intent.putExtra("plot",overview);
                intent.putExtra("ratings",ratings);
                intent.putExtra("releaseDate",date);
                intent.putExtra("movie_id",movieId);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        if (mCursor == null) {
            return 0;
        }
        return mCursor.getCount();
    }

    public Cursor swapCursor(Cursor c) {
        // check if this cursor is the same as the previous cursor (mCursor)
        if (mCursor == c) {
            return null; // bc nothing has changed
        }
        Cursor temp = mCursor;
        this.mCursor = c; // new cursor value assigned

        //check if this is a valid cursor, then update the cursor
        if (c != null) {
            this.notifyDataSetChanged();
        }
        return temp;
    }

    public class FavoriteViewHolder extends RecyclerView.ViewHolder {
        ImageView backgroundImage;
        public FavoriteViewHolder(View itemView) {
            super(itemView);
            backgroundImage = (ImageView) itemView.findViewById(R.id.favorite_image);
        }
    }
}

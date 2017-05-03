package com.lekai.root.popularmovies.Favorites;

import android.provider.BaseColumns;

/**
 * Created by root on 5/3/17.
 */

public class FavoritesContract {

    public static  final class FavoritesEntry implements BaseColumns{
        public static final String TABLE_NAME = "favorites";
        public static final String COLUMN_MOVIE_ID = "movieId";
        public static final String MOVIE_TITLE = "title";
        public static final String MOVIE_RATING = "rating";
    }
}

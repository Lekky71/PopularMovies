package com.lekai.root.popularmovies.Favorites;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by root on 5/3/17.
 */

public class FavoritesContract {
    public static final String AUTHORITY = "com.lekai.root.popularmovies";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://"+AUTHORITY);
    public static final String PATH_FAVORITES = "favorites";
    public static final class FavoritesEntry implements BaseColumns{
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_FAVORITES).build();
        public static final String TABLE_NAME = "favorites" ;
        public static final String COLUMN_MOVIE_ID = "movieId";
        public static final String MOVIE_TITLE = "title";
        public static final String MOVIE_RATING = "rating";
        public static final String MOVIE_DATE = "date";
        public static final String MOVIE_IMAGEPATH ="imagepath";
        public static final String MOVIE_OVERVIEW = "plot";
    }
}

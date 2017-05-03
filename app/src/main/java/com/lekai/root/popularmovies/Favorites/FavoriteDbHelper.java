package com.lekai.root.popularmovies.Favorites;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.lekai.root.popularmovies.Favorites.FavoritesContract.FavoritesEntry;

import static com.lekai.root.popularmovies.Favorites.FavoritesContract.FavoritesEntry.*;

/**
 * Created by root on 5/3/17.
 */

public class FavoriteDbHelper extends SQLiteOpenHelper {

    //name of the database
    private final static String DATABASE_NAME = "favoritesDb.db";
    private static final int VERSION = 1;

    public FavoriteDbHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String CREATE_TABLE = "CREATE TABLE "+ TABLE_NAME+ " ("+
                FavoritesEntry._ID + " INTEGER PRIMARY KEY, "+
                FavoritesEntry.MOVIE_TITLE + " TEXT NOT NULL, "+
                FavoritesEntry.COLUMN_MOVIE_ID + " TEXT NOT NULL, "+
                FavoritesEntry.MOVIE_RATING + " TEXT NOT NULL, "+
                FavoritesEntry.MOVIE_DATE + " TEXT NOT NULL, "+
                FavoritesEntry.MOVIE_OVERVIEW+ "TEXT NOT NULL);";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP IF TABLE EXISTS "+FavoritesEntry.TABLE_NAME);
        onCreate(db);

    }
}

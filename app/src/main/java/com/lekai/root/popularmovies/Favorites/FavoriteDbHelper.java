package com.lekai.root.popularmovies.Favorites;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by root on 5/3/17.
 */

public class FavoriteDbHelper extends SQLiteOpenHelper {

    //name of the database
    private final static String DATABASE_NAME = "favoritesDb.db";
    private static final int VERSION = 1;

    public FavoriteDbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

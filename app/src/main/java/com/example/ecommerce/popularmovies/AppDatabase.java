package com.example.ecommerce.popularmovies;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.util.Log;

import java.util.concurrent.locks.Lock;
 @Database(entities = {Movie.class} ,version = 1 ,exportSchema = false)
public abstract class AppDatabase  extends RoomDatabase {


    private  static  final String LOG_TAG = AppDatabase.class.getSimpleName() ;
    private  static  final Object LOCK = new Object() ;
    private  static  final String Database_Name = "Movie_Database" ;
    private static AppDatabase sInstance ;

public static AppDatabase getInstance(Context context)
    {
        if (sInstance==null)
    {
        synchronized (LOCK)
        {
            Log.d(LOG_TAG ,"Creating new database instance") ;
            sInstance = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class,AppDatabase.Database_Name)
                   // .allowMainThreadQueries()
            .build();
        }
    }
Log.d(LOG_TAG,"returne database instance");
        return sInstance ;

    }
    public abstract FavoritesDao favoritesDao ();
}

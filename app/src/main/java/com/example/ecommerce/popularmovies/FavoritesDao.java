package com.example.ecommerce.popularmovies;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.icu.text.Replaceable;

import java.util.List;
import static android.arch.persistence.room.OnConflictStrategy.REPLACE ;

@Dao
public interface FavoritesDao {

    @Query("select * from Movie")
     LiveData< List<Movie>> loadallmovies();

    @Insert(onConflict = REPLACE )
    void insertTask(Movie movie);

    @Delete
    void  deletTask(Movie movie) ;

    @Query("SELECT COUNT(id) FROM Movie WHERE id = :id")
    Integer ifExists(int id);

    @Query("SELECT imageurl FROM Movie")
   LiveData <List<String> >getAllImageurl();

    @Query("DELETE FROM Movie WHERE id = :id")
    void deleteThisMovie(int id);

    // select all movie object instead of select image url only
    @Query("SELECT * FROM Movie ")
    LiveData<List<Movie>> getAllFavorites();


    @Query("DELETE FROM Movie")
    void deleteAllFavorites();



}

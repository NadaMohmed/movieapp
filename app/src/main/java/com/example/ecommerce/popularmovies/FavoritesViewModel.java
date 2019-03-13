package com.example.ecommerce.popularmovies;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

public class FavoritesViewModel extends AndroidViewModel {

    private FavoritesRepository favoritesRepository;
    private LiveData<List<Movie>> allFavorites;

    public FavoritesViewModel(@NonNull Application application) {
        super(application);
        favoritesRepository = new FavoritesRepository(application);
        allFavorites = favoritesRepository.getAllFavorites();
    }

    public void insert(Movie movieResults) {
        favoritesRepository.insert(movieResults);
    }

    public void deleteAllFavorites() {
        favoritesRepository.deleteAllFavorites();
    }

    public LiveData<List<Movie>> getAllFavorites() {
        return allFavorites;
    }

}
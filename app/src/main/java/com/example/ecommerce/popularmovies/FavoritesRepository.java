package com.example.ecommerce.popularmovies;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class FavoritesRepository {

    private FavoritesDao favoritesDao;

    private LiveData<List<Movie>> allFavorites;


    public FavoritesRepository(Application application) {
        AppDatabase database = AppDatabase.getInstance(application);
        favoritesDao = database.favoritesDao();
        allFavorites = favoritesDao.getAllFavorites();

    }

    public void insert(Movie fav) {
        new InsertFavoritesAsyncTask(favoritesDao).execute(fav);

    }


    public void deleteAllFavorites() {
        new DeleteAllFavoritesAsyncTask(favoritesDao).execute();
    }

    public LiveData<List<Movie>> getAllFavorites() {
        return allFavorites;
    }


    private static class InsertFavoritesAsyncTask extends AsyncTask<Movie, Void, Void> {
        private FavoritesDao favoritesDao;

        private InsertFavoritesAsyncTask(FavoritesDao favoritesDao) {
            this.favoritesDao = favoritesDao;
        }

        @Override
        protected Void doInBackground(Movie... movieResults) {
            favoritesDao.insertTask(movieResults[0]);
            return null;
        }
    }


    private static class DeleteAllFavoritesAsyncTask extends AsyncTask<Void, Void, Void> {
        private FavoritesDao favoritesDao;

        private DeleteAllFavoritesAsyncTask(FavoritesDao favoritesDao) {
            this.favoritesDao = favoritesDao;
        }

        @Override
        protected Void doInBackground(Void... Voids) {
            favoritesDao.deleteAllFavorites();
            return null;
        }
    }
      }





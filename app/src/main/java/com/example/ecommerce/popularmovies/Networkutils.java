package com.example.ecommerce.popularmovies;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class Networkutils {

/*    final static String BASE_URL =
            "https://api.themoviedb.org/3/discover/movie";
// to sort by in popular and igth rated movies
    final static String PARAM_SORT = "sort_by";*/

    final static String apikey = "api_key" ;
    final static  String valueofappikey = "3a5397d56bbe2f929da0cab8113fd2c8" ;

    public static URL buildUrl() {
        // COMPLETED (1) Fill in this method to build the proper Github query URL
        Uri builtUri = Uri.parse("https://api.themoviedb.org/3/movie/popular").buildUpon()
                .appendQueryParameter("language","en-US")
                .appendQueryParameter("page","1")
                .appendQueryParameter(apikey, valueofappikey)
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }

    // build url for hight rated movies

    public static URL buildUrlfortoprated() {
        // COMPLETED (1) Fill in this method to build the proper Github query URL
        Uri builtUri = Uri.parse("https://api.themoviedb.org/3/movie/top_rated").buildUpon()
                .appendQueryParameter("language","en-US")
                .appendQueryParameter("page","1")
                .appendQueryParameter(apikey, valueofappikey)
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }

    // build url for movie trailers
    public static URL buildUrlforTrailers(String id) {
        // COMPLETED (1) Fill in this method to build the proper Github query URL
        Uri builtUri = Uri.parse("https://api.themoviedb.org/3/movie").buildUpon()
                .appendPath(id)
                .appendPath("videos")
                .appendQueryParameter(apikey, valueofappikey)
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }

    // build url for movie reveiws
    public static URL buildUrlforReviews(String id) {
        // COMPLETED (1) Fill in this method to build the proper Github query URL
        Uri builtUri = Uri.parse("https://api.themoviedb.org/3/movie").buildUpon()
                .appendPath(id)
                .appendPath("reviews")
                .appendQueryParameter(apikey, valueofappikey)
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }



    /*




    /**
     * This method returns the entire result from the HTTP response.
     *
     * @param url The URL to fetch the HTTP response from.
     * @return The contents of the HTTP response.
     * @throws IOException Related to network and stream reading
     */
    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }



}

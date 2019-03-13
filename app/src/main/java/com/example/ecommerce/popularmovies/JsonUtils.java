package com.example.ecommerce.popularmovies;

import android.os.Parcelable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils  {
   public static   Movie movieobject ;

   public static String typeofmovie ;

    public static void setTypeofmovie(String typeofmovie) {
        JsonUtils.typeofmovie = typeofmovie;
    }

    public static String getTypeofmovie() {
        return typeofmovie;
    }

public static List<Movie> getmovieobjects (String json)

        throws JSONException {

        //"http://image.tmdb.org/t/p/" + "w185" +"/"+
    List<Movie>  movieList = new ArrayList<Movie>() ;

    //JSONObject variable = jsonObject.getJSONObject("results") ;
    JSONObject jsonObject = new JSONObject(json) ;
    JSONArray resultsarray = jsonObject.getJSONArray("results") ;
    JSONObject objectwithspecificposition ;
    for (int i=0 ; i<resultsarray.length();i++)

    {
         Movie movieobject ;
        objectwithspecificposition= resultsarray.getJSONObject(i) ;


            String title = objectwithspecificposition.getString("title");
            String releasedate = objectwithspecificposition.getString("release_date") ;
            String vote_average = String.valueOf( objectwithspecificposition.getDouble("vote_average")) ;
            String  image =  objectwithspecificposition.getString("poster_path") ;
            String overview = objectwithspecificposition.getString("overview") ;
            String ID = String.valueOf( objectwithspecificposition.getInt("id") );
            movieobject = new Movie(title,releasedate,vote_average,image,overview,ID) ;
           movieList.add(movieobject) ;
    }

        return movieList ;

}

    public static List<String> getimageurl(String json)
            throws JSONException {
        List<String>  imageresuls = new ArrayList<String>() ;

        JSONObject jsonObj = new JSONObject(json) ;

        //JSONObject variable = jsonObject.getJSONObject("results") ;
        JSONArray variable = jsonObj.getJSONArray("results") ;
        imageresuls.clear();
        for (int i=0 ; i<variable.length();i++)

        {
            JSONObject obj = variable.getJSONObject(i) ;
            imageresuls.add(obj.getString("poster_path")) ;

        }
        return imageresuls ;
    }

    public  Movie getdeails (String json ,int pos )
            throws JSONException
    {

        Movie movieobject ;
        JSONObject jsonObject = new JSONObject(json) ;
        JSONArray resultsarray = jsonObject.getJSONArray("results") ;
        JSONObject objectwithspecificposition= resultsarray.getJSONObject(pos) ;
        String title = objectwithspecificposition.getString("title");
        String releasedate = objectwithspecificposition.getString("release_date") ;
        String vote_average = String.valueOf( objectwithspecificposition.getDouble("vote_average")) ;
        String imageurl = "http://image.tmdb.org/t/p/" + "w185" +"/"+ objectwithspecificposition.getString("poster_path") ;
        String overview = objectwithspecificposition.getString("overview") ;
        String ID = String.valueOf( objectwithspecificposition.getInt("id") );
        movieobject = new Movie(title,releasedate,vote_average,imageurl,overview,ID) ;
        return movieobject ;

    }
// new returne list of movies

    public  Movie getdeailsofmovie (String json, String url)
            throws JSONException
    {
         //Movie movieobject = null;
        JSONObject jsonObject = new JSONObject(json) ;
        JSONArray resultsarray = jsonObject.getJSONArray("results") ;
        JSONObject objectwithspecificposition ;
        for (int i=0 ; i<resultsarray.length();i++)

        {

             objectwithspecificposition= resultsarray.getJSONObject(i) ;
            String imageurl = "http://image.tmdb.org/t/p/" + "w185" +"/"+ objectwithspecificposition.getString("poster_path") ;
             if (imageurl== url )
             {
                 String title = objectwithspecificposition.getString("title");
                 String releasedate = objectwithspecificposition.getString("release_date") ;
                 String vote_average = String.valueOf( objectwithspecificposition.getDouble("vote_average")) ;
               String  image = "http://image.tmdb.org/t/p/" + "w185" +"/"+ objectwithspecificposition.getString("poster_path") ;
                 String overview = objectwithspecificposition.getString("overview") ;
                 String ID = String.valueOf( objectwithspecificposition.getInt("id") );
                  movieobject  = new Movie(title,releasedate,vote_average,image,overview,ID) ;

                 return movieobject ;

             }

        }

 return movieobject ;


       /*List<Movie>  movieList = new ArrayList<Movie>()  ;
       Movie movieobject ;
        JSONObject jsonObj = new JSONObject(json) ;

        JSONArray variable = jsonObj.getJSONArray("results") ;
                for (int i=0 ; i<variable.length();i++)

        {
            JSONObject obj = variable.getJSONObject(i) ;
            String title  = obj.getString("title");
            String releasedate = obj.getString("release_date") ;
            String vote_average = obj.getString("vote_average") ;
            String overview = obj.getString("overview") ;
            String ID = obj.getString("id") ;
            String imageurl = "http://image.tmdb.org/t/p/" + "w185" +"/"+ obj.getString("poster_path") ;
            movieobject = new Movie(title,releasedate,vote_average,imageurl,overview,ID) ;

           movieList.add(movieobject) ;

        }
        return movieList ;*/
    }


    public static List<String> getTrailersUrl (String json)
            throws JSONException
    {

        List<String>  trailerResuls = new ArrayList<String>() ;

        JSONObject jsonObj = new JSONObject(json) ;

        //JSONObject variable = jsonObject.getJSONObject("results") ;
        JSONArray variable = jsonObj.getJSONArray("results") ;
        trailerResuls.clear();
        for (int i=0 ; i<variable.length();i++)

        {
            JSONObject obj = variable.getJSONObject(i) ;
            String trailerUrl = "https://www.youtube.com/watch?v=" + obj.getString("key") ;
            trailerResuls.add(trailerUrl) ;

        }
        return trailerResuls ;
    }

    // get reviews from json string
    public static List<String> getreviews (String json)
            throws JSONException
    {

        List<String>  trailerResuls = new ArrayList<String>() ;

        JSONObject jsonObj = new JSONObject(json) ;

        //JSONObject variable = jsonObject.getJSONObject("results") ;
        JSONArray variable = jsonObj.getJSONArray("results") ;
        trailerResuls.clear();
        for (int i=0 ; i<variable.length();i++)

        {
            JSONObject obj = variable.getJSONObject(i) ;
            String trailerUrl = obj.getString("content") ;
            trailerResuls.add(trailerUrl) ;

        }
        return trailerResuls ;
    }

}

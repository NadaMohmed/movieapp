package com.example.ecommerce.popularmovies;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.AsyncTask;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONException;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private FavoritesViewModel favoritesViewModel;
    List<String> getpathofimageurlcopy ;
    final static String baseurl = "http://image.tmdb.org/t/p/" ;
    final static String sizeofimage = "w185" ;
    String  jsonresultstring  ;
    TextView text ;
    URL resulturl ;
    List<String> imageresuls ;
    List<String> getpathofimageurl ;
    RecyclerView rv ;
    RecyclerView recyclerView ;
    ImageGridAdapter iga ;
    MovieAdapter ig ;
    // object to get json String
    JsonUtils jsonstring ;
    TextView errormessage ;
    AppDatabase mDb ;
     List<String> tasks ;
List <Movie> listofmovie ;
    Parcelable mListState ;
    GridLayoutManager sglm ;
    int options ;
    int number ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ////////////////////////
        favoritesViewModel = ViewModelProviders.of(this).get(FavoritesViewModel.class);
        // get instance from database
        mDb = AppDatabase.getInstance(getApplication()) ;
        jsonstring =  new JsonUtils() ;
listofmovie = new ArrayList<Movie>() ;
     //errormessage= (TextView)findViewById(R.id.errormeassage) ;
        imageresuls = new ArrayList<String>() ;
        getpathofimageurl = new ArrayList<String>() ;
        text = (TextView)findViewById(R.id.text) ;
        // making grid view
        rv = findViewById(R.id.rv);
recyclerView= findViewById(R.id.rv);
      //  resulturl = Networkutils.buildUrl() ;
        //new GithubQueryTask().execute(resulturl);
        /////////////////////// fro live data
        sglm = new GridLayoutManager(MainActivity.this,2);
        rv.setLayoutManager(sglm);


        // check for bundle instance

        if (savedInstanceState != null)
        {
            if (savedInstanceState.containsKey("number"))
            {
                number = savedInstanceState.getInt("number") ;
                if (number == 1)
                {
                    resulturl = Networkutils.buildUrl() ;
                    new GithubQueryTask().execute(resulturl);

                }
                else if (number ==2 )
                {
                    resulturl = Networkutils.buildUrlfortoprated();

                    new GithubQueryTask().execute(resulturl);
                }
                else if (number == 3)
                {
                    favoritesViewModel.getAllFavorites().observe(this, new Observer<List<Movie>>() {

                        @Override
                        public void onChanged(@Nullable List<Movie> list) {

                            for (int i =0 ; i<list.size() ; i++)
                            {

                                Log.i("yarab" ,list.get(i).getImageurl()) ;

                            }
                            //GridLayoutManager ay7aga = new GridLayoutManager(MainActivity.this, 2);
                            //rv.setLayoutManager(ay7aga);
                            //  List<Movie> hi = new ArrayList<Movie>(list) ;

                            ig = new MovieAdapter(MainActivity.this, list);
                            //List<Movie> li = new ArrayList<>(list) ;
                            ig.setFavs(list);
                            rv.setAdapter(ig);
                        }
                    });
                }

/*                else
                {
                    resulturl = Networkutils.buildUrl() ;
                    new GithubQueryTask().execute(resulturl);

                }
*/
            }
        }

     /*   if (options!=1 && options!=2 && options!=3 )
        {
            resulturl = Networkutils.buildUrl() ;
            new GithubQueryTask().execute(resulturl);

        }*/


        /*try {
            imageresuls = JsonUtils.getimageurl(jsonresultstring) ;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        for (int i =0; i <imageresuls.size();i++)
        {

            text.append(imageresuls.get(i).toString());

        }*/


       // resulturl = Networkutils.buildUrl("popularity.desc") ;

        //String url = resulturl.toString() ;
        //new GithubQueryTask().execute(resulturl);

    }

  /* public void getjsonresultinstring ()
                     {
                         URL resulturl = Networkutils.buildUrl("popularity.desc") ;
                         new GithubQueryTask().execute(resulturl);


                      }*/

    public class GithubQueryTask extends AsyncTask<URL, Void, String> {

        // COMPLETED (2) Override the doInBackground method to perform the query. Return the results. (Hint: You've already written the code to perform the query)
        @Override
        protected String doInBackground(URL... params) {
            URL searchUrl = params[0];
            String githubSearchResults = null;
            try {
                githubSearchResults = Networkutils.getResponseFromHttpUrl(searchUrl);
               // Log.i("msg",githubSearchResults) ;
            } catch (IOException e) {
                e.printStackTrace();
                //Log.i("error",e.getMessage());
            }
            return githubSearchResults;
        }


        // COMPLETED (3) Override onPostExecute to display the results in the TextView
        @Override
        protected void onPostExecute(String githubSearchResults) {
            if (githubSearchResults != null && !githubSearchResults.equals("")) {
                jsonresultstring = githubSearchResults ;
                // bageb object el json 34an ab3ato lel function
                jsonstring.setTypeofmovie(githubSearchResults);

                // hanade hena 3la el function b3d ma postexecute tero7 w terga3 be el string malyan
                try {
                    imageresuls = JsonUtils.getimageurl(jsonresultstring) ;
                    listofmovie = JsonUtils.getmovieobjects(jsonresultstring) ;
                    for (int i=0 ;i < listofmovie.size();i++)
                    { Log.i("listofmovie",listofmovie.get(i).getImageurl());}

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                // bnade function ely betgebly rigth path lel image like http://image.tmdb.org/t/p/w185//nBNZadXqJSdt05SHLqgT0HuC5Gm.jpg
                getrightpathofimage(imageresuls);


                //List<String> getpathofimageurlcopy = new ArrayList<String>(getpathofimageurl) ;
                 getpathofimageurlcopy = new ArrayList<String>(getpathofimageurl) ;
                // bnade 3la adapter 34an ygrbly data y7otaha fe grid view
                // iga = new ImageGridAdapter(MainActivity.this, getpathofimageurl);
                //GridLayoutManager sglm = new GridLayoutManager(MainActivity.this,2);
                //rv.setLayoutManager(sglm);
                   ig = new MovieAdapter(MainActivity.this,listofmovie) ;
                ig.notifyDataSetChanged();
                rv.setAdapter(ig);

               /* iga = new ImageGridAdapter(MainActivity.this, getpathofimageurlcopy);
                 iga.notifyDataSetChanged();
                 rv.setAdapter(iga);*/


            }

            else
            {
                Toast.makeText(MainActivity.this, "Failed to get results,please try again", Toast.LENGTH_SHORT).show();

            }

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemThatWasClickedId = item.getItemId();
        if (itemThatWasClickedId == R.id.popularmovie)
        {
            options = 1 ;
            //Toast.makeText(this, "popular movie", Toast.LENGTH_SHORT).show();
            resulturl = Networkutils.buildUrl() ;
            new GithubQueryTask().execute(resulturl);

            return true;
        }
       else  if (itemThatWasClickedId == R.id.topRatedMovie) {
            options = 2 ;
            //Toast.makeText(this, "toprated movie", Toast.LENGTH_SHORT).show();
         //   resulturl = Networkutils.buildUrl("vote_average.desc") ;
            resulturl = Networkutils.buildUrlfortoprated();

            new GithubQueryTask().execute(resulturl);

            return true;
        }
// get favorite movies from database
       else  if (itemThatWasClickedId== R.id.favouriteMovie)
        {
            options = 3 ;
            //StaggeredGridLayoutManager sglm = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
            //rv.setLayoutManager(sglm);

            Log.d("h","actively retriving tasks") ;
           // final LiveData <List<Movie>> tasks =  mDb.favoritesDao().loadallmovies() ;
            // final LiveData <List<String>> tasks =  mDb.favoritesDao().getAllImageurl() ;
            // iga = new ImageGridAdapter(MainActivity.this, LiveData <List<String>> tasks );
            //tasks.observe(this, new Observer<List<String>>()
            favoritesViewModel.getAllFavorites().observe(this, new Observer<List<Movie>>() {

                @Override
                public void onChanged(@Nullable List<Movie> list) {

              for (int i =0 ; i<list.size() ; i++)
                 {

                     Log.i("yarab" ,list.get(i).getImageurl()) ;

                 }
                    //GridLayoutManager ay7aga = new GridLayoutManager(MainActivity.this, 2);
                    //rv.setLayoutManager(ay7aga);
                    //  List<Movie> hi = new ArrayList<Movie>(list) ;

                    ig = new MovieAdapter(MainActivity.this, list);
                    //List<Movie> li = new ArrayList<>(list) ;
                    ig.setFavs(list);
                    rv.setAdapter(ig);
                }
            });

           //rv.setAdapter(ig);
        }


        else if (itemThatWasClickedId == R.id.deleteMovie)
        {
            options = 4 ;
            try {
                favoritesViewModel.deleteAllFavorites();
                Toast.makeText(this, "All Favorites Deleted", Toast.LENGTH_SHORT).show();

            } catch (Exception e) {
                Toast.makeText(this, "Error: " + e + " Trying to Delete Favorites", Toast.LENGTH_SHORT).show();
            }

        }

       // else {options=0 ;}

        return super.onOptionsItemSelected(item);
    }



    public void getrightpathofimage(List<String> imageresuls)
    {
        getpathofimageurl.clear();
        for (int i =0; i <imageresuls.size();i++)
        {
            String baseofimage ;

            baseofimage= baseurl+sizeofimage+"/"+ imageresuls.get(i).toString();
           getpathofimageurl.add(baseofimage);
           Log.i("ii",getpathofimageurl.get(i)) ;
            //   text.append(getpathofimageurl.get(i)+ "\n");
            //getpathofimageurl.add(i,baseofimage);
        }


    }

 // to save position of layoout manager not recycler view
 protected void onSaveInstanceState(Bundle state) {
     super.onSaveInstanceState(state);

     // Save list state
     mListState = sglm.onSaveInstanceState();
     number = options ;
     state.putParcelable("LIST_STATE_KEY", mListState);
     state.putInt("number",number);
 }

    protected void onRestoreInstanceState(Bundle state) {
        super.onRestoreInstanceState(state);

        // Retrieve list state and list/item positions
        if(state != null)
        {
            mListState = state.getParcelable("LIST_STATE_KEY");
            number = state.getInt("number") ;
        }
    }
    @Override
    protected void onResume() {
        super.onResume();

        if (mListState != null) {
            sglm.onRestoreInstanceState(mListState);
        }
        if (number!=0)
        {
            number= options ;
    //    onRestoreInstanceState(); ;
        }
    }
 
 
}

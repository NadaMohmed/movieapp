package com.example.ecommerce.popularmovies;

import android.annotation.SuppressLint;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.view.Menu;

import com.squareup.picasso.Picasso;

import org.json.JSONException;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class DetailsActivity extends AppCompatActivity {
    public static final String BASE_IMG_URL = "https://image.tmdb.org/t/p/w500";
    TrailerAdapter adapter;
    private FavoritesViewModel mFavoritesViewModel;
    ReviewAdapter adapterforreviwes;
    RecyclerView trailer_rv;
    RecyclerView Review_rv;
    Movie movie;
    String movie_id;
    String movie_title;
    String movie_releasedate;
    String movie_voteaverage;
    String movie_imageurl;
    String movie_overview;
    // get urls of api
    URL resulturl;
    URL resulturl_reviews;
    // get file json AS string
    String jsonresultstring;
    String trailer_results;

    List<String> traileresuls;

    // reviews of selected movie in list
    List<String> reviewresuls;
    // define button
    private FloatingActionButton fab;
    private boolean mCheckResult;
    //  private Boolean mCheckResult;
    // define object from datbase
    AppDatabase mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        mFavoritesViewModel = ViewModelProviders.of(this).get(FavoritesViewModel.class);

        // get instnace from db ;
        mDb = AppDatabase.getInstance(getApplicationContext());
        fab = findViewById(R.id.fab_favorites);


        trailer_rv = findViewById(R.id.trailer_rv);
        Review_rv = findViewById(R.id.review_rv);
        ImageView img = (ImageView) findViewById(R.id.imageView);
        TextView title = (TextView) findViewById(R.id.textView);
        TextView realse_date = (TextView) findViewById(R.id.textView2);
        TextView vote_average = (TextView) findViewById(R.id.textView3);
        TextView overview = (TextView) findViewById(R.id.textView4);


        Intent intent = getIntent();
        int position = intent.getIntExtra("position", 0);
        String url = intent.getStringExtra("pos");

        JsonUtils json = new JsonUtils();
        String jsonresult = json.getTypeofmovie();
        Log.i("l", jsonresult);

        /*try {
           // movie = json.getdeails(jsonresult, position);
             movie = json.getdeailsofmovie(jsonresult, url);
        } catch (JSONException e) {
            e.printStackTrace();
        }*/
        movie = intent.getParcelableExtra("Movie");
        Log.i("hof", movie.getId());
        Picasso.get()
                .load(BASE_IMG_URL + movie.getImageurl())
                .into(img);
        title.setText(movie.getTitle());
        realse_date.setText(movie.getRelesedate());
        vote_average.setText(movie.getVote_average());
        overview.setText(movie.getOverview());
        // get data from movie object
        movie_id = movie.getId();
        movie_title = movie.getTitle();
        movie_releasedate = movie.getRelesedate();
        movie_voteaverage = movie.getVote_average();
        movie_imageurl = movie.getImageurl();
        movie_overview = movie.getOverview();

// get url of trailers of on movie
        resulturl = Networkutils.buildUrlforTrailers(movie_id);
        // get url of reviwes on selected movies
        resulturl_reviews = Networkutils.buildUrlforReviews(movie_id);

        new GithubQueryTask().execute(resulturl);
        new getreviwes().execute(resulturl_reviews);
        // define horizontall layout for trailer
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        trailer_rv.setLayoutManager(layoutManager);
        // define horizontall layout for Reviews
        LinearLayoutManager layoutManagerForReviews = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        Review_rv.setLayoutManager(layoutManagerForReviews);
        // call check to star button
        checkIfExists task = new checkIfExists();
        task.execute();

        // click on star button
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fabButtonClicked();
            }
        });
    }


    public class GithubQueryTask extends AsyncTask<URL, Void, String> {

        // COMPLETED (2) Override the doInBackground method to perform the query. Return the results. (Hint: You've already written the code to perform the query)
        @Override
        protected String doInBackground(URL... params) {
            URL searchUrl = params[0];
            String githubSearchResults = null;
            try {
                githubSearchResults = Networkutils.getResponseFromHttpUrl(searchUrl);
                Log.i("msg", githubSearchResults);
            } catch (IOException e) {
                e.printStackTrace();
                Log.i("error", e.getMessage());
            }
            return githubSearchResults;
        }

        @Override
        protected void onPostExecute(String githubSearchResults) {
            if (githubSearchResults != null && !githubSearchResults.equals("")) {
                jsonresultstring = githubSearchResults;
                //Log.i("jsonresult",jsonresultstring) ;
                // bageb object el json 34an ab3ato lel function
                // jsonstring.setTypeofmovie(githubSearchResults);

                // hanade hena 3la el function b3d ma postexecute tero7 w terga3 be el string malyan
                try {
                    traileresuls = JsonUtils.getTrailersUrl(jsonresultstring);
                    // chechking for list of videos url only

                   /* for (int i=0 ; i< traileresuls.size() ;i++)
                    {
                        Log.i("1videourl",traileresuls.get(i)) ;
                    }*/
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                adapter = new TrailerAdapter(traileresuls, getApplicationContext());
                trailer_rv.setAdapter(adapter);


            } else {

                Toast.makeText(DetailsActivity.this, "Failed to get results,please try again", Toast.LENGTH_SHORT).show();

            }
        }
    }

    // get reviews


    public class getreviwes extends AsyncTask<URL, Void, String> {

        // COMPLETED (2) Override the doInBackground method to perform the query. Return the results. (Hint: You've already written the code to perform the query)
        @Override
        protected String doInBackground(URL... params) {
            URL searchUrl = params[0];
            String githubSearchResults = null;
            try {
                githubSearchResults = Networkutils.getResponseFromHttpUrl(searchUrl);
                Log.i("msg", githubSearchResults);
            } catch (IOException e) {
                e.printStackTrace();
                Log.i("error", e.getMessage());
            }
            return githubSearchResults;
        }

        @Override
        protected void onPostExecute(String githubSearchResults) {
            if (githubSearchResults != null && !githubSearchResults.equals("")) {
                jsonresultstring = githubSearchResults;
                //Log.i("jsonresult",jsonresultstring) ;
                // bageb object el json 34an ab3ato lel function
                // jsonstring.setTypeofmovie(githubSearchResults);

                // hanade hena 3la el function b3d ma postexecute tero7 w terga3 be el string malyan
                try {
                    reviewresuls = JsonUtils.getreviews(jsonresultstring);
                    // chechking for list of videos url only

                   /* for (int i=0 ; i< traileresuls.size() ;i++)
                    {
                        Log.i("1videourl",traileresuls.get(i)) ;
                    }*/
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //adapterforreviwes = new TrailerAdapter(traileresuls,getApplicationContext()) ;
                adapterforreviwes = new ReviewAdapter(reviewresuls);
                Review_rv.setAdapter(adapterforreviwes);


            } else {

                Toast.makeText(DetailsActivity.this, "Failed to get results,please try again", Toast.LENGTH_SHORT).show();

            }
        }
    }

    public void fabButtonClicked() {

        if (mCheckResult) {

            AppExecutors.getsInstance().getDiskIO().execute(new Runnable() {
                @Override
                public void run() {
                    // mDb.favoritesDao().deletTask(movie);
                    mDb.favoritesDao().deleteThisMovie(Integer.parseInt(movie_id));

                }
            });
            fab.setImageResource(R.drawable.ic_star_empty);
            mCheckResult = false;
            Toast.makeText(this, "Deleted " + movie_title + " from Favorites", Toast.LENGTH_SHORT).show();
        } else {
            //  mFavoritesViewModel.insert(mMovieResults);
            //String title , String relesedate, String vote_average , String imageurl,String overview
            final Movie moviee = new Movie(movie_title, movie_releasedate, movie_voteaverage, movie_imageurl, movie_overview, movie_id);
            mFavoritesViewModel.insert(moviee);

            /*AppExecutors.getsInstance().getDiskIO().execute(new Runnable() {
                @Override
                public void run() {

                    mDb.favoritesDao().insertTask(moviee);
                    finish();
                }
            });*/

            fab.setImageResource(R.drawable.ic_star_yellow);
            mCheckResult = true;
            Toast.makeText(this, "Added " + movie_title + " to Favorites", Toast.LENGTH_SHORT).show();
        }
    }

    // checking and update button
    @SuppressLint("StaticFieldLeak")
    protected class checkIfExists extends AsyncTask<Integer, Integer, Boolean> {

        @Override
        protected Boolean doInBackground(Integer... integers) {
            Boolean checkResult;
            // Integer checkMovieId = mDb.favoritesDao().ifExists(mMovieResults.getId());
            Integer checkMovieId = mDb.favoritesDao().ifExists(Integer.parseInt(movie.getId()));
            checkResult = checkMovieId > 0;
            return checkResult;
        }


        @Override
        protected void onPostExecute(Boolean Result) {
            mCheckResult = Result;
            if (mCheckResult) {
                fab.setImageResource(R.drawable.ic_star_yellow);
            } else {
                fab.setImageResource(R.drawable.ic_star_empty);
            }
        }
    }

}
package com.example.tianmingxu.flickster;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.tianmingxu.flickster.adapters.MovieArrayAdapter;
import com.example.tianmingxu.flickster.models.Movie;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

import static com.example.tianmingxu.flickster.models.Movie.fromJsonArray;

//Youtube API Key: AIzaSyCjDfHS_IujZr-Dm0bALVQmmAQr245a2Lc
public class MoviewActivity extends AppCompatActivity {

    private ArrayList<Movie> movies;
    private MovieArrayAdapter movieAdapter;
    private ListView lvItmes;
    private SwipeRefreshLayout swipeContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moview);

        movies = new ArrayList<Movie>();
        lvItmes = (ListView) findViewById(R.id.lvMovies);
        movieAdapter = new MovieArrayAdapter(this, movies);
        lvItmes.setAdapter(movieAdapter);
        lvItmes.setOnItemClickListener(new ListView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Movie movie = (Movie) lvItmes.getItemAtPosition(i);
                if (movie.getScore() < 5.0) {
                    launchMovieDetail(movie);
                }
                else {
                    launchMovieTrailer(movie);
                }
            }
        });
        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                fetchMoviesAsync(0);
            }
        });
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        fetchMoviesAsync(0);
    }
    private void launchMovieDetail(Movie movie) {
        // first parameter is the context, second is the class of the activity to launch
        Intent i = new Intent(MoviewActivity.this, DetailActivity.class);
        // put "extras" into the bundle for access in the second activity
        i.putExtra("movie", movie);
        // brings up the second activity
        startActivity(i);
    }

    private void launchMovieTrailer(Movie movie){
        // first parameter is the context, second is the class of the activity to launch
        Intent i = new Intent(MoviewActivity.this, playbackActivity.class);
        // put "extras" into the bundle for access in the second activity
        i.putExtra("movie", movie);
        // brings up the second activity
        startActivity(i);
    }
    public void fetchMoviesAsync(int page){
        String url = "https://api.themoviedb.org/3/movie/now_playing?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed";
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(url, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, final JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                final ArrayList<Movie> moviesFromResponse = new ArrayList<Movie>();
                try {
                    moviesFromResponse.addAll(Movie.fromJsonArray(response.getJSONArray("results")));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            movies.clear();
                            movies.addAll(moviesFromResponse);
                            movieAdapter.notifyDataSetChanged();
                            swipeContainer.setRefreshing(false);
                        }
                        catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                });
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
            }
        });
    }
}

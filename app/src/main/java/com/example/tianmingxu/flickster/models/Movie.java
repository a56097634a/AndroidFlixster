package com.example.tianmingxu.flickster.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by tianmingxu on 1/23/17.
 */

public class Movie implements Serializable {
    private  String posterPath;
    private  String originalTitle;
    private  String overView;
    private  String backdropPath;
    private  String releaseDate;
    private  double score;

    public Movie(JSONObject jsonObject) throws JSONException {
        this.posterPath = jsonObject.getString("poster_path");
        this.originalTitle = jsonObject.getString("original_title");
        this.overView = jsonObject.getString("overview");
        this.backdropPath = jsonObject.getString("backdrop_path");
        this.score = jsonObject.getDouble("vote_average");
        this.releaseDate = jsonObject.getString("release_date");
    }

    public static ArrayList<Movie> fromJsonArray(JSONArray array) {
        ArrayList<Movie> results = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {
            try {
                results.add(new Movie(array.getJSONObject(i)));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return  results;
    }

    public String getPosterPath() {
        return "https://image.tmdb.org/t/p/w342/" + posterPath;
    }

    public  String getBackdropPath(){
        return "https://image.tmdb.org/t/p/w342/" + backdropPath;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getOverView() {
        return overView;
    }

    public double getScore() {return  score;}

    public String getReleaseDate(){return releaseDate;}
}

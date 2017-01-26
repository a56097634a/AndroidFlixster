package com.example.tianmingxu.flickster;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.RatingBar;
import android.widget.TextView;
import com.example.tianmingxu.flickster.models.Movie;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import com.google.android.youtube.player.YouTubePlayer.PlaybackEventListener;

import static java.security.AccessController.getContext;

/**
 * Created by tianmingxu on 1/24/17.
 */

//Youtube API Key: AIzaSyCjDfHS_IujZr-Dm0bALVQmmAQr245a2Lc
public class DetailActivity extends YouTubeBaseActivity {
    private YouTubePlayer yPlayer;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Movie movie = (Movie) getIntent().getSerializableExtra("movie");

        setContentView(R.layout.activity_detail);
        final YouTubePlayerView youTubePlayerView = (YouTubePlayerView) findViewById(R.id.player);
        youTubePlayerView.initialize("AIzaSyCjDfHS_IujZr-Dm0bALVQmmAQr245a2Lc", new YouTubePlayer.OnInitializedListener(){
            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
            }

            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.cueVideo("0WWzgGyAH6Y");
                youTubePlayer.setPlaybackEventListener(playbackEventListener);
                yPlayer = youTubePlayer;
            }
            private PlaybackEventListener playbackEventListener = new PlaybackEventListener() {

                @Override
                public void onBuffering(boolean arg0) {
                }

                @Override
                public void onPaused() {
                    yPlayer.setFullscreen(false);
                }

                @Override
                public void onPlaying() {
                    yPlayer.setFullscreen(true);
                }

                @Override
                public void onSeekTo(int arg0) {
                }

                @Override
                public void onStopped() {
                }

            };
        });


        TextView titleView = (TextView) findViewById(R.id.title);
        titleView.setText(movie.getOriginalTitle());

        TextView releaseDateView = (TextView) findViewById(R.id.releaseDate);
        releaseDateView.setText("Release Date" + movie.getReleaseDate());

        RatingBar ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        ratingBar.setRating((float)(movie.getScore() / 2.0));

        TextView overview = (TextView) findViewById(R.id.overview);
        overview.setText(movie.getOverView());


    }



}

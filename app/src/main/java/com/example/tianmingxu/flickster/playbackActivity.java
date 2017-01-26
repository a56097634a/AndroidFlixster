package com.example.tianmingxu.flickster;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class playbackActivity extends YouTubeBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playback);
        final YouTubePlayerView youTubePlayerView = (YouTubePlayerView) findViewById(R.id.player2);
        youTubePlayerView.initialize("AIzaSyCjDfHS_IujZr-Dm0bALVQmmAQr245a2Lc", new YouTubePlayer.OnInitializedListener(){
            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
            }

            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.setFullscreen(true);
                youTubePlayer.loadVideo("6as8ahAr1Uc");
            }
        });
    }
}

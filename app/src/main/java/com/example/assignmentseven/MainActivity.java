package com.example.assignmentseven;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ProcessLifecycleOwner;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;
import android.os.PowerManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements LifecycleObserver {
    MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Monitors whether app is in back or foreground test
        ProcessLifecycleOwner.get().getLifecycle().addObserver(this);

        player = MediaPlayer.create(getApplicationContext(), R.raw.song);
        player.setLooping(true);
        player.start();
    }

    protected void onResume() {

        // Hide actionbar
        getSupportActionBar().hide();

        // Enable fullscreen
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

        super.onResume();
    }


    protected void onDestroy() {
        super.onDestroy();

        // Remove music player
        player.stop();
        player.release();
    }


    // Click event for the start button
    public void onclickPlayButton(View view) {
        Intent i = new Intent(this, GameActivity.class);
        startActivity(i);
    }

    // Click event for the highscore button
    public void onClickHighScoreButton(View view) {
        Intent i = new Intent(this, HighScoreActivity.class);
        startActivity(i);
    }

    // Event for app being put in background
    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onAppBackgrounded() {
        player.pause();
    }

    // Event for app being put in foreground
    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onAppForegrounded() {
        player.start();
    }
}
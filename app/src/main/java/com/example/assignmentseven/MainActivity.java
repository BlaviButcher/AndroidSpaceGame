package com.example.assignmentseven;

import androidx.appcompat.app.AppCompatActivity;

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

public class MainActivity extends AppCompatActivity {
    MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Bind music service
        doBindService();
        Intent music = new Intent();
        music.setClass(this, MusicService.class);
        startService(music);


    }

    protected void onResume() {
        super.onResume();

        // Hide actionbar
        getSupportActionBar().hide();

        // Enable fullscreen
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

        // Resume music
        if (mServ != null) {
            mServ.resumeMusic();
        }


    }

    protected void onPause() {
        super.onPause();
        mServ.pauseMusic();

    }

    protected void onDestroy() {
        super.onDestroy();

        // Unbind music
        doUnbindService();
        Intent music = new Intent();
        music.setClass(this, MusicService.class);
        stopService(music);
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


    // Music Stuff

    private boolean mIsBound = false;
    private MusicService mServ;
    private ServiceConnection Scon = new ServiceConnection() {

        public void onServiceConnected(ComponentName name, IBinder
                binder) {
            mServ = ((MusicService.ServiceBinder) binder).getService();
        }

        public void onServiceDisconnected(ComponentName name) {
            mServ = null;
        }
    };

    void doBindService() {
        bindService(new Intent(this, MusicService.class),
                Scon, Context.BIND_AUTO_CREATE);
        mIsBound = true;
    }

    void doUnbindService() {
        if (mIsBound) {
            unbindService(Scon);
            mIsBound = false;
        }
    }

}
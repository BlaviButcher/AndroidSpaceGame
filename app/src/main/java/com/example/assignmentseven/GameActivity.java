package com.example.assignmentseven;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.IBinder;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import com.example.assignmentseven.GameClasses.Asteroid;

import com.example.assignmentseven.GameClasses.Asteroid;
import com.example.assignmentseven.GameClasses.GraphicsView;

import static com.example.assignmentseven.GameClasses.GraphicsView.Classes.Asteroid;
import static java.security.AccessController.getContext;

public class GameActivity extends AppCompatActivity {

    GraphicsView graphicsView;
    /* Create broadcast receiver so GameActivity can be closed from another activity ie
       game over popup */
    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context arg0, Intent intent) {
            String action = intent.getAction();
            if (action.equals("finish_activity")) {
                finish();
            }
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gamescreen);

        // Hide actionbar
        getSupportActionBar().hide();

        // Enable fullscreen
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

        // Create graphics view and add to constraintLayout
        graphicsView = new GraphicsView(this);
        ConstraintLayout constraintLayout = (ConstraintLayout) findViewById(R.id.cl_gamescreen);
        constraintLayout.addView(graphicsView);

        // register broadcast receiver to GameActivity
        registerReceiver(broadcastReceiver, new IntentFilter("finish_activity"));



        doBindService();
        Intent music = new Intent();
        music.setClass(this, MusicService.class);
        startService(music);
    }

    protected void onResume()
    {
        super.onResume();

        // Hide actionbar
        getSupportActionBar().hide();

        
        // Enable fullscreen
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

        if (mServ != null) {
            mServ.resumeMusic();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        graphicsView.spaceship.bmAsteroid.recycle();
        graphicsView.planet.bitmap.recycle();

        for (Asteroid a : graphicsView.asteroids)
            a.bmAsteroid.recycle();

        doUnbindService();
        Intent music = new Intent();
        music.setClass(this,MusicService.class);
        stopService(music);
    }

    @Override
    protected void onPause() {
        super.onPause();

        mServ.pauseMusic();

    }


    private boolean mIsBound = false;
    private MusicService mServ;
    private ServiceConnection Scon =new ServiceConnection(){

        public void onServiceConnected(ComponentName name, IBinder
                binder) {
            mServ = ((MusicService.ServiceBinder)binder).getService();
        }

        public void onServiceDisconnected(ComponentName name) {
            mServ = null;
        }
    };

    void doBindService(){
        bindService(new Intent(this,MusicService.class),
                Scon,Context.BIND_AUTO_CREATE);
        mIsBound = true;
    }

    void doUnbindService()
    {
        if(mIsBound)
        {
            unbindService(Scon);
            mIsBound = false;
        }
    }


}

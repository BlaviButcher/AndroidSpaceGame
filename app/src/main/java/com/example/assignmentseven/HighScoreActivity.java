package com.example.assignmentseven;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


import java.lang.reflect.Array;
import java.util.Arrays;


public class HighScoreActivity extends AppCompatActivity
{
    // Constants for storing the high scores
    private static String SHARED_PREFERENCES_LOCATION = "high_scores";
    private static String SHARED_PREFERENCES_PREFIX = "score";
    public static int NUM_SCORES = 5;

    // Returns the stored high scores
    public static int[] getHighScores(Context context){
        SharedPreferences preferences = context.getSharedPreferences(SHARED_PREFERENCES_LOCATION, MODE_PRIVATE);
        int[] scores  = new int[NUM_SCORES];
        for (int i = 0; i < NUM_SCORES; i++)
            scores[i] = preferences.getInt(SHARED_PREFERENCES_PREFIX + Integer.toString(i),0);
        return scores;
    }

    // Sets the high scores
    public static void setHighScores(int[] scores, Context context){
        // Do nothing if used incorrectly
        if (scores.length != NUM_SCORES)
            return;

        // Save scores
        SharedPreferences.Editor preferences = context.getSharedPreferences(SHARED_PREFERENCES_LOCATION, MODE_PRIVATE).edit();
        for (int i = 0; i < NUM_SCORES; i++)
            preferences.putInt(SHARED_PREFERENCES_PREFIX + Integer.toString(i), scores[i]);
        preferences.apply();
    }



    // Lifecycle methods

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore);

        // Hide actionbar
        getSupportActionBar().hide();

        // Enable fullscreen
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

        // Get high scores
        int[] scores = getHighScores(getApplicationContext());
        String[] formattedScores = new String[scores.length];
        for (int i = 0; i < formattedScores.length; i++)
            formattedScores[i] = "" + scores[i];

        // Set into list view
        CustomAdaptor arrayAdapter = new CustomAdaptor(this, R.layout.highscore_adaptor, formattedScores);
        ListView highScoreListView = (ListView) findViewById(R.id.lv_highscores);
        highScoreListView.setAdapter(arrayAdapter);
    }


    public class CustomAdaptor extends ArrayAdapter<String> {


        int resource;
        private Context context;

        public CustomAdaptor(Context context, int resID, String[] items) {
            super(context,resID, items);
            this.resource = resID;
            this.context = context;
        }



        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(resource, parent, false);

            TextView item = (TextView) convertView.findViewById(R.id.tv_1);


            item.setText(getItem(position));

            item.setPadding(0, 90, 0, 90);

            return convertView;
        }
    }


    protected void onResume()
    {
        super.onResume();

        // NOTE: On some displays it bugs out with a white bar
        // this fixes that issue
        // Hide actionbar
        getSupportActionBar().hide();
        // Enable fullscreen
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
    }


    public void onClickHighScoreBackButton(View view)
    {
        finish();
    }
}

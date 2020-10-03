package com.example.assignmentseven;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.lang.reflect.Array;
import java.util.Arrays;

public class HighScoreActivity extends AppCompatActivity
{
    // Constants for storing the high scores
    private static String SHARED_PREFERENCES_LOCATION = "high_scores";
    private static String SHARED_PREFERENCES_PREFIX = "score";
    private static int NUM_SCORES = 5;

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


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore);

        // Hide actionbar
        getSupportActionBar().hide();

        // Enable fullscreen
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

        int[] highscoresArray = getHighScores(getApplicationContext());

        String[] highscoreStringArray = new String[highscoresArray.length];
        for (int i = 0; i < highscoreStringArray.length; i++)
        {
            highscoreStringArray[i] = "" + highscoresArray[i];
        }

        Log.d("blavi", 1 + highscoreStringArray[0]);
        Log.d("blavi", 2 + highscoreStringArray[1]);
        Log.d("blavi", 3 + highscoreStringArray[2]);
        Log.d("blavi", 4 + highscoreStringArray[3]);
        Log.d("blavi", 5 + highscoreStringArray[4]);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, highscoreStringArray);
        ListView highScoreListView = (ListView) findViewById(R.id.lv_highscores);
        highScoreListView.setAdapter(arrayAdapter);
    }

    protected void onResume()
    {
        super.onResume();

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

package com.example.assignmentseven;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.TextView;

public class GameOver extends Activity {

    private int score;
    private int[] scores;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_game_over);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width * 0.8), (int) (height * 0.6));
        score = getIntent().getIntExtra("SCORE", -1);
        scores = getIntent().getIntArrayExtra("SCORES_ARRAY");

        TextView textViewScore = (TextView) findViewById(R.id.tv_game_over_score);
        textViewScore.setText("" + score);

        TextView textViewScoreLabel = (TextView) findViewById(R.id.tv_score_label);
        textViewScoreLabel.setText("Your Score: ");



        if (scores.length < 5)
        {
            newHighScore();
        }

        for (int highScore : scores)
        {
            if (score > highScore)
            {
                newHighScore();
                break;
            }
        }
    }

    protected void onResume()
    {
        super.onResume();

        // Enable fullscreen
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

    }

    public void onClickMenuButton(View view) {

        // Tell GameActivity to finish
        Intent intent = new Intent("finish_activity");
        sendBroadcast(intent);
        finish();
    }

    public void onClickRetryButton(View view) {
        // Tell GameActivity to finish
        Intent intent = new Intent("finish_activity");
        sendBroadcast(intent);
        finish();

        // Create new Game Activity
        Intent i = new Intent(this, GameActivity.class);
        // Makes the transition not to this weird bouncy thing
        i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(i);


    }

    public void newHighScore()
    {
        TextView textViewScoreLabel = (TextView) findViewById(R.id.tv_new_high_score_label);
        textViewScoreLabel.setText("New High Score!!!");
    }
}
package com.example.assignmentseven;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    // Click event for the start button
    public void onclickPlayButton(View view) {
        Intent i = new Intent(this, GameActivity.class);
        startActivity(i);
    }

    // Click event for the highscore button
    public void onClickHighScoreButton(View view)
    {
        Intent i = new Intent(this, HighScoreActivity.class);
        startActivity(i);
    }
}
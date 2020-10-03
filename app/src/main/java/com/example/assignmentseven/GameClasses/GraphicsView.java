package com.example.assignmentseven.GameClasses;


import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import com.example.assignmentseven.HighScoreActivity;
import com.example.assignmentseven.R;

import java.util.Random;
// TODO: Get laser spawning from ships coords
// TODO: Make laser draw behind ship

// Graphics view class
public class GraphicsView extends View {

    // Dimensions of activity bounds
    public int width, height;
    Random rand;

    // Gesture detector
    private GestureDetector gestureDetector;

    // This is the scaler that we will reduce the fling variables by
    private static float flingDampener = 500;

    Paint paintText;


    // Only allow one laser to be active at a time - so have a single variable instead of array
    // If laser is null will not draw anything
    private Laser laser = null;

    private Asteroid[] asteroids;
    private Planet planet;
    private Spaceship spaceship;

    public int score = 0;
    public int lives = 3;

    private int numAsteroids = 3;



    public GraphicsView(Context context) {
        super(context);
        rand = new Random();

        paintText = new Paint();
        paintText.setColor(getResources().getColor(R.color.colorFloralWhite));
        paintText.setTextSize(60);

        // Get initial width and height - just using screen size..
        width = context.getResources().getDisplayMetrics().widthPixels;
        height = context.getResources().getDisplayMetrics().heightPixels;

        // Add gesture listener
        gestureDetector = new GestureDetector(context, new GestureListener());


        // Create asteroids
        asteroids = new Asteroid[numAsteroids];
        for (int i = 0; i < numAsteroids; i ++)
        {
            asteroids[i] = new Asteroid(this, (float) (height * 0.02));
        }

        planet = new Planet(this, (float) (height * 0.07));
        spaceship = new Spaceship(this, (float) (height * 0.02));


    }


    // newLaser will launch a laser to the screen
    protected void newLaser(float dx, float dy){
        // Don't add a new laser if one already exists
        if (laser != null)
            return;

        laser = new Laser(this);
        laser.setVelocity(dx,dy);
    }


    // Main method for rendering the objects to our GraphicsView
    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);



        // TODO: break into individual methods
        drawText(canvas);


        if (laser != null){
            laser.move();
            laser.draw(canvas);
            if (laser.outOfBounds()) // Check the laser is still in bounds
            {
                laser = null;
                lives--;
            }
        }

        for (Asteroid asteroid : asteroids)
        {
            asteroid.move();
            asteroid.draw(canvas);
            if (asteroid.outOfBounds())
            {
                asteroid.respawn();
            }
            if (asteroid.collidesWith(laser))
            {
                asteroid.respawn();
                laser = null;
                lives--;
            }
        }

        if (planet.collidesWith(laser))
        {
            planet.respawn();
            laser = null;
            increaseScore(canvas);
        }

        if(lives <= 0)
        {
            gameOver();
        }

        spaceship.draw(canvas);
        planet.draw(canvas);

        invalidate();
    }

    private void drawText(Canvas canvas)
    {
        canvas.drawText("Score: " + score, 30, this.height - 30, paintText);
        canvas.drawText("Lives: " + lives, width - 230, this.height - 30, paintText);
    }
    private void increaseScore(Canvas canvas)
    {
       score++;
    }

    private void gameOver()
    {
        Log.d("blavi", "here");
        // Read in high scores
        int[] scores = HighScoreActivity.getHighScores(getContext());
        if (score > scores[scores.length-1]){
            // Get index where score should be
            int i;
            for (i = scores.length - 2; i >= 0; i--){
                if (score <= scores[i])
                    break;
            }
            Log.d("blavi", "" + i);
            if (i < 0) i = 0;
            // Shift all previous scores and set this high score
            for (int j = scores.length - 1; j > i; j--)
                scores[j] = scores[j - 1];
            scores[i] = score;
            HighScoreActivity.setHighScores(scores, this.getContext());
        }
        ((Activity)getContext()).finish();
    }


    // Tracks the size of the screen
    @Override
    protected void onSizeChanged(int w, int h, int oldW, int oldH) {
        super.onSizeChanged(w, h, oldW, oldH);
        width = w;
        height = h;
    }

    // For the gesture detection
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (gestureDetector.onTouchEvent(event))
            return true;

        return super.onTouchEvent(event);
    }

    // Gesture listener class - extends SimpleOnGestureListener that returns false for every other event
    // other than the ones we care about. We only need to worry about onFling event
    public class GestureListener extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onDown(MotionEvent e) {
            Log.i("LASER", "ondown");
            return true;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            Log.i("LASER", "onfling: " + velocityX + "  --  " + velocityY);
            newLaser(velocityX / flingDampener, velocityY/ flingDampener);
            return false;
        }
    }


}
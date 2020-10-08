package com.example.assignmentseven.GameClasses;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import com.example.assignmentseven.GameActivity;
import com.example.assignmentseven.GameOver;
import com.example.assignmentseven.GameUtils.Point;
import com.example.assignmentseven.GameUtils.Vector;
import com.example.assignmentseven.HighScoreActivity;
import com.example.assignmentseven.MainActivity;
import com.example.assignmentseven.R;

import java.util.Random;



// Graphics view class
public class GraphicsView extends View {

    // Dimensions of activity bounds
    public int width, height;
    Random rand;

    // Gesture detector
    private GestureDetector gestureDetector;

    // This is the scaler that we will reduce the fling variables by
    private static double flingDampener = 500;

    // Color of the text we render to screen
    private Paint paintText;


    // Objects in the game
    private Laser laser;
    private Asteroid[] asteroids;
    private Planet planet;
    private GasGiant gasgiant;
    private Spaceship spaceship;

    // Lists of objects sharing traits
    private Sprite[] collidables;
    private DynamicSprite[] movables;

    // Tracks state of the game
    public int score = 0;
    public int lives = 3;

    // Number of asteroids we will allow on the screen at once
    private int numAsteroids = 3;


    private boolean gameIsOver = false;



    public GraphicsView(Context context) {
        super(context);
        rand = new Random();

        // Get initial width and height - just using screen size..
        width = context.getResources().getDisplayMetrics().widthPixels;
        height = context.getResources().getDisplayMetrics().heightPixels;

        // Set text paint
        paintText = new Paint();
        paintText.setColor(getResources().getColor(R.color.color_floral_white));
        paintText.setTextSize(60);

        // Add custom gesture listener
        gestureDetector = new GestureDetector(context, new GestureListener());

        // Initilise and populate lists
        collidables = new Sprite[numAsteroids + 4];
        movables = new DynamicSprite[numAsteroids + 1];

        // Create asteroids
        asteroids = new Asteroid[numAsteroids];
        for (int i = 0; i < numAsteroids; i ++)
        {
            asteroids[i] = new Asteroid(this, (int)(height * 0.02));
            collidables[i] = asteroids[i];
            movables[i] = asteroids[i];
        }

        laser = new Laser(this);
        collidables[numAsteroids] = laser;
        movables[numAsteroids] = laser;

        gasgiant = new GasGiant(this,(int)(height * 0.05), (int)(height * 0.1));
        collidables[numAsteroids + 1] = gasgiant;

        planet = new Planet(this, (int)(height * 0.07));
        collidables[numAsteroids  + 2] = planet;

        spaceship = new Spaceship(this, (int) (height * 0.02));
        collidables[numAsteroids + 3] = spaceship;
    }



    // Main method for rendering the objects to our GraphicsView
    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // Move everything
        for (DynamicSprite s : movables)
            s.move();


        // Check collisions
        for (int i = 0; i < collidables.length; i++ )
            for (int j = i + 1; j < collidables.length; j++ )
                if(collidables[i].collidesWith(collidables[j]))
                    handleCollision(collidables[i], collidables[j]);

        // Draw objects
        for (Sprite s : collidables)
            s.draw(canvas);

        drawText(canvas);




        if(lives <= 0 && !gameIsOver)
        {
            gameIsOver = true;
            /* Because the draw method is called so quickly ~.0500 milliseconds apart
            This would be called again before the activity is properly closed
            This caused the 5 highscores to always be the same */
            gameOver();
        }

        invalidate();
    }



    // Enum coding a class to a value
    public enum Classes{
        Laser, Asteroid, GasGiant, Planet, Spaceship
    }

    // Handles a collision between two objects
    private void handleCollision(Sprite a, Sprite b){
        Classes typeA = Classes.valueOf(a.getClass().getSimpleName());
        Classes typeB = Classes.valueOf(b.getClass().getSimpleName());

        // Switch so always ordered
        if (typeA.ordinal() > typeB.ordinal()){
            Classes tmpC = typeA; typeA = typeB; typeB = tmpC;
            // if types are switch, then sprites are switched to reflect
            Sprite  tmpS = a; a = b; b = tmpS;
        }

        // handle every possible collision
        switch (typeA){
            case Laser:
                switch (typeB){
                    case Laser:
                        break;

                    case Asteroid:
                        ((Laser) a).hide();
                        ((Asteroid) b).respawn();
                        lives--;
                        break;

                    case GasGiant:
                        ((GasGiant) b).pull((Laser) a);
                        break;

                    case Planet:
                        ((Laser) a).hide();
                        ((Planet) b).respawn();
                        increaseScore();

                    case Spaceship:
                        break;
                }
                break;

            case Asteroid:
                switch (typeB){
                    case Asteroid:
                        ((Asteroid) a).respawn();
                        ((Asteroid) b).respawn();
                        break;

                    case GasGiant:
                        ((GasGiant) b).pull((Asteroid) a);
                        break;

                    case Planet:
                        ((Asteroid) a).respawn();

                    case Spaceship:
                        ((Asteroid) a).respawn();
                        break;
                }


            case GasGiant:
                switch (typeB){
                    case GasGiant:
                        break;

                    case Planet:
                        break;

                    case Spaceship:
                        break;
                }



            case Planet:
                switch (typeB){
                 case Planet:
                        break;

                    case Spaceship:
                        break;
                }


            case Spaceship:
                switch (typeB){
                 case Spaceship:
                        break;
                }
        }
    }




    // renders the score and life count to the screen
    private void drawText(Canvas canvas)
    {
        canvas.drawText("Score: " + score, 30, this.height - 30, paintText);
        canvas.drawText("Lives: " + lives, width - 230, this.height - 30, paintText);
    }
    // increments score
    private void increaseScore()
    {
       score++;
    }




    // gameOver is called when lives <= 0. Will check if this score is a high score, if so save
    // will then render the game over screen
    private void gameOver()
    {
        // Read in high scores
        int[] scores = HighScoreActivity.getHighScores(getContext());
        if (score > scores[scores.length-1]){
            scores[scores.length-1] = score;
            for (int i = scores.length - 2; i >= 0; i--){
                if (score > scores[i]){
                    scores[i + 1] = scores[i];
                    scores[i] = score;
                } else
                    break;
            }
            HighScoreActivity.setHighScores(scores, this.getContext());
        }
        //((Activity)getContext()).finish();
        Context context = (Activity)getContext();
        Intent myIntent = new Intent(context, GameOver.class);
        myIntent.putExtra("SCORES_ARRAY", scores);
        myIntent.putExtra("SCORE", this.score);
        context.startActivity(myIntent);
    }



    // Tracks the size of the screen in case the activity changes size
    @Override
    protected void onSizeChanged(int w, int h, int oldW, int oldH) {
        super.onSizeChanged(w, h, oldW, oldH);
        width = w;
        height = h;
    }

    // For the gesture detection
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();




        //float bounds = height / 3f;
        //bounds = height - bounds;
        //Log.d("blavi", ""+ bounds);
        // Stops multispawning - check if laser is currently flyinng
        if(laser.hidden)
        {
            //if(y < bounds) return false;
            if (gestureDetector.onTouchEvent(event))
                return true;
            //return super.onTouchEvent(event);
        }
        return false;
    }

    // Gesture listener class - extends SimpleOnGestureListener that returns false for every other event
    // other than the ones we care about. We only need to worry about onFling event
    public class GestureListener extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onDown(MotionEvent e) { return true; }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            laser.shoot(new Vector(velocityX, velocityY), spaceship.pos);
            return false;
        }
    }


    // Helper functions
    public double randDoubleInRange(double min, double max){ return rand.nextDouble() * (max - min) + min; }
    public int randIntInRange(int min, int max){
        return rand.nextInt(max - min) + min;
    }

}
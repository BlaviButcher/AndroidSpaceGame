package com.example.assignmentseven.GameClasses;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

import androidx.core.content.ContextCompat;

import com.example.assignmentseven.R;

import java.util.Random;

// Asteroid is an obstacle in the game
class Asteroid extends DynamicSprite {

    public Paint paint = new Paint();

    // Dividends used when deciding bounds
    final double UPPER_DIVIDEND = 0.25;
    final double LOWER_DIVIDEND = 0.75;

    final int MAX_VELOCITY = 10;
    final int MIN_VELOCITY = 5;

    // which side of screen object spawned
    private boolean spawnedLeft;

    // Constructors
    public Asteroid(GraphicsView screen, float radius){
        super(screen, 0, 0, radius);
        this.paint.setColor(getColor(R.color.colorMiddleRed));
        respawn(screen);
    }
    public Asteroid(GraphicsView screen, float radius, int colorId){
        super(screen, 0, 0, radius);
        this.paint.setColor(getColor(colorId));
        respawn(screen);
    }


    // draws to the screen
    public void draw(Canvas canvas){ canvas.drawCircle(x,y, radius, paint); }

    public void respawn(GraphicsView screen)
    {
        // TODO: Maybe break this method up
        Random rand = new Random();
        // Create a 50/50 of spawning left or right of screen

        spawnedLeft = rand.nextBoolean();
        // Set variables according to which side spawn
        if (spawnedLeft)
        {
            // set spawn point
            x = -radius;
            // set random velocity
            int rand_dx = rand.nextInt(MAX_VELOCITY - MIN_VELOCITY) + MIN_VELOCITY;
            this.setVelocity(rand_dx, 0);
        }
        else
        {
            // Set spawn point
            x = screen.width + radius;

            // Set random velocity within bounds
            int rand_dx = rand.nextInt(MAX_VELOCITY - MIN_VELOCITY) + MIN_VELOCITY;
            // invert
            rand_dx *= -1;
            this.setVelocity(rand_dx,0);
        }

        // Y = 0 is at the top of the screen - idk why... damn programmers
        // So by upper and lower I mean that in relation to the view of the user
        int upperBound = (int) (screen.height * UPPER_DIVIDEND);
        int lowerBound = (int) (screen.height * LOWER_DIVIDEND);

        // Get random spawn location between bounds
        y = rand.nextInt(lowerBound - upperBound) + upperBound;

    }

    // Check whether object is out of bounds depending on which side it spawned ie velocity
    public boolean outOfBounds(GraphicsView screen)
    {
        if (spawnedLeft)
        {
            return x > screen.width + radius;
        }

        return x < -radius;

    }




}
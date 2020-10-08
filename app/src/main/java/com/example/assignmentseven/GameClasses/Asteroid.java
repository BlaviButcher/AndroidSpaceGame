package com.example.assignmentseven.GameClasses;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.media.MediaMetadataRetriever;

import com.example.assignmentseven.R;




// Asteroid is an obstacle in the game
class Asteroid extends DynamicSprite {

    public Paint paint = new Paint();

    // Dividends used when deciding bounds
    private final double UPPER_DIVIDEND = 0.25;
    private final double LOWER_DIVIDEND = 0.75;

    // Min and max velocity for an asteroid
    private final double MAX_VELOCITY = 8;
    private final double MIN_VELOCITY = 4;

    private final double HALF = 0.5;



    // which side of screen object spawned
    private boolean spawnedLeft;


    // Constructors
    public Asteroid(GraphicsView screen, int radius){
        super(screen, 0, 0, radius);
        this.paint.setColor(getColor(R.color.color_middle_red));
        respawn();
    }
    public Asteroid(GraphicsView screen, int radius, int colorId){
        super(screen, 0, 0, radius);
        respawn();
    }


    // draws to the screen
    public void draw(Canvas canvas){
        canvas.drawCircle(pos.x,pos.y, radius, paint);

    }


    // override move method
    @Override
    public void move() {
        if (outOfBounds())
            respawn();
        else
            super.move();
    }

    // respawns the asteroid
    public void respawn()
    {
        // Decided if the asteroid spawns on the left or right
        spawnedLeft = screen.rand.nextBoolean();
        double xdir = 1;
        if (spawnedLeft) pos.x = -radius;
        else {
            pos.x = screen.width + radius;
            xdir = -1;
        }

        // Set random velocity
        velocity.x = screen.rand.nextDouble() * 2 + 1;
        velocity.y = screen.rand.nextDouble() - HALF;
        velocity.unit().scale(screen.rand.nextDouble() * (MAX_VELOCITY - MIN_VELOCITY) + MIN_VELOCITY);
        velocity.x *= xdir; // Set x velocity by the side it spawns

        // Get random spawn location between bounds
        pos.y = (int) screen.randDoubleInRange(screen.height * LOWER_DIVIDEND, screen.height * UPPER_DIVIDEND);
    }


    // Check whether object is out of bounds depending on which side it spawned ie velocity
    public boolean outOfBounds()
    {
        if (pos.y < -radius) return true;
        else if (pos.y > screen.height + radius) return true;
        else if (spawnedLeft && pos.x > screen.width + radius) return true;
        return pos.x < -radius;
    }




}
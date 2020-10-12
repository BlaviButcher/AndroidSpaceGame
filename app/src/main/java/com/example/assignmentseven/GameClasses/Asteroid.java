package com.example.assignmentseven.GameClasses;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.MediaMetadataRetriever;
import android.util.Log;

import androidx.constraintlayout.solver.widgets.Rectangle;

import com.example.assignmentseven.GameUtils.Point;
import com.example.assignmentseven.R;




// Asteroid is an obstacle in the game
public class Asteroid extends DynamicSprite {
    // Set the state for the asteroid
    private enum State{
        Moving, Exploding
    }
    private State state;

    // Dividends used when deciding bounds
    private final double UPPER_DIVIDEND = 0.25;
    private final double LOWER_DIVIDEND = 0.75;

    // Min and max velocity for an asteroid
    private final double MAX_VELOCITY = 8;
    private final double MIN_VELOCITY = 4;

    private final double HALF = 0.5;

    public Bitmap bitmap = BitmapFactory.decodeResource(screen.getResources(), R.drawable.asteroid);

    public Bitmap explosionsBitmap = BitmapFactory.decodeResource(screen.getResources(), R.drawable.explosions);
    private static int explosionPixelSize = 64; // Size of each sprite
    private static int explosionCount = 4; // Number of sprites per row/col (it's a square bitmap)
    private int explosionX, explosionY;


    private final Rect srcRect = new Rect();
    private final Rect destRect = new Rect();


    // which side of screen object spawned
    private boolean spawnedLeft;


    // Constructors
    public Asteroid(GraphicsView screen, int radius){
        super(screen, 0, 0, radius);
        respawn();
    }


    // draws to the screen
    public void draw(Canvas canvas){
        switch (state){
            case Moving:
                destRect.set(pos.x - radius, pos.y - radius, pos.x + radius, pos.y + radius);
                canvas.drawBitmap(bitmap, srcRect, destRect, null);
                break;


            case Exploding:
                srcRect.set(explosionX * explosionPixelSize, explosionY * explosionPixelSize, (explosionX + 1) * explosionPixelSize, (explosionY + 1) * explosionPixelSize);
                destRect.set(pos.x - radius, pos.y - radius, pos.x + radius, pos.y + radius);
                canvas.drawBitmap(explosionsBitmap, srcRect, destRect, null);

                if(explosionX < explosionCount) {explosionX++;}
                else if(explosionY < explosionCount) {explosionY++; explosionX = 0;}
                else respawn();
                break;
        }
    }


    // override move method
    @Override
    public void move() {
        if (outOfBounds())
            respawn();
        else
            super.move();
    }

    // explode the asteroid
    public void explode()
    {
        velocity.x = 0; velocity.y = 0;
        state = State.Exploding;
        explosionX = 0; explosionY = 0;
    }


    // Check whether object is out of bounds depending on which side it spawned ie velocity
    public boolean outOfBounds() {
        return pos.y < -radius ||
                pos.y > screen.height + radius ||
                spawnedLeft && pos.x > screen.width + radius ||
                !spawnedLeft && pos.x < -radius;
    }

    // Set the src rect to the full asteroid bitmap
    private void respawn(){
        state = State.Moving;
        srcRect.set(0, 0, bitmap.getWidth(), bitmap.getHeight());


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
}
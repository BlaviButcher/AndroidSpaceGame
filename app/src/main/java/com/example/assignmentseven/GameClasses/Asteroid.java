package com.example.assignmentseven.GameClasses;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.MediaMetadataRetriever;
import android.util.Log;
import android.view.WindowManager;

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

    // Bitmap for drawing asteroid
    public Bitmap bitmap = BitmapFactory.decodeResource(screen.getResources(), R.drawable.asteroid);

    // Some state for tracking explosions
    private int explosionIdx;
    private static int explosionLength = 2;

    // Rectangles used for rendering bitmaps
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
                canvas.drawBitmap(screen.explosions[explosionIdx / explosionLength], srcRect, destRect, null);
                explosionIdx++;
                if (explosionIdx > (screen.explosions.length - 1) * explosionLength)
                    respawn();
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


    // need to override so objects aren't constantly colliding
    @Override
    public boolean collidesWith(Sprite other) {
        if (state == State.Exploding) return false;
        return super.collidesWith(other);
    }


    // explode the asteroid
    public void explode()
    {
        velocity.x = 0; velocity.y = 0;
        state = State.Exploding;
        srcRect.set(0,0, screen.explosions[0].getWidth(), screen.explosions[0].getHeight());
        explosionIdx = 0;
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
        velocity.y = screen.rand.nextDouble() - 0.5;
        velocity.unit().scale(screen.randDoubleInRange(MIN_VELOCITY, MAX_VELOCITY));
        velocity.x *= xdir; // Set x velocity by the side it spawns

        // Get random spawn location between bounds
        pos.y = (int) screen.randDoubleInRange(screen.height * LOWER_DIVIDEND, screen.height * UPPER_DIVIDEND);
    }


    // Check whether object is out of bounds depending on which side it spawned ie velocity
    public boolean outOfBounds() {
        return pos.y < -radius ||
                pos.y > screen.height + radius ||
                spawnedLeft && pos.x > screen.width + radius ||
                !spawnedLeft && pos.x < -radius;
    }
}
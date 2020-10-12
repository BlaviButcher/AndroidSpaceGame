package com.example.assignmentseven.GameClasses;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorSpace;
import android.graphics.Paint;
import android.graphics.Rect;

import com.example.assignmentseven.R;

// Planet is the 'target' in the game
public class Planet extends Sprite
{
    // State of the planet
    private enum State{
        Hovering, Exploding
    }
    private Planet.State state;


    // Some state for tracking explosions
    private int explosionIdx;
    private static int explosionLength = 4;

    // Bitmap variables
    public final Bitmap bitmap = BitmapFactory.decodeResource(screen.getContext().getResources(), R.drawable.planet_foreground);
    private final Rect srcRect = new Rect();
    private final Rect destRect = new Rect();


    // Need a little space between asteroids and planets - currently 5%
    private final double LOWER_DIVIDEND = 0.25;


    // Constructor
    public Planet(GraphicsView screen, int radius)
    {
        super(screen, 0, 0, radius);
        respawn();
    }


    // draw the planet to the canvas - state dependant
    @Override
    public void draw(Canvas canvas) {
        switch (state) {
            case Hovering:
                canvas.drawBitmap(bitmap, srcRect, destRect,null);
                break;

            case Exploding:
                canvas.drawBitmap(screen.explosions[explosionIdx / explosionLength], srcRect, destRect, null);
                explosionIdx++;
                if (explosionIdx > (screen.explosions.length - 1) * explosionLength)
                    respawn();

                break;
        }

    }


    // explode the planet
    public void explode(){
        state = State.Exploding;
        explosionIdx = 0;

        srcRect.set(0,0, screen.explosions[0].getWidth(), screen.explosions[0].getHeight());
        destRect.set(pos.x - radius * 2,pos.y - radius * 2, pos.x + radius * 2, pos.y + radius * 2);
    }


    // Respawn the planet
    public void respawn()
    {
        state = State.Hovering;
        int y_lowerBound = (int) (screen.height * LOWER_DIVIDEND);
        pos.x = screen.rand.nextInt(screen.width - radius * 2) + radius;
        pos.y = screen.rand.nextInt(y_lowerBound - radius * 2) + radius;

        srcRect.set(0,0, bitmap.getWidth(), bitmap.getHeight());
        destRect.set(pos.x - radius,pos.y -radius, pos.x + radius, pos.y + radius);
    }
}

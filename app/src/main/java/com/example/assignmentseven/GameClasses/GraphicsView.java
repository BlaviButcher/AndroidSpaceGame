package com.example.assignmentseven.GameClasses;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import com.example.assignmentseven.R;


// Graphics view class
public class GraphicsView extends View {

    // Dimensions of activity bounds
    public int width, height;

    // Gesture detector
    private GestureDetector gestureDetector;

    // This is the scaler that we will reduce the fling variables by
    private static float flingDampener = 500;


    // Only allow one laser to be active at a time - so have a single variable instead of array
    // If laser is null will not draw anything
    private Laser laser = null;


    private Asteroid[] asteroids;



    public GraphicsView(Context context) {
        super(context);

        // Get initial width and height - just using screen size..
        width = context.getResources().getDisplayMetrics().widthPixels;
        height = context.getResources().getDisplayMetrics().heightPixels;

        // Add gesture listener
        gestureDetector = new GestureDetector(context, new GestureListener());


        // Create asteroids
        asteroids = new Asteroid[3];
        for (int i = 0; i < 3; i ++)
        {
            asteroids[i] = new Asteroid(this, 50);
        }
    }


    // newLaser will launch a laser to the screen
    protected void newLaser(float dx, float dy){
        // Don't add a new laser if one already exists
        if (laser != null)
            return;

        Log.i("LASER", "laser added");

        laser = new Laser(this);
        laser.setVelocity(dx,dy);
    }


    // Main method for rendering the objects to our GraphicsView
    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // TODO: break into individual methods

        if (laser != null){
            laser.move();
            laser.draw(canvas);
            if (laser.outOfBounds()) // Check the laser is still in bounds
                laser = null;
        }

        for (Asteroid asteroid : asteroids)
        {
            if (asteroid != null)
            {
                asteroid.move();
                asteroid.draw(canvas);
                if (asteroid.outOfBounds(this))
                {
                    asteroid.respawn(this);
                }
            }
        }


        invalidate();
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
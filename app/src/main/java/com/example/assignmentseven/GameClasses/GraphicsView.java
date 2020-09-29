package com.example.assignmentseven.GameClasses;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import com.example.assignmentseven.R;


// Graphics view class
public class GraphicsView extends View {

    // Dimensions of activity bounds
    private int width, height;

    // Gesture detector
    private GestureDetector gestureDetector;


    public GraphicsView(Context context) {
        super(context);

        // Get initial width and height - just using screen size..
        width = context.getResources().getDisplayMetrics().widthPixels;
        height = context.getResources().getDisplayMetrics().heightPixels;

        // Add gesture listener
        gestureDetector = new GestureDetector(context, new GestureListener());
    }





    // Main method for rendering the objects to our GraphicsView
    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);



    }



    // For the gesture detection
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (gestureDetector.onTouchEvent(event))
            return true;

        return super.onTouchEvent(event);
    }

    // Tracks the size of the screen
    @Override
    protected void onSizeChanged(int w, int h, int oldW, int oldH) {
        super.onSizeChanged(w, h, oldW, oldH);
        width = w;
        height = h;
    }
}
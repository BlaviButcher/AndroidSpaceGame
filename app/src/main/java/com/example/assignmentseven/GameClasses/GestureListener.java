package com.example.assignmentseven.GameClasses;

import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

public class GestureListener extends GestureDetector.SimpleOnGestureListener {
    @Override
    public boolean onDown(MotionEvent e) {
        return true;
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        Log.i("FLING", "velocties: " +  velocityX + "  -  " + velocityY);
        return true;
    }
}

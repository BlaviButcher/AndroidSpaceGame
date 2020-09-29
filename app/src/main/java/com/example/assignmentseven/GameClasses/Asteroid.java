package com.example.assignmentseven.GameClasses;

import android.graphics.Canvas;
import android.graphics.Paint;

import androidx.core.content.ContextCompat;

import com.example.assignmentseven.R;

// Asteroid is an obstacle in the game
class Asteroid extends DynamicSprite {

    public Paint paint = new Paint();

    // Constructors
    public Asteroid(float x, float y, float radius){
        super(x, y, radius);
        this.paint.setColor(getColor(R.color.colorMiddleRed));
    }
    public Asteroid(float x, float y, float radius, int colorId){
        super(x, y, radius);
        this.paint.setColor(getColor(colorId));
    }


    // draws to the screen
    public void draw(Canvas canvas){ canvas.drawCircle(x,y, radius, paint); }
}
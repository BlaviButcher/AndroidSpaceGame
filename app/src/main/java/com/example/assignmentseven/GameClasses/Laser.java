package com.example.assignmentseven.GameClasses;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.assignmentseven.R;

// Laser is the main class for the laser in the game
class Laser extends DynamicSprite {

    public Paint paint = new Paint();

    // Constructors
    public Laser(GraphicsView screen, float x, float y, float radius){
        super(screen, x, y, radius);
        this.paint.setColor(getColor(R.color.colorOrangeYellowCrayola));

    }

    public Laser(GraphicsView screen, float x, float y, float radius, int colorId){
        super(screen,x, y, radius);
        this.paint.setColor(getColor(colorId));
    }


    // draws to the screen
    public void draw(Canvas canvas){ canvas.drawCircle(x,y, radius, paint); }
}

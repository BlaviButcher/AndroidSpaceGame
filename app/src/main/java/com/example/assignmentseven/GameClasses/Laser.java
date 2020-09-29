package com.example.assignmentseven.GameClasses;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.assignmentseven.R;

// Laser is the main class for the laser in the game
class Laser extends DynamicSprite {

    // Make the radius for a laser static - never have a need to change
    private static float radius = 15;

    // Colour of the laser, may change to some kind of bitmap later
    public Paint paint = new Paint();

    // Constructors
    public Laser(GraphicsView screen){
        super(screen, screen.width / 2, screen.height - 3 * radius, radius);
        this.paint.setColor(getColor(R.color.colorOrangeYellowCrayola));
    }

    public Laser(GraphicsView screen, float x, float y){
        super(screen, x, y,radius);
        this.paint.setColor(getColor(R.color.colorOrangeYellowCrayola));
    }

    public Laser(GraphicsView screen, float x, float y, int colorId){
        super(screen,x, y, radius);
        this.paint.setColor(getColor(colorId));
    }


    // draws to the screen
    public void draw(Canvas canvas){ canvas.drawCircle(x,y, radius, paint); }


    // This is true if the laser is out of the screen and should be destroyed
    public boolean outOfBounds(){
        return this.x > screen.width ||
                this.y > screen.height ||
                this.x < 0 ||
                this.y < 0;
    }
}

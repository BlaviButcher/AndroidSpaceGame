package com.example.assignmentseven.GameClasses;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

import com.example.assignmentseven.GameUtils.Vector;
import com.example.assignmentseven.GameUtils.Point;
import com.example.assignmentseven.R;



// Laser is the main class for the laser in the game
class Laser extends DynamicSprite {

    // Laser State

    // tail is the trailing circles for the laser
    private static int tailLength = 3;
    private Point[] tail;

    public boolean hidden = true;

    // Class constants

    // Min and max velocity for a laser
    private static double MIN_VELOCITY = 6;
    private static double MAX_VELOCITY = 15;

    // Make the radius for a laser static - never have a need to change
    private static int radius = 15;
    private static int outlineRadius = 23;

    // Colour of the laser, may change to some kind of bitmap later
    public Paint laserPaint = new Paint();
    public Paint outlinePaint = new Paint();

    // Constructors
    public Laser(GraphicsView screen){
        super(screen, -500, -500, radius);
        this.laserPaint.setColor(getColor(R.color.color_laser));
        this.outlinePaint.setColor(getColor(R.color.color_laser_outline));

        tail = new Point[tailLength];
        for (int i = 0; i < tailLength; i++)
            tail[i] = pos.copy();
    }


    // draws to the screen
    public void draw(Canvas canvas){
        canvas.drawCircle(pos.x, pos.y, outlineRadius, outlinePaint);
        for (Point p : tail)
            canvas.drawCircle(p.x, p.y, outlineRadius, outlinePaint);

        for (Point p : tail)
            canvas.drawCircle(p.x, p.y, radius, laserPaint);
    }


    // override move method to handle outOfBounds
    @Override
    public void move() {
        if (outOfBounds()){
            hide();
            screen.decrementLives();
        } else {
            for (int i = tailLength-2; i >= 0; i--)
                tail[i+1] = tail[i];
            tail[0] = pos.copy();
            super.move();
        }
    }

    // Hides the laser
    public void hide(){
        pos.x = -500; pos.y = -500;
        velocity = new Vector();
        hidden = true;
    }

    // Shoots the laser
    public void shoot(Vector vel, Point spaceship){
        pos = new Point(spaceship.x, (int) (spaceship.y));
        tail = new Point[tailLength];
        for (int i = 0; i < tailLength; i++)
            tail[i] = pos.copy();

        setVelocity(vel);
        hidden = false;
    }


    // This is true if the laser is out of the screen and should be destroyed
    public boolean outOfBounds(){
        if (hidden) return false;
        return pos.x > screen.width ||
                pos.y > screen.height ||
                pos.x < 0 ||
                pos.y < 0;
    }



    // Sets the velocity for the laser
    @Override
    public void setVelocity(Vector v) {
        double mag = v.mag();
        if (mag < MIN_VELOCITY){
            v = v.unit().scale(MIN_VELOCITY);
        } else if (mag > MAX_VELOCITY){
            v = v.unit().scale(MAX_VELOCITY);
        }
        velocity = v;
    }


}

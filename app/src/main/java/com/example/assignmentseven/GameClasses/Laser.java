package com.example.assignmentseven.GameClasses;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.assignmentseven.GameUtils.Vector;
import com.example.assignmentseven.GameUtils.Point;
import com.example.assignmentseven.R;


// TODO: Laser respawns if you swipe again while laser is travelling
// Laser is the main class for the laser in the game
class Laser extends DynamicSprite {

    // Min and max velocity for a laser
    private static double MIN_VELOCITY = 10;
    private static double MAX_VELOCITY = 18;

    // Make the radius for a laser static - never have a need to change
    private static int radius = 15;
    private boolean hidden = true;

    // Colour of the laser, may change to some kind of bitmap later
    public Paint paint = new Paint();

    // Constructors
    public Laser(GraphicsView screen){
        super(screen, 0, -radius, radius);
        this.paint.setColor(getColor(R.color.color_orange_yellow_crayola));
    }


    // draws to the screen
    public void draw(Canvas canvas){ canvas.drawCircle(pos.x, pos.y, radius, paint); }


    // override move method to handle outOfBounds
    @Override
    public void move() {
        if (outOfBounds()){
            hide();
            screen.lives--;
        } else {
            super.move();
        }
    }

    // Hides the laser
    public void hide(){
        pos.x = -radius;
        velocity = new Vector();
        hidden = true;
    }

    // Shoots the laser
    public void shoot(Vector vel){
        pos = startPoint();
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


    // Returns the start point for the ball
    // put in function as we want the start point to change with screen size
    private Point startPoint(){
        return new Point(screen.width / 2, (int) (screen.height - 3 * radius));
    }

}

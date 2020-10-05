package com.example.assignmentseven.GameClasses;

import com.example.assignmentseven.GameClasses.Sprite;
import com.example.assignmentseven.GameUtils.Point;
import com.example.assignmentseven.GameUtils.Vector;

// DynamicSprite is a sprite that can move
abstract class DynamicSprite extends Sprite {

    // The speed that this object can move
    protected Vector velocity = new Vector();

    public DynamicSprite(GraphicsView screen, int x, int y, int radius){
        super(screen, x, y, radius);
    }
    public DynamicSprite(GraphicsView screen, Point pos, int radius){
        super(screen, pos, radius);
    }


    // Sets the velocity variables for a DynamicSprite
    public void setVelocity(Vector v){
        velocity = v;
    }

    // move will move the sprite by dx * velocity, dy * velocity
    public void move() {
        pos.add(velocity);
    }
}

package com.example.assignmentseven.GameClasses;

import com.example.assignmentseven.GameClasses.Sprite;

// DynamicSprite is a sprite that can move
abstract class DynamicSprite extends Sprite {

    // The speed that this object can move
    protected float dx,dy;

    public DynamicSprite(GraphicsView screen, float x, float y, float radius){
        super(screen, x, y, radius);
    }


    // Sets the velocity variables for a DynamicSprite
    public void setVelocity(float dx, float dy){
        this.dx = dx; this.dy = dy;
    }

    // move will move the sprite by dx * velocity, dy * velocity
    public void move() {
        this.x += this.dx;
        this.y += this.dy;

//            // check constraints
//            if (this.x > width - this.radius) this.x = width - (int) this.radius;
//            if (this.x < this.radius) this.x = (int) this.radius;
//            if (this.y > height - this.radius) this.y = height - (int) this.radius;
//            if (this.y < this.radius) this.y = (int) this.radius;
    }
}

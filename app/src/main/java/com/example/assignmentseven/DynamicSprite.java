package com.example.assignmentseven;

// DynamicSprite is a sprite that can move
abstract class DynamicSprite extends Sprite {

    // The speed that this object can move
    protected float dx,dy;

    public DynamicSprite(float x, float y, float radius){
        super(x, y, radius);
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

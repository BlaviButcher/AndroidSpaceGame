package com.example.assignmentseven;

import android.graphics.Canvas;

// Sprite class contains the logic for a drawable on the game activity
abstract class Sprite {

    // Position of the sprite
    protected float x, y;

    // Radius of the sprite hit box
    protected float radius;

    // Constructor
    public Sprite(float x, float y, float radius){
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    // Draw method will draw the sprite to the screen
    public abstract void draw(Canvas canvas);

    // collidesWith is true when the sprites hit-boxes overlap, otherwise false
    public boolean collidesWith(Sprite other){
        return (Math.sqrt(Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2)) < this.radius + other.radius);
    }
}
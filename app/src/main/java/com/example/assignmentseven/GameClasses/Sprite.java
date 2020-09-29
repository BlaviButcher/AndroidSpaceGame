package com.example.assignmentseven.GameClasses;

import android.graphics.Canvas;

import androidx.core.content.ContextCompat;

// Sprite class contains the logic for a drawable on the game activity
abstract class Sprite {

    // Position of the sprite
    protected float x, y;

    // Radius of the sprite hit box
    protected float radius;

    protected GraphicsView screen;

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

    // getColor implementation for these objects
    protected int getColor(int colorId){
        return ContextCompat.getColor(this.screen.getContext(), colorId);
    }
}
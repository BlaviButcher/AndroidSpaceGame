package com.example.assignmentseven.GameClasses;

import android.graphics.Canvas;
import androidx.core.content.ContextCompat;
import com.example.assignmentseven.GameUtils.Point;

// Sprite class contains the logic for a drawable on the game activity
abstract class Sprite {

    // Position of the sprite
    protected Point pos;

    // Radius of the sprite hit box
    protected int radius;

    protected GraphicsView screen;

    // Constructor
    public Sprite(GraphicsView screen, int x, int y, int radius){
        this.screen = screen;
        this.pos = new Point(x,y);
        this.radius = radius;
    }
    public Sprite(GraphicsView screen, Point pos, int radius){
        this.screen = screen;
        this.pos = pos;
        this.radius = radius;
    }


    // Draw method will draw the sprite to the screen
    public abstract void draw(Canvas canvas);

    // collidesWith is true when the sprites hit-boxes overlap, otherwise false
    public boolean collidesWith(Sprite other){
        if (this == null || other == null) return false;
        return pos.distanceTo(other.pos) < radius + other.radius;
    }



    // getColor implementation for these objects
    protected int getColor(int colorId){
        return ContextCompat.getColor(this.screen.getContext(), colorId);
    }
}
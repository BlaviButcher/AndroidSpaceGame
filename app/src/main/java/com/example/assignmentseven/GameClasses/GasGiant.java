package com.example.assignmentseven.GameClasses;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.assignmentseven.GameUtils.Vector;
import com.example.assignmentseven.R;

public class GasGiant extends Sprite {

    public Paint backgroundPaint = new Paint();
    public Paint foregroundPaint = new Paint();


    // Radius for rendering the planet
    public int planetRadius;

    // Constructors
    public GasGiant(GraphicsView screen, int planetRadius, int radius){
        super(screen, 0, 0, radius);
        this.backgroundPaint.setColor(getColor(R.color.color_gas_giant_background));
        this.foregroundPaint.setColor(getColor(R.color.color_gas_giant_foreground));
        this.planetRadius = planetRadius;
        respawn();
    }

    // draws to the screen
    public void draw(Canvas canvas){
        canvas.drawCircle(pos.x,pos.y, radius, backgroundPaint );
        canvas.drawCircle(pos.x,pos.y, planetRadius, foregroundPaint );
    }


    // gives the gas giant a random position in it's spawning zone
    public void respawn()
    {
        pos.x = screen.randIntInRange(radius, screen.width-radius);
        pos.y = screen.randIntInRange(radius, screen.height-radius);
    }

    // pull gets the distance between itself and another sprite and
    public void pull(DynamicSprite other){
        Vector change = new Vector(other.pos, pos);
//        change.scale(1 / Math.pow(change.mag(), 2));
        change.scale(1 / change.mag());
        other.velocity.add(change);
    }
}

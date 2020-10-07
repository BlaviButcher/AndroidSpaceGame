package com.example.assignmentseven.GameClasses;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

import com.example.assignmentseven.GameUtils.Vector;
import com.example.assignmentseven.R;

public class GasGiant extends Sprite {

    public Paint paint = new Paint();

    // Dividends used when deciding bounds
    final double UPPER_DIVIDEND = 0.25;
    final double LOWER_DIVIDEND = 0.75;

    // Radius for rendering the planet
    public int planetRadius;

    // Constructors
    public GasGiant(GraphicsView screen, int planetRadius, int radius){
        super(screen, 0, 0, radius);
        this.paint.setColor(getColor(R.color.color_gas_giant));
        this.planetRadius = planetRadius;
        respawn();
    }

    // draws to the screen
    public void draw(Canvas canvas){ canvas.drawCircle(pos.x,pos.y, planetRadius, paint); }


    // gives the gas giant a random position in it's spawning zone
    public void respawn()
    {
        pos.x = screen.randIntInRange(radius, screen.width-radius);
        pos.y = screen.randIntInRange((int) (screen.height * UPPER_DIVIDEND), (int)(screen.height * LOWER_DIVIDEND));
    }

    // pull gets the distance between itself and another sprite and
    public void pull(DynamicSprite other){
        Vector change = new Vector(other.pos, pos);
//        change.scale(1 / Math.pow(change.mag(), 2));
        change.scale(1 / change.mag());
        other.velocity.add(change);
    }
}
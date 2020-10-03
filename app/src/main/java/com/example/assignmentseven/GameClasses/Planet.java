package com.example.assignmentseven.GameClasses;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.assignmentseven.R;

import java.util.Random;

// Planet is the 'target' in the game
public class Planet extends Sprite
{
    public Paint paint = new Paint();

    // Need a little space between asteroids and planets - currently 5%
    final double LOWER_DIVIDEND = 0.25;

    public Planet(GraphicsView screen, float radius, int colorID)
    {
        super(screen, 0, 0, radius);
        paint.setColor(getColor(colorID));
        respawn();
    }

    public Planet(GraphicsView screen,  float radius)
    {
        super(screen, 0, 0, radius);
        paint.setColor(getColor(R.color.colorDarkPurple));
        respawn();
    }


    @Override
    public void draw(Canvas canvas)
    {
        canvas.drawCircle(x, y, radius, paint);
    }

    public void respawn()
    {
        int x_rightBound = screen.width;

        int y_lowerBound = (int) (screen.height * LOWER_DIVIDEND);
        x = screen.rand.nextInt(screen.width - (int)radius * 2) + radius;
        y = screen.rand.nextInt(y_lowerBound - (int)radius * 2) + radius;

    }
}

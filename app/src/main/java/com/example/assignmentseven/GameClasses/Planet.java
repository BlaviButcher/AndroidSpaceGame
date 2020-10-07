package com.example.assignmentseven.GameClasses;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.assignmentseven.R;

// Planet is the 'target' in the game
public class Planet extends Sprite
{
    public Paint paint = new Paint();

    // Need a little space between asteroids and planets - currently 5%
    final double LOWER_DIVIDEND = 0.25;

    public Planet(GraphicsView screen, int radius, int colorID)
    {
        super(screen, 0, 0, radius);
        paint.setColor(getColor(colorID));
        respawn();
    }

    public Planet(GraphicsView screen, int radius)
    {
        super(screen, 0, 0, radius);
        paint.setColor(getColor(R.color.color_dark_purple));
        respawn();
    }


    @Override
    public void draw(Canvas canvas)
    {
        canvas.drawCircle(pos.x, pos.y, radius, paint);
    }

    public void respawn()
    {
        int y_lowerBound = (int) (screen.height * LOWER_DIVIDEND);
        pos.x = screen.rand.nextInt(screen.width - radius * 2) + radius;
        pos.y = screen.rand.nextInt(y_lowerBound - radius * 2) + radius;
    }
}

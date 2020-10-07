package com.example.assignmentseven.GameClasses;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.assignmentseven.R;

public class Spaceship extends Sprite
{
    public Paint paint = new Paint();

    public Spaceship(GraphicsView screen, int radius, int colorID)
    {
        super(screen, screen.width / 2, screen.height - 100, radius);
    }

    public Spaceship(GraphicsView screen, int radius)
    {
        super(screen, screen.width / 2, screen.height - 100, radius);
        paint.setColor(getColor(R.color.color_floral_white));
    }

    @Override
    public void draw(Canvas canvas)
    {
        canvas.drawCircle(pos.x, pos.y, radius, paint);
    }
}

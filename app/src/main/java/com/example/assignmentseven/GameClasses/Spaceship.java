package com.example.assignmentseven.GameClasses;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.assignmentseven.R;

public class Spaceship extends Sprite
{
    public Paint paint = new Paint();

    public Spaceship(GraphicsView screen, float radius, int colorID)
    {
        super(screen, screen.width / 2, screen.height - 100, radius);
    }

    public Spaceship(GraphicsView screen, float radius)
    {
        super(screen, screen.width / 2, screen.height - 100, radius);
        paint.setColor(getColor(R.color.colorFloralWhite));
    }

    @Override
    public void draw(Canvas canvas)
    {
        canvas.drawCircle(x, y, radius, paint);
    }
}

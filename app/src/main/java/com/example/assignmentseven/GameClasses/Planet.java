package com.example.assignmentseven.GameClasses;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.assignmentseven.R;

// Planet is the 'target' in the game
public class Planet extends Sprite
{
    public Paint paint = new Paint();

    public Planet(GraphicsView screen, float posX, float posY, float radius, int colorID)
    {
        super(screen, posX, posY, radius);
        paint.setColor(getColor(colorID));
    }

    public Planet(GraphicsView screen, float posX, float posY, float radius)
    {
        super(screen, posX, posY, radius);
        paint.setColor(getColor(R.color.colorDarkPurple));
    }

    @Override
    public void draw(Canvas canvas)
    {
        canvas.drawCircle(x, y, radius, paint);
    }
}
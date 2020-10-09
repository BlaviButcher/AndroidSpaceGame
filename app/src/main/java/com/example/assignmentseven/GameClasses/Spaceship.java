package com.example.assignmentseven.GameClasses;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import com.example.assignmentseven.R;

public class Spaceship extends Sprite
{
    public Paint paint = new Paint();

    private final Bitmap bmAsteroid = BitmapFactory.decodeResource(screen.getResources(), R.drawable.spaceship);
    private final Rect srcRect = new Rect(0, 0, bmAsteroid.getWidth(), bmAsteroid.getHeight());
    private final Rect destRect = new Rect();

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
        //canvas.drawCircle(pos.x, pos.y, radius, paint);

        destRect.set(pos.x - radius, pos.y - radius, pos.x + radius, pos.y + radius);
        canvas.drawBitmap(bmAsteroid, srcRect, destRect, paint);
    }
}

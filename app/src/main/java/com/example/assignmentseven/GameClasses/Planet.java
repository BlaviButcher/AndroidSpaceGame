package com.example.assignmentseven.GameClasses;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorSpace;
import android.graphics.Paint;
import android.graphics.Rect;

import com.example.assignmentseven.R;

// Planet is the 'target' in the game
public class Planet extends Sprite
{
    public Paint paint = new Paint();

    public final Bitmap bitmap = BitmapFactory.decodeResource(screen.getContext().getResources(), R.mipmap.planet_foreground);
    private final Rect bitmapSrcRect = new Rect(0,0, bitmap.getWidth(), bitmap.getHeight());
    private final Rect bitmapDestRect = new Rect();

    // Need a little space between asteroids and planets - currently 5%
    private final double LOWER_DIVIDEND = 0.25;

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
    public void draw(Canvas canvas) {
        canvas.drawCircle(pos.x, pos.y, radius, paint);
        bitmapDestRect.set(pos.x - radius,pos.y -radius, pos.x + radius, pos.y + radius);
        canvas.drawBitmap(bitmap,bitmapSrcRect,bitmapDestRect,null);
    }

    public void respawn()
    {
        int y_lowerBound = (int) (screen.height * LOWER_DIVIDEND);
        pos.x = screen.rand.nextInt(screen.width - radius * 2) + radius;
        pos.y = screen.rand.nextInt(y_lowerBound - radius * 2) + radius;
    }
}

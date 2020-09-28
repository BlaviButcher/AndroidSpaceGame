package com.example.assignmentseven;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import static java.security.AccessController.getContext;

public class GameActivity extends AppCompatActivity {

    // Dimensions of activity bounds
    private int width, height;


    public class GraphicsView extends View {
        public GraphicsView(Context context) {
            super(context);
        }

        @Override
        public void onDraw(Canvas canvas)
        {
            super.onDraw(canvas);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gamescreen);

        // Hide actionbar
        getSupportActionBar().hide();

        // Enable fullscreen
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);


        // Create graphics view and add to constraintLayout
        GraphicsView graphicsView = new GraphicsView(this);
        ConstraintLayout constraintLayout = (ConstraintLayout) findViewById(R.id.cl_gamescreen);
        constraintLayout.addView(graphicsView);
    }

    protected void onResume()
    {
        super.onResume();

        // Hide actionbar
        getSupportActionBar().hide();

        
        // Enable fullscreen
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

    }


    // Sprite class contains the logic for a drawable on the game activity
    abstract class Sprite {

        // Position of the sprite
        protected int x, y;

        // Radius of the sprite hit box
        protected float radius;

        // Constructor
        public Sprite(int x, int y, float radius){
            this.x = x;
            this.y = y;
            this.radius = radius;
        }

        // Draw method will draw the sprite to the screen
        public abstract void draw(Canvas canvas);

        // collidesWith is true when the sprites hit-boxes overlap, otherwise false
        public boolean collidesWith(Sprite other){
            return (Math.sqrt(Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2)) < this.radius + other.radius);
        }
    }

    // DynamicSprite is a sprite that can move
    abstract class DynamicSprite extends Sprite {

        // The speed that this object can move
        protected float velocity;

        public DynamicSprite(int x, int y, float radius, float velocity){
            super(x, y, radius);
            this.velocity = velocity;
        }


        // move will move the sprite by dx * velocity, dy * velocity
        public void move(float dx, float dy) {
            this.x += dx * this.velocity;
            this.y += dy * this.velocity;

            // check constraints
            if (this.x > width - this.radius) this.x = width - (int) this.radius;
            if (this.x < this.radius) this.x = (int) this.radius;
            if (this.y > height - this.radius) this.y = height - (int) this.radius;
            if (this.y < this.radius) this.y = (int) this.radius;
        }
    }


    // Laser is the main class for the laser in the game
    class Laser extends DynamicSprite {

        public Paint paint = new Paint();

        // Constructors
        public Laser(int x, int y, float radius){
            super(x, y, radius, 1);
            this.paint.setColor(getColor(R.color.colorOrangeYellowCrayola));

        }

        public Laser(int x, int y, float radius, int colorId){
            super(x, y, radius, 1);
            this.paint.setColor(getColor(colorId));
        }

        public Laser(int x, int y, float radius, int colorId, float velocity){
            super(x, y, radius, velocity);
            this.paint.setColor(getColor(colorId));
        }

        // draws to the screen
        public void draw(Canvas canvas){ canvas.drawCircle(x,y, radius, paint); }
    }

    public class Planet extends Sprite
    {
        public Paint paint = new Paint();

        public Planet(int posX, int posY, float radius, int colorID)
        {
            super(posX, posY, radius);
            paint.setColor(getColor(colorID));
        }

        public Planet(int posX, int posY, float radius)
        {
            super(posX, posY, radius);
            paint.setColor(getColor(R.color.colorDarkPurple));
        }

        @Override
        public void draw(Canvas canvas)
        {
            canvas.drawCircle(x, y, radius, paint);
        }
    }



    // Asteroid is an obstacle in the game
    class Asteroid extends DynamicSprite {

        public Paint paint = new Paint();

        // Constructors
        public Asteroid(int x, int y, float radius){
            super(x, y, radius, 1);
            this.paint.setColor(getColor(R.color.colorMiddleRed));
        }

        public Asteroid(int x, int y, float radius, int colorId){
            super(x, y, radius, 1);
            this.paint.setColor(getColor(colorId));
        }

        public Asteroid(int x, int y, float radius, int colorId, float velocity){
            super(x, y, radius, velocity);
            this.paint.setColor(getColor(colorId));
        }

        // draws to the screen
        public void draw(Canvas canvas){ canvas.drawCircle(x,y, radius, paint); }
    }
}

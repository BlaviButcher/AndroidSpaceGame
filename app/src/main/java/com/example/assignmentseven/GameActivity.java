package com.example.assignmentseven;
import android.content.Context;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

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


    // Sprite class contains the logic for a drawable on the game activity
    abstract class Sprite {

        // Position of the sprite
        protected int x, y;

        // Radius of the sprite hit box
        protected float radius;

        // Draw method will draw the sprite to the screen
        public abstract void draw();

        // collidesWith is true when the sprites hit-boxes overlap, otherwise false
        public boolean collidesWith(Sprite other){
            if (Math.sqrt(Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2)) < this.radius + other.radius)
                return true;
            return false;
        }
    }

    // DynamicSprite is a sprite that can move
    abstract class DynamicSprite extends Sprite {

        // The speed that this object can move
        private float velocity;

        // move will move the sprite by dx * velocity, dy * velocity
        public void move(float dx, float dy){
            this.x += dx * this.velocity;
            this.y += dy * this.velocity;

            // check constraints
            if (this.x  > width - this.radius) this.x = width - (int) this.radius;
            if (this.x  < this.radius) this.x = (int) this.radius;
            if (this.y  > height - this.radius) this.y = height - (int) this.radius;
            if (this.y  < this.radius) this.y = (int) this.radius;
        }

    }



}

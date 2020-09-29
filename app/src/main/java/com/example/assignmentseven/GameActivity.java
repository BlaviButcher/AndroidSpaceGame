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


    // Graphics view class
    public class GraphicsView extends View {

        // Dimensions of activity bounds
        private int width, height;


        public GraphicsView(Context context) {
            super(context);

            // Get initial width and height - just using screen size..
            width = context.getResources().getDisplayMetrics().widthPixels;
            height = context.getResources().getDisplayMetrics().heightPixels;
        }


        // Tracks the size of the screen
        @Override
        protected void onSizeChanged(int w, int h, int oldW, int oldH) {
            super.onSizeChanged(w, h, oldW, oldH);
            width = w;
            height = h;
        }



        @Override
        public void onDraw(Canvas canvas)
        {
            super.onDraw(canvas);
        }





        // Laser is the main class for the laser in the game
        class Laser extends DynamicSprite {

            public Paint paint = new Paint();

            // Constructors
            public Laser(int x, int y, float radius){
                super(x, y, radius);
                this.paint.setColor(getColor(R.color.colorOrangeYellowCrayola));

            }

            public Laser(int x, int y, float radius, int colorId){
                super(x, y, radius);
                this.paint.setColor(getColor(colorId));
            }


            // draws to the screen
            public void draw(Canvas canvas){ canvas.drawCircle(x,y, radius, paint); }
        }


        // Planet is the 'target' in the game
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
            public Asteroid(float x, float y, float radius){
                super(x, y, radius);
                this.paint.setColor(getColor(R.color.colorMiddleRed));
            }
            public Asteroid(float x, float y, float radius, int colorId){
                super(x, y, radius);
                this.paint.setColor(getColor(colorId));
            }


            // draws to the screen
            public void draw(Canvas canvas){ canvas.drawCircle(x,y, radius, paint); }
        }
    }
}

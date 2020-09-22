package com.example.assignmentseven;
import android.content.Context;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class GameActivity extends AppCompatActivity {

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

        // Create graphics view and add to constraintLayout
        GraphicsView graphicsView = new GraphicsView(this);
        ConstraintLayout constraintLayout = (ConstraintLayout) findViewById(R.id.cl_gamescreen);
        constraintLayout.addView(graphicsView);
    }
}

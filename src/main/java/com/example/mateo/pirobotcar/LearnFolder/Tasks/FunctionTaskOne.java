package com.example.mateo.pirobotcar.LearnFolder.Tasks;

import android.content.ClipData;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.example.mateo.pirobotcar.R;


public class FunctionTaskOne extends AppCompatActivity  {
    TextView target;
    Button forward, backwards, left, right, run;
    boolean success = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_function_task_one);
        forward = findViewById(R.id.forward);
        target = findViewById(R.id.target);

        run = findViewById(R.id.run);

        forward.setOnLongClickListener(longClickListener);
        backwards.setOnLongClickListener(longClickListener);
        left.setOnLongClickListener(longClickListener);
        right.setOnLongClickListener(longClickListener);
        target.setOnDragListener(drawListener);

        run.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    Toast.makeText(getApplicationContext(), "Task NOT sucesfully completed", Toast.LENGTH_LONG).show();
                }

        });
    }

    View.OnLongClickListener longClickListener = new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
            ClipData data = ClipData.newPlainText("", "");
            View.DragShadowBuilder myShadowBuilder = new View.DragShadowBuilder(v);
            v.startDrag(data, myShadowBuilder, v, 0);
            return true;
        }
    };

    View.OnDragListener drawListener = new View.OnDragListener() {

        @Override
        public boolean onDrag(View v, DragEvent event) {
            int dragEvent = event.getAction();
            final View view = (View) event.getLocalState();

            switch (dragEvent) {
                //Poziva se kada jedan od texview dode u 4. texView
                case DragEvent.ACTION_DRAG_ENTERED:
                    break;

                case DragEvent.ACTION_DRAG_EXITED:
                    break;

                case DragEvent.ACTION_DROP:
                    if (view.getId() == R.id.forward /*&& view.getId() == R.id.backwards && view.getId() == R.id.left && view.getId() == R.id.right*/ ) {
                        view.animate()
                                .x(target.getX())
                                .y(target.getY())
                                .setDuration(700)
                                .start();
                        Toast.makeText(getApplicationContext(), "Task sucesfully completed, " + " Click the RUN button now!", Toast.LENGTH_LONG).show();
                        success = true;
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Task NOT sucesfully completed", Toast.LENGTH_LONG).show();
                        success = false;
                    }
                    break;
            }
            return true;
        }
    };

    }
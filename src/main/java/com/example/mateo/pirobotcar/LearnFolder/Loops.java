package com.example.mateo.pirobotcar.LearnFolder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mateo.pirobotcar.LearnFolder.Tasks.LoopTakOne;
import com.example.mateo.pirobotcar.LearnFolder.Tasks.LoopTaskTwo;
import com.example.mateo.pirobotcar.R;

public class Loops extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loops);

        Button loopTaskOne = findViewById(R.id.task_one_loop);
        Button loppTaskTwo = findViewById(R.id.task_two_loop);

        loopTaskOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getApplicationContext(), LoopTakOne.class);
                startActivity(in);
            }
        });

        loppTaskTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in2 = new Intent(getApplicationContext(), LoopTaskTwo.class);
                startActivity(in2);
            }
        });
    }
}

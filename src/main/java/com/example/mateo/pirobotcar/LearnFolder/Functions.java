package com.example.mateo.pirobotcar.LearnFolder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mateo.pirobotcar.LearnFolder.Tasks.FunctionTaskTwo;
import com.example.mateo.pirobotcar.LearnFolder.Tasks.FunctionTaskOne;
import com.example.mateo.pirobotcar.R;

public class Functions extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_functions);

        Button taskOne = findViewById(R.id.task_one_fun);
        Button taskTwo = findViewById(R.id.task_two_fun);

        taskOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in1 = new Intent(getApplicationContext(), FunctionTaskOne.class);
                startActivity(in1);
            }
        });

        taskTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in2 = new Intent(getApplicationContext(), FunctionTaskTwo.class);
                startActivity(in2);
            }
        });
    }
}

package com.example.mateo.pirobotcar.LearnFolder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mateo.pirobotcar.LearnFolder.Tasks.ConditionTaskTwo;
import com.example.mateo.pirobotcar.LearnFolder.Tasks.ConditiontakOne;
import com.example.mateo.pirobotcar.R;

public class Conditions extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conditions);

        Button taskOne = findViewById(R.id.task_one_con);
        Button taskTwo = findViewById(R.id.task_two_con);

        taskOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getApplicationContext(), ConditiontakOne.class);
                startActivity(in);
            }
        });

        taskTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in2 = new Intent(getApplicationContext(), ConditionTaskTwo.class);
                startActivity(in2);
            }
        });
    }
}

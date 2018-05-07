package com.example.mateo.pirobotcar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mateo.pirobotcar.LearnFolder.Conditions;
import com.example.mateo.pirobotcar.LearnFolder.Functions;
import com.example.mateo.pirobotcar.LearnFolder.Loops;
import com.example.mateo.pirobotcar.LearnFolder.Variables;

public class LearnMode extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_mode);

        Button variables_btn = findViewById(R.id.varijable);
        Button condition_btn = findViewById(R.id.uvjeti);
        Button loop_btn = findViewById(R.id.petlje);
        Button function_btn = findViewById(R.id.funkcije);

        variables_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in1 = new Intent(getApplicationContext(), Variables.class);
                startActivity(in1);
            }
        });

        condition_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in2 = new Intent(getApplicationContext(), Conditions.class);
                startActivity(in2);
            }
        });

        loop_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in3 = new Intent(getApplicationContext(), Loops.class);
                startActivity(in3);
            }
        });

        function_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in4 = new Intent(getApplicationContext(), Functions.class);
                startActivity(in4);
            }
        });
    }
}

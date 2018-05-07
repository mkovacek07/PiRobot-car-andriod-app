package com.example.mateo.pirobotcar.LearnFolder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.mateo.pirobotcar.LearnFolder.Tasks.VariableTaskOne;
import com.example.mateo.pirobotcar.LearnFolder.Tasks.VariableTaskTwo;
import com.example.mateo.pirobotcar.R;

import static com.example.mateo.pirobotcar.LearnFolder.Tasks.VariableTaskOne.success;

public class Variables extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_variables);

        Button taskOne = findViewById(R.id.task_one_var);
        Button taskTwo = findViewById(R.id.task_two_var);



                taskOne.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                            Intent in = new Intent(getApplicationContext(), VariableTaskOne.class);
                            startActivity(in);
                    }
                });

        taskTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!success) {
                    Toast.makeText(getApplicationContext(), "You must complete task one first", Toast.LENGTH_LONG).show();
                } else {
                    Intent in2 = new Intent(getApplicationContext(), VariableTaskTwo.class);
                    startActivity(in2);
                }
            }
        });

    }

}


package com.example.mateo.pirobotcar;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainMenu extends AppCompatActivity {
    final Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        Button freeMode = findViewById(R.id.freeMode);
        Button learnMode = findViewById(R.id.learnMode);
        Button help = findViewById((R.id.help));


        freeMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in1 = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(in1);
            }
        });

        learnMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in2 = new Intent(getApplicationContext(), LearnMode.class);
                startActivity(in2);
            }
        });

        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in3 = new Intent(getApplicationContext(), helpActivty.class);
                startActivity(in3);
            }
        });

    }
        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            int id = item.getItemId();
            return super.onOptionsItemSelected(item);
        }
        @Override
        public void onBackPressed() {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Izlaz");
            builder.setMessage("Jeste sigurni da želite izaći?");
            builder.setPositiveButton("Da", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            builder.setNegativeButton("Ne", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();

        }
    }


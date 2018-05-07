package com.example.mateo.pirobotcar.LearnFolder.Tasks;

import android.content.ClipData;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mateo.pirobotcar.R;
import com.google.gson.Gson;

import java.net.URISyntaxException;

import io.socket.client.IO;
import io.socket.emitter.Emitter;

public class VariableTaskTwo extends AppCompatActivity {


    LinearLayout target;
    Button  forward2, backwards, run, reset, delay;
    Button forward;
    TextView distance;
    boolean success = false;
    io.socket.client.Socket socket;
    Gson gson;
    public static final String TAG = "Level";

    int count = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_variable_task_two);
        forward = findViewById(R.id.forward);
        forward2 = findViewById(R.id.forward2);
        backwards = findViewById(R.id.backward);
        target = findViewById(R.id.target);
        delay = findViewById(R.id.delay);
        reset = findViewById(R.id.reset);
        distance = findViewById(R.id.distance);

        run = findViewById(R.id.run);

        gson = new Gson();
        socketConnection();

        forward.setOnLongClickListener(longClickListener);
        forward2.setOnLongClickListener(longClickListener);
        backwards.setOnLongClickListener(longClickListener);
        distance.setOnLongClickListener(longClickListener);
        target.setOnDragListener(drawListener);

        run.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Click");
                if (success) {
                    Toast.makeText(getApplicationContext(), "Task sucesfully completed", Toast.LENGTH_LONG).show();
                    sendSocketData();
                } else {
                    Toast.makeText(getApplicationContext(), "Task NOT sucesfully completed", Toast.LENGTH_LONG).show();
                }
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v==reset){
                    startActivity(new Intent(VariableTaskTwo.this, VariableTaskTwo.class));
                }
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
            View view = (View) event.getLocalState();

            switch (dragEvent) {
                //Poziva se kada jedan od texview dode u 4. texView
                case DragEvent.ACTION_DRAG_ENTERED:
                    break;

                case DragEvent.ACTION_DRAG_EXITED:
                    break;

                case DragEvent.ACTION_DROP:
                    if (view.getId() == R.id.forward) {
                        Log.d(TAG, "Radi naprijed 1");
                        count++;

                    }

                    else if(view.getId() == R.id.forward2){
                        Log.d(TAG, "Radi naprijed 2");
                        count++;

                    }

                    if(view.getId() == R.id.backward && count==3 ){
                        Log.d(TAG, "Radi nazad");
                        count++;
                    }

                    if(view.getId() == R.id.backward && count<3 ){
                        Log.d(TAG, "Radi nazad");
                        count--;
                    }

                    if(view.getId() == R.id.distance){
                        Log.d(TAG, "radi distance");
                        count++;
                    }

                    success = count == 4;

                   view.animate()
                            .x(target.getX())
                            .y(target.getY())
                            .setDuration(700)
                            .start();

                    break;
            }
            return true;
        }
    };

    public void socketConnection() {
        try {
            socket = IO.socket("http://192.168.43.183:8888/");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        Log.d(TAG, "SensorListner: constructor");
        if (socket != null)
            socket.connect();
        Log.d(TAG, "SensorListner: called");


        socket.on(io.socket.client.Socket.EVENT_CONNECT, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                Log.d(TAG, "call: inside connect");
                socket.emit("foo", "hii");
            }
        });
    }

    public void sendSocketData() {
        String thisLevel = "level2";
        String thisObject = gson.toJson(thisLevel);
        Log.d(TAG, "sendSocketData: sendData to server");
        socket.emit("level", thisObject);
    }

}
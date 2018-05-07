package com.example.mateo.pirobotcar.LearnFolder.Tasks;


import android.content.ClipData;
import android.content.ClipDescription;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.view.View.OnDragListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.mateo.pirobotcar.R;
import com.google.gson.Gson;

import java.net.URISyntaxException;

import io.socket.client.IO;
import io.socket.emitter.Emitter;

public class ConditiontakOne extends AppCompatActivity {

    LinearLayout target;
    Button forward, forward2, backwards, run, reset;
    boolean success = false;
    private static final String BUTTON_VIEW_TAG = "DRAG BUTTON";

    io.socket.client.Socket socket;
    Gson gson;
    public static final String TAG = "Level";
    int count = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conditiontak_one);

        forward = findViewById(R.id.forward);
        target = findViewById(R.id.target);

        forward.setOnLongClickListener(longClickListener);
        target.setOnDragListener(drawListener);

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

                    } else if (view.getId() == R.id.forward2) {
                        Log.d(TAG, "Radi naprijed 2");
                        count++;

                    }

                    if (view.getId() == R.id.backward && count == 2) {
                        Log.d(TAG, "Radi nazad");
                        count++;
                    }

                    if (view.getId() == R.id.backward && count < 2) {
                        Log.d(TAG, "Radi nazad");
                        count--;
                    }

                    success = count == 3;

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


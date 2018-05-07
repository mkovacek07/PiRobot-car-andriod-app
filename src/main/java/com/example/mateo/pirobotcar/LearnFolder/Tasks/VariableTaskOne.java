package com.example.mateo.pirobotcar.LearnFolder.Tasks;

import android.content.ClipData;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.mateo.pirobotcar.R;
import com.google.gson.Gson;

import java.net.URISyntaxException;

import io.socket.client.IO;
import io.socket.emitter.Emitter;

public class VariableTaskOne extends AppCompatActivity {

    LinearLayout target;
    Button forward, run;
    public static boolean success = false;
    io.socket.client.Socket socket;
    Gson gson;

    public static  final String TAG = "Level";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_variable_task_one);
        forward = findViewById(R.id.forward);
        target = findViewById(R.id.target);

        run = findViewById(R.id.run);

        gson = new Gson();
        socketConnection();

        forward.setOnLongClickListener(longClickListener);
        target.setOnDragListener(drawListener);

        run.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Click");
                if (success) {
                    sendSocketData("level1");
                } else {
                    Toast.makeText(getApplicationContext(), "Task NOT sucesfully completed", Toast.LENGTH_LONG).show();
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
            final View view = (View) event.getLocalState();

            switch (dragEvent) {
                //Poziva se kada jedan od texview dode u 4. texView
                case DragEvent.ACTION_DRAG_ENTERED:
                    break;

                case DragEvent.ACTION_DRAG_EXITED:
                    break;

                case DragEvent.ACTION_DROP:
                    if (view.getId() == R.id.forward ) {
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

    public void socketConnection() {
        try {
            socket = IO.socket("http://192.168.43.183:8888/");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        Log.d(TAG, "Listner: constructor");
        if (socket != null)
            socket.connect();
        Log.d(TAG, "Listner: called");


        socket.on(io.socket.client.Socket.EVENT_CONNECT, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                Log.d(TAG, "call: inside connect");
                socket.emit("foo", "hii");
            }
        });


    }

    public void sendSocketData(String level){
        String thisLevel = new String(level);
        String thisObject = gson.toJson(thisLevel);
        Log.d(TAG, "sendSocketData: sendData to server");
        socket.emit("level", thisObject);
    }


}
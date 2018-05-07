package com.example.mateo.pirobotcar.LearnFolder.Tasks;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipDescription;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.DragEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.mateo.pirobotcar.R;

import static android.content.ClipDescription.*;

public class ConditionTaskTwo extends AppCompatActivity implements View.OnDragListener, View.OnLongClickListener {

    private LinearLayout target;
    private Button forward;
    private Button forward2;
    private Button delay, button2;
    private static final String BUTTON_VIEW_TAG = "Drag Button";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.condition_tasks_two);
        FindViews();
        implementEvetns();
    }

    private void FindViews() {
        forward = findViewById(R.id.forward);
        forward2 = findViewById(R.id.forward2);
        delay = findViewById(R.id.delay);
        forward.setTag(BUTTON_VIEW_TAG);
        forward2.setTag(BUTTON_VIEW_TAG);
        delay.setTag(BUTTON_VIEW_TAG);
        target = findViewById(R.id.target);

    }

    private void implementEvetns(){
        forward.setOnLongClickListener(this);
        forward2.setOnLongClickListener(this);
        delay.setOnLongClickListener(this);
        target.setOnDragListener(this);
    }


    @Override
    public boolean onDrag(View view, DragEvent event) {
        int action = event.getAction();

        switch (action) {
            case DragEvent.ACTION_DRAG_STARTED:
                if(event.getClipDescription().hasMimeType(MIMETYPE_TEXT_PLAIN)){

                 return true;
                }
                return false;

            case DragEvent.ACTION_DROP:

                    ClipData.Item item = event.getClipData().getItemAt(0);
                    String dragData = item.getText().toString();
                    view.invalidate();
                    View v = (View) event.getLocalState();
                    ViewGroup owner = (ViewGroup) v.getParent();
                    owner.removeView(v);
                    LinearLayout container = (LinearLayout) view;
                    container.addView(v);
                    v.setVisibility(View.VISIBLE);

                    return true;

        }
        return false;
    }

    @Override
    public boolean onLongClick(View view) {
        ClipData.Item item = new ClipData.Item((CharSequence) view.getTag());
        String[] mimeTypes = {MIMETYPE_TEXT_PLAIN};
        ClipData data  = new ClipData(view.getTag().toString(), mimeTypes, item);
        View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);

        view.startDrag( data, shadowBuilder, view, 0);
        view.setVisibility(View.VISIBLE);
        return true;
    }
}

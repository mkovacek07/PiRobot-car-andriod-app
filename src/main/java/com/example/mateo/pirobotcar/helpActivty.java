package com.example.mateo.pirobotcar;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class helpActivty extends Activity {

    ExpandleListAdapter listAdapter;
    ExpandableListView expandableListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_activty);

        //get the lisView
        expandableListView =  findViewById(R.id.lvExp);

        //preparing list data
        prepareListData();

        listAdapter = new ExpandleListAdapter(this, listDataHeader, listDataChild);

        //setting list adapter
        expandableListView.setAdapter(listAdapter);

        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                return false;
            }
        });

        /*expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(getApplicationContext(), listDataHeader.get(groupPosition)+
                "Collapsed", Toast.LENGTH_SHORT).show();
            }
        });

        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(getApplicationContext(), listDataHeader.get(groupPosition)+ "Expanded", Toast.LENGTH_SHORT).show();
            }
        });
*/
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

                Toast.makeText(getApplicationContext(), listDataHeader.get(groupPosition) + ":"
                + listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition), Toast.LENGTH_SHORT).show();
                return false;
            }
        });

    }

    private void prepareListData() {
        listDataHeader = new ArrayList<>();
        listDataChild = new HashMap<>();

        //Dodaj podatke
        listDataHeader.add("Variables");
        listDataHeader.add("Conditions");
        listDataHeader.add("Loops");
        listDataHeader.add("Functions");

        //dodaj dijete
        List<String> var = new ArrayList<>();
        var.add("Variables are used to store information to be referenced and manipulated in a computer program." +
                " They also provide a way of labeling data with a descriptive name," +
                " so our programs can be understood more clearly by the reader and ourselves." +
                " It is helpful to think of variables as containers that hold information." +
                " Their sole purpose is to label and store data in memory. " +
                "This data can then be used throughout your program.");

        List<String> con = new ArrayList<>();
        con.add("Often a computer program must make choices on which way to proceed, " +
                "e.g., if the ball is in bounds, do one thing, else, do something different" +
                "... if the data has all been processed, end the program, else continue to the next data item... while the player has lives left continue the game.\n" +
                "\n" +
                "These \"things\" are called Conditions \n" + "Examples of Conditional Boolean Expressions\n" +
                "\t \n" +
                "     ( money < 5 )                \n" +
                " \n" +
                "     ( loop_counter < length_of_loop )                \n" +
                " \n" +
                "     ( rent > 300  ) \n" +
                " \n" +
                "     ( test_score > 80 && grade_level == 5 ) ");

        List<String> loops = new ArrayList<>();
        loops.add("In computer science, a loop is a programming structure that repeats a " +
                "sequence of instructions until a specific condition is met. " +
                "Programmers use loops to cycle through values, add sums of numbers," +
                " repeat functions, and many other things.");

        List<String> fun = new ArrayList<>();
        fun.add("Functions combine many instructions into a single line of code. " +
                "Steps to Writing a Function\n" +
                "1. Understand the purpose of the function.\n" +
                "2. Define the data that comes into the function from the caller (in the form of parameters)!\n" +
                "3. Define what data variables are needed inside the function to accomplish its goal.\n" +
                "4. Decide on the set of steps that the program will use to accomplish this goal. (The Algorithm)");


        listDataChild.put(listDataHeader.get(0), var);
        listDataChild.put(listDataHeader.get(1), con);
        listDataChild.put(listDataHeader.get(2), loops);
        listDataChild.put(listDataHeader.get(3), fun);

    }
}



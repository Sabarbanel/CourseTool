package com.example.sarah.coursetool.CourseListing;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.sarah.coursetool.BaseNavigationActivity;
import com.example.sarah.coursetool.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class Listings extends BaseNavigationActivity {

    Spinner spinner;
    DataGenerator dataGenerator = DataGenerator.getGenerator();
    ArrayList<CourseListing> inputData = new ArrayList<CourseListing>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listings);


        Button button =(Button)findViewById(R.id.enrollmentButton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                TextView courseId= findViewById(R.id.courseIdMain);
                ArrayList<String> reg=new ArrayList<String>();
                String cID=courseId.getText().toString();
                reg.add(cID);

                System.out.print(reg);
            }
        });




        Log.d("Hello","Loaded");
        dataGenerator.getAllCourses(inputData);
        RecyclerView courseListContainer = (RecyclerView) findViewById(R.id.recycleView);
        courseListContainer.setHasFixedSize(true);
        RecyclerView.LayoutManager listContainerManager = new LinearLayoutManager(this);
        courseListContainer.setLayoutManager(listContainerManager);
        final RecyclerView.Adapter viewAdapter = new viewAdapter(inputData, getApplicationContext());
        courseListContainer.setAdapter(viewAdapter);

        spinner=(Spinner)findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String s = spinner.getItemAtPosition(position).toString();
                Log.d("Hello",s);
                //String Fall="Fall"
                switch (s) {
                    case "All":

                        dataGenerator.getAllCourses(inputData);
                        viewAdapter.notifyDataSetChanged();
                        break;
                    case "Fall":
                        dataGenerator.getFallCourses(inputData);
                        viewAdapter.notifyDataSetChanged();
                        break;
                    case "Winter":
                        dataGenerator.getWinterCourses(inputData);
                        viewAdapter.notifyDataSetChanged();
                        break;
                    case "Summer":
                        dataGenerator.getSummerCourses(inputData);
                        viewAdapter.notifyDataSetChanged();
                        break;
                    case "CSCI":
                        dataGenerator.getCSCICourses(inputData);
                        viewAdapter.notifyDataSetChanged();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void closePopup(View v) {
        ViewGroup g = (ViewGroup) v.getParent().getParent();
        View x = g.getChildAt(2);
        x.setVisibility(View.GONE);
    }









    /*public void enrollStudent(View v){
        Button button = findViewById(R.id.enrollmentButton);
        TextView preReqs = findViewById(R.id.courseSpotsLeft);
        TextView courseIDMain = findViewById(R.id.courseIdMain);
        TextView courseDeptMain = findViewById(R.id.courseDeptMain);
        String course = courseDeptMain.toString().concat(" "+ courseIDMain.toString());
        int schedID = courseIDMain.getId();
        Log.i("Show me the money", course);
        System.out.println("Money bro: "+course);

    }
*/}

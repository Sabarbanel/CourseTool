package com.example.sarah.coursetool.CourseListing;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.Date;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.example.sarah.coursetool.BaseNavigationActivity;
import com.example.sarah.coursetool.R;

public class Listings extends BaseNavigationActivity {

    Spinner spinner;
    DataGenerator dataGenerator = DataGenerator.getGenerator();
    ArrayList<CourseListing> inputData = new ArrayList<CourseListing>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listings);

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

}

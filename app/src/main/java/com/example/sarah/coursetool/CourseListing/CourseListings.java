package com.example.sarah.coursetool.CourseListing;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.sarah.coursetool.R;

import java.util.ArrayList;
import java.util.Date;


public class CourseListings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listings);
        //Toolbar toolbar = (Toolbar) findViewById (R.id.toolbar);
        //setSupportActionBar(toolbar);

        Date start = new Date(1967, 03, 04);
        Date end = new Date(1970, 03, 05);
        ArrayList<CourseListing> inputData = new ArrayList<CourseListing>();
        CourseListing course1 = new CourseListing("Introduction to the Tyrannosaurus Rex",
                                                    "Professor Fossils", "Archaeology",
                                                    "Not for the faint of heart", start, end,
                                                    275638, 04, 12);


        inputData.add(course1);

        RecyclerView courseListContainer = (RecyclerView) findViewById(R.id.recycleView);
        courseListContainer.setHasFixedSize(true);
        RecyclerView.LayoutManager listContainerManager = new LinearLayoutManager(this);
        courseListContainer.setLayoutManager(listContainerManager);
        RecyclerView.Adapter viewAdapter = new viewAdapter(inputData, getApplicationContext());
        courseListContainer.setAdapter(viewAdapter);
    }

}

package com.example.sarah.coursetool.CompletedCourses;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.sarah.coursetool.BaseNavigationActivity;
import com.example.sarah.coursetool.CourseListing.CourseListing;
import com.example.sarah.coursetool.CourseListing.DataGenerator;
import com.example.sarah.coursetool.R;

import java.util.ArrayList;

public class CompletedCoursesActivity extends BaseNavigationActivity {

    DataGenerator dataGenerator = DataGenerator.getGenerator();
    ArrayList<CourseListing> completeData = new ArrayList<CourseListing>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_completed_courses);
        dataGenerator.getCompletedCourses(completeData);
        RecyclerView courseListContainer = (RecyclerView) findViewById(R.id.completedCoursesRecyclerView);
        courseListContainer.setHasFixedSize(true);
        RecyclerView.LayoutManager listContainerManager = new LinearLayoutManager(this);
        courseListContainer.setLayoutManager(listContainerManager);
        final RecyclerView.Adapter dayAdapter = new CompletedCoursesAdapter(completeData, getApplicationContext());
        courseListContainer.setAdapter(dayAdapter);
    }
}

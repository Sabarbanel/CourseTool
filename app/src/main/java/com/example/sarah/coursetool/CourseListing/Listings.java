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
import android.widget.Spinner;

import com.example.sarah.coursetool.BaseNavigationActivity;
import com.example.sarah.coursetool.R;

public class Listings extends BaseNavigationActivity {

    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listings);

        DataGenerator dataGenerator = DataGenerator.getGenerator();
        ArrayList<CourseListing> inputData = new ArrayList<CourseListing>();
        dataGenerator.getAllCourses(inputData);

        RecyclerView courseListContainer = (RecyclerView) findViewById(R.id.recycleView);
        courseListContainer.setHasFixedSize(true);
        RecyclerView.LayoutManager listContainerManager = new LinearLayoutManager(this);
        courseListContainer.setLayoutManager(listContainerManager);
        RecyclerView.Adapter viewAdapter = new viewAdapter(inputData, getApplicationContext());
        courseListContainer.setAdapter(viewAdapter);
    }

    public void closePopup(View v) {
        ViewGroup g = (ViewGroup) v.getParent().getParent();
        View x = g.getChildAt(2);
        x.setVisibility(View.GONE);
    }

}

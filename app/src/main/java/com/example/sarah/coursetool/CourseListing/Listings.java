package com.example.sarah.coursetool.CourseListing;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import java.util.ArrayList;

import com.example.sarah.coursetool.R;

public class Listings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listings);

        ArrayList<Listing> inputData = new ArrayList<Listing>();
            Listing l1 = new Listing("Halifax", "Nova Scotia", 1);
            Listing l2 = new Listing("Moncton", "New Brunswick", 2 );
            Listing l3 = new Listing("Toronto", "Ontario", 3);
            Listing l4 = new Listing("Montreal", "Quebec", 4);
            Listing l5 = new Listing("Vancouver", "British Columbia", 5);

            inputData.add(l1);
            inputData.add(l2);
            inputData.add(l3);
            inputData.add(l4);
            inputData.add(l5);

        RecyclerView courseListContainer = (RecyclerView) findViewById(R.id.recycleView);
        courseListContainer.setHasFixedSize(true);
        RecyclerView.LayoutManager listContainerManager = new LinearLayoutManager(this);
        courseListContainer.setLayoutManager(listContainerManager);
        RecyclerView.Adapter viewAdapter = new viewAdapter(inputData, getApplication());
        courseListContainer.setAdapter(viewAdapter);
    }

}

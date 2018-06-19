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

import com.example.sarah.coursetool.BaseNavigationActivity;
import com.example.sarah.coursetool.R;

public class Listings extends BaseNavigationActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listings);

                Date start1 = new Date(1967, 01, 04);
                Date end1 = new Date(1970, 04, 05);
                Date start2 = new Date(2017, 01, 06);
                Date end2 = new Date(2017, 4, 07);
                Date start3 = new Date(1960, 01, 02);
                Date end3 = new Date(1970, 05, 03);
                Date start4 = new Date(1980, 01, 01);
                Date end4 = new Date(1990, 8, 01);
                Date start5 = new Date(1900, 04, 02);
                Date end5 = new Date(1997, 8, 03);

                ArrayList<CourseListing> inputData = new ArrayList<CourseListing>();

                CourseListing course1 = new CourseListing("Introduction to the Tyrannosaurus Rex",
                                                            "Dr Fossils", "Archaeology",
                                                            "Not for the faint of heart", start1, end1,
                                                            275638, 4);

                CourseListing course2 = new CourseListing("All About Rocks",
                                                            "Dr Stone", "Archaeology",
                                                            "Learning rocks!", start2, end2,
                                                            14506, 9);

                CourseListing course3 = new CourseListing("Advanced Animal Behaviour",
                                                            "Dr Moose", "Psychology",
                                                            "Cats, dogs, and more!", start3, end3,
                                                            18013, 6);

                CourseListing course4 = new CourseListing("Introduction to Data Structures",
                                                            "Dr Bitwise", "Computer Science",
                                                            "course4.desc = \"Fun course!\"", start4, end4,
                                                            34829, 7);

                CourseListing course5 = new CourseListing("How to Succeed in Business",
                                                            "Dr Moneybags", "Business",
                                                            "Learn how to make money from others for only $1350!", start5, end5,
                                                            40392, 12);

                inputData.add(course1);
                inputData.add(course2);
                inputData.add(course3);
                inputData.add(course4);
                inputData.add(course5);
        inputData.add(course1);
        inputData.add(course2);
        inputData.add(course3);
        inputData.add(course4);
        inputData.add(course5);
        inputData.add(course1);
        inputData.add(course2);
        inputData.add(course3);
        inputData.add(course4);
        inputData.add(course5);

        RecyclerView courseListContainer = (RecyclerView) findViewById(R.id.recycleView);
        courseListContainer.setHasFixedSize(true);
        RecyclerView.LayoutManager listContainerManager = new LinearLayoutManager(this);
        courseListContainer.setLayoutManager(listContainerManager);
        RecyclerView.Adapter viewAdapter = new viewAdapter(inputData, getApplicationContext());
       courseListContainer.setAdapter(viewAdapter);
    }

    public void closePopup(View v) {
        ViewGroup g = (ViewGroup) v.getParent().getParent();
        View x = g.getChildAt(1);
        x.setVisibility(View.GONE);
    }

}

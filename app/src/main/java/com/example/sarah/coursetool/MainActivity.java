package com.example.sarah.coursetool;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.sarah.coursetool.CompletedCourses.CompletedCoursesActivity;
import com.example.sarah.coursetool.CourseListing.Listings;
import com.example.sarah.coursetool.ViewCourseSchedule.WeekSchedule;

/**
 * The "landing page" Activity for the app.
 */
public class MainActivity extends BaseNavigationActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listview = findViewById(R.id.main_nav_option_list);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?>adapter,View v, int position, long a){
                String item = (String) adapter.getItemAtPosition(position);
                if(item.equals("View Schedule")) {
                    Intent intent = new Intent(MainActivity.this, WeekSchedule.class);
                    startActivity(intent);
                } else if(item.equals("View Courses")){
                    Intent intent = new Intent(MainActivity.this, Listings.class);
                    startActivity(intent);
                } else if(item.equals("Completed Courses")){
                    Intent intent = new Intent(MainActivity.this, CompletedCoursesActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    public void goToSchedule(View v) {
        Intent intent = new Intent(this, WeekSchedule.class);
        startActivity(intent);
    }
}

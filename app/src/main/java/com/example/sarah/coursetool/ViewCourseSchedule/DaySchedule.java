package com.example.sarah.coursetool.ViewCourseSchedule;

import com.example.sarah.coursetool.BaseNavigationActivity;
import com.example.sarah.coursetool.CourseListing.CourseListing;
import com.example.sarah.coursetool.CourseListing.DataGenerator;
import com.example.sarah.coursetool.CourseListing.viewAdapter;
import com.example.sarah.coursetool.R;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
/**
 * The list of courses for a specific day
 *
 * @author  Sarah Abarbanel
 * @author  Lauchlan Toal
 * @since   2018-06-29
 */
public class DaySchedule extends BaseNavigationActivity  {

    DataGenerator dataGenerator = DataGenerator.getGenerator();
    ArrayList<CourseListing> dayData = new ArrayList<CourseListing>();
    Calendar day = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_schedule);
        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle == null) {
            day.setTime(new Date());
        } else if (bundle.getSerializable("week") != null) {
            day = (Calendar) bundle.getSerializable("week");
        } else {
            day.setTime(new Date());
        }
        dataGenerator.getDaySchedule(day, dayData);
        RecyclerView courseListContainer = (RecyclerView) findViewById(R.id.dayRecyclerView);
        courseListContainer.setHasFixedSize(true);
        RecyclerView.LayoutManager listContainerManager = new LinearLayoutManager(this);
        courseListContainer.setLayoutManager(listContainerManager);
        final RecyclerView.Adapter dayAdapter = new DayAdapter(dayData, getApplicationContext());
        courseListContainer.setAdapter(dayAdapter);
    }
}

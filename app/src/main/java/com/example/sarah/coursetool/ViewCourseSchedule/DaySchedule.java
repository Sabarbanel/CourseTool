package com.example.sarah.coursetool.ViewCourseSchedule;

import com.example.sarah.coursetool.BaseNavigationActivity;
import com.example.sarah.coursetool.CourseListing.CourseListing;
import com.example.sarah.coursetool.CourseListing.DataGenerator;
import com.example.sarah.coursetool.R;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

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
        } else if (bundle.getSerializable("date") != null) {
            day = (Calendar) bundle.getSerializable("date");
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

    public void nextDay(View v) {
        Intent intent = new Intent(this, DaySchedule.class);
        day.add(Calendar.DATE, 1);
        Bundle bundle = new Bundle();
        bundle.putSerializable("date", day);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void prevDay(View v) {
        Intent intent = new Intent(this, DaySchedule.class);
        day.add(Calendar.DATE, -1);
        Bundle bundle = new Bundle();
        bundle.putSerializable("date", day);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void returnWeek(View v) {
        Intent intent = new Intent(this, WeekSchedule.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("week", day);
        intent.putExtras(bundle);
        startActivity(intent);
    }

}

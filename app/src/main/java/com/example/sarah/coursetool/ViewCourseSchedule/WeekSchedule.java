package com.example.sarah.coursetool.ViewCourseSchedule;

import android.content.Intent;
import android.os.Bundle;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import java.util.Calendar;

import com.example.sarah.coursetool.BaseNavigationActivity;
import com.example.sarah.coursetool.CourseListing.CourseListing;
import com.example.sarah.coursetool.CourseListing.DataGenerator;
import com.example.sarah.coursetool.R;
/**
 * Activity that shows the weekly schedule
 *
 * @author  Sarah Abarbanel
 * @author  Lauchlan Toal
 * @since   2018-06-29
 */
public class WeekSchedule extends BaseNavigationActivity implements View.OnClickListener {
    Calendar weekDate = Calendar.getInstance();
    DataGenerator dataGenerator = DataGenerator.getGenerator();
    ArrayList<CourseListing> dayData = new ArrayList<CourseListing>();
    DateFormat formatter = new SimpleDateFormat("HH:mm");
    DateFormat headerFormatter = new SimpleDateFormat("MMM d");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week_schedule);
        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle == null) {
            weekDate.setTime(new Date());
        } else if (bundle.getSerializable("week") != null) {
            weekDate = (Calendar) bundle.getSerializable("week");
        } else {
            weekDate.setTime(new Date());
        }
        while (weekDate.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY) {
            weekDate.add(Calendar.DATE, -1);
        }
        TextView header = (TextView)findViewById(R.id.weekdatetextview);
        String headerText  ="Week of ";
        headerText += headerFormatter.format(weekDate.getTime());
        headerText += " ";
        weekDate.add(Calendar.DATE,6);
        headerText += headerFormatter.format(weekDate.getTime());
        weekDate.add(Calendar.DATE, -6);
        header.setText(headerText);
        Button monday = (Button)findViewById(R.id.monday);
        monday.setOnClickListener(this);
        weekDate.add(Calendar.DATE, 2);
        monday.setText("Monday " + dayTimes(weekDate));
        Button tuesday = (Button)findViewById(R.id.tuesday);
        tuesday.setOnClickListener(this);
        weekDate.add(Calendar.DATE, 1);
        tuesday.setText("Tuesday " + dayTimes(weekDate));
        Button wednesday = (Button)findViewById(R.id.wednesday);
        wednesday.setOnClickListener(this);
        weekDate.add(Calendar.DATE, 1);
        wednesday.setText("Wednesday " + dayTimes(weekDate));
        Button thursday = (Button)findViewById(R.id.thursday);
        thursday.setOnClickListener(this);
        weekDate.add(Calendar.DATE, 1);
        thursday.setText("Thursday " + dayTimes(weekDate));
        Button friday = (Button)findViewById(R.id.friday);
        friday.setOnClickListener(this);
        weekDate.add(Calendar.DATE, 1);
        friday.setText("Friday " + dayTimes(weekDate));
        weekDate.add(Calendar.DATE, -6);

    }

    private String dayTimes(Calendar cal) {
        dataGenerator.getDaySchedule(cal, dayData);
        String dayTimeline = "";
        if(dayData.size() != 0) {
            dayTimeline += formatter.format(dayData.get(0).courseStartTime);
            dayTimeline += " - ";
            dayTimeline += formatter.format(dayData.get(dayData.size()-1).courseEndTime);
        } else {
            dayTimeline = "FREE DAY!";
        }
        return dayTimeline;
    }

    @Override
    public void onClick(View v) {
        while (weekDate.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY) {
            weekDate.add(Calendar.DATE, -1);
        }
        int offset = 0;
        switch(v.getId()) {
            case R.id.monday:
                offset = 2;
                break;
            case R.id.tuesday:
                offset = 3;
                break;
            case R.id.wednesday:
                offset = 4;
                break;
            case R.id.thursday:
                offset = 5;
                break;
            case R.id.friday:
                offset = 6;
                break;
            default:
                offset = 2;
                break;
        }
        weekDate.add(Calendar.DATE, offset);
        Intent intent = new Intent(this, DaySchedule.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("date", weekDate);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void nextWeek(View v) {
        Intent intent = new Intent(this, WeekSchedule.class);
        weekDate.add(Calendar.DATE, 7);
        Bundle bundle = new Bundle();
        bundle.putSerializable("week", weekDate);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void prevWeek(View v) {
        Intent intent = new Intent(this, WeekSchedule.class);
        weekDate.add(Calendar.DATE, -7);
        Bundle bundle = new Bundle();
        bundle.putSerializable("week", weekDate);
        intent.putExtras(bundle);
        startActivity(intent);
    }

}
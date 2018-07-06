package com.example.sarah.coursetool.ViewCourseSchedule;

import android.support.v7.widget.RecyclerView;
import java.util.ArrayList;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import java.util.Collections;
import java.util.List;
import android.util.Log;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.example.sarah.coursetool.CourseListing.CourseListing;
import com.example.sarah.coursetool.R;

public class DayAdapter extends RecyclerView.Adapter<DayHolder> {

    ArrayList<CourseListing> dayData = new ArrayList<CourseListing>();
    Context context;
    DateFormat formatter = new SimpleDateFormat("hh:mm");

    public DayAdapter(ArrayList<CourseListing> someData, Context newContext){
        dayData = someData;
        context = newContext;
    }

    @Override
    public DayHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout, parent, false);
        DayHolder holder = new DayHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(final DayHolder someHolder, int i){
        someHolder.courseTitle.setText(dayData.get(i).courseTitle);
        someHolder.courseProf.setText(dayData.get(i).courseProf);
        someHolder.courseStart.setText(formatter.format(dayData.get(i).courseStartTime));
        someHolder.courseEnd.setText(formatter.format(dayData.get(i).courseEndTime));
        someHolder.courseRoom.setText(Integer.toString(dayData.get(i).courseRoom));

    }

    @Override
    public int getItemCount() {
        return dayData.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recycler){
        super.onAttachedToRecyclerView(recycler);
    }

    public void insert(int position, CourseListing entry){
        dayData.add(position, entry);
        notifyItemInserted(position);
    }

    public void remove(CourseListing entry) {
        int position = dayData.indexOf(entry);
        dayData.remove(entry);
        notifyItemRemoved(position);
    }

}


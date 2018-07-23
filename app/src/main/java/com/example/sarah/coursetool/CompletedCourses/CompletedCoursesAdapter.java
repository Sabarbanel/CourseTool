package com.example.sarah.coursetool.CompletedCourses;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sarah.coursetool.CourseListing.CourseListing;
import com.example.sarah.coursetool.R;
import com.example.sarah.coursetool.ViewCourseSchedule.DayHolder;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class CompletedCoursesAdapter extends RecyclerView.Adapter<CompletedCoursesHolder> {

    ArrayList<CourseListing> completeData = new ArrayList<CourseListing>();
    Context context;
    DateFormat formatter = new SimpleDateFormat("hh:mm");

    public CompletedCoursesAdapter(ArrayList<CourseListing> someData, Context newContext){
        completeData = someData;
        context = newContext;
    }

    @Override
    public CompletedCoursesHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.completed_course_card, parent, false);
        CompletedCoursesHolder holder = new CompletedCoursesHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(final CompletedCoursesHolder someHolder, int i){
        someHolder.courseTitle.setText(completeData.get(i).courseTitle);
        someHolder.courseProf.setText(completeData.get(i).courseProf);
        someHolder.courseEnd.setText(formatter.format(completeData.get(i).courseEndTime));
        int grade = completeData.get(i).courseGrade;
        String gradeText;
        if (grade == -1) {
            gradeText = "IP";
        } else {
            gradeText = Integer.toString(grade);
        }
        someHolder.courseGrade.setText(gradeText);

    }

    @Override
    public int getItemCount() {
        return completeData.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recycler){
        super.onAttachedToRecyclerView(recycler);
    }
}

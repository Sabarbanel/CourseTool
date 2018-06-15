package com.example.sarah.coursetool.CourseListing;

import android.support.v7.widget.RecyclerView;
import java.util.ArrayList;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import java.util.Collections;
import java.util.List;
import android.util.Log;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


import com.example.sarah.coursetool.R;

public class viewAdapter extends RecyclerView.Adapter<CourseHolder> {

    //Benefitted from https://www.sitepoint.com/mastering-complex-lists-with-the-android-recyclerview/

    ArrayList<CourseListing> inputData = new ArrayList<CourseListing>();
    Context context;
    DateFormat formatter = new SimpleDateFormat("MMMM-dd hh:mm");

    public viewAdapter(ArrayList<CourseListing> someData, Context newContext){
        inputData = someData;
        context = newContext;
    }

    @Override
    public CourseHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout, parent, false);
        CourseHolder holder = new CourseHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(CourseHolder someHolder, int i){
        someHolder.courseTitle.setText(inputData.get(i).courseTitle);
        someHolder.courseProf.setText(inputData.get(i).courseProf);
        someHolder.courseDept.setText(inputData.get(i).courseDepartment);
        someHolder.courseDesc.setText(inputData.get(i).courseDescription);
        someHolder.courseStart.setText(formatter.format(inputData.get(i).courseStartTime));
        someHolder.courseEnd.setText(formatter.format(inputData.get(i).courseEndTime));
        someHolder.courseId.setText(Integer.toString(inputData.get(i).courseID));
        someHolder.courseRoom.setText(Integer.toString(inputData.get(i).courseRoom));
        String prereqs = "";
        for(Integer x:inputData.get(i).coursePreqs){
            prereqs += " ,";
            prereqs += Integer.toString(x);
        }
        if(prereqs.length() > 1){
            prereqs = prereqs.substring(2);
        }
        if(prereqs.equals("")){
            prereqs = "None";
        }
        someHolder.coursePrereqs.setText(prereqs);
    }

    @Override
    public int getItemCount() {
        return inputData.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recycler){
        super.onAttachedToRecyclerView(recycler);
    }

    public void insert(int position, CourseListing entry){
        inputData.add(position, entry);
        notifyItemInserted(position);
    }

    public void remove(CourseListing entry){
        int position = inputData.indexOf(entry);
        inputData.remove(entry);
        notifyItemRemoved(position);
    }

}

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

import com.example.sarah.coursetool.R;

public class viewAdapter extends RecyclerView.Adapter<CourseHolder> {

    //Benefitted from https://www.sitepoint.com/mastering-complex-lists-with-the-android-recyclerview/

    ArrayList<Listing> inputData = new ArrayList<Listing>();
    Context context;

    public viewAdapter(ArrayList<Listing> someData, Context newContext){
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
        someHolder.title.setText(inputData.get(i).title);
        someHolder.description.setText(inputData.get(i).description);
        someHolder.imageView.setText(Integer.toString(inputData.get(i).imageId));
    }

    @Override
    public int getItemCount() {
        return inputData.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recycler){
        super.onAttachedToRecyclerView(recycler);
    }

    public void insert(int position, Listing entry){
        inputData.add(position, entry);
        notifyItemInserted(position);
    }

    public void remove(Listing entry){
        int position = inputData.indexOf(entry);
        inputData.remove(entry);
        notifyItemRemoved(position);
    }

}

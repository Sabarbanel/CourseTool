package com.example.sarah.coursetool.CourseListing;

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

import com.example.sarah.coursetool.R;

public class viewAdapter extends RecyclerView.Adapter<CourseHolder> {

    //Benefitted from https://www.sitepoint.com/mastering-complex-lists-with-the-android-recyclerview/
    // and https://stackoverflow.com/questions/39218567/how-to-add-setonclicklistener-for-each-item-in-cardview
    // and https://stackoverflow.com/questions/39218567/how-to-add-setonclicklistener-for-each-item-in-cardview
    // and https://stackoverflow.com/questions/2271570/android-findviewbyid-finding-view-by-id-when-view-is-not-on-the-same-layout-in
    // and https://stackoverflow.com/questions/31301470/cannot-resolve-method-getlayoutinflater

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
    public void onBindViewHolder(final CourseHolder someHolder, int i){
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
        someHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Hello",(String)someHolder.courseTitle.getText());
                //LayoutInflater layoutSeed = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                //View baseView = layoutSeed.inflate(R.layout.activity_listings, null, false);
                //RelativeLayout popup = (RelativeLayout) v.findViewById(R.id.courseInfoContainer);
                //Log.d("Hello",Integer.toString(popup.getVisibility()));
                //popup.setVisibility(View.VISIBLE);
                //v.postInvalidate();
                //Log.d("Hello",Integer.toString(popup.getVisibility()));
                //popup.bringToFront();
                ViewGroup g = (ViewGroup) v.getParent().getParent().getParent().getParent().getParent();
                //Log.d("Hello", g.getTag().toString());
                //Log.d("Hello", Integer.toString(g.getChildCount()));
                View x = g.getChildAt(2);
                //Log.d("Hello",x.getTag().toString());
                ViewGroup y = (ViewGroup) x;
                TextView title = (TextView) y.getChildAt(1);
                TextView tt = (TextView) v.findViewById(R.id.courseTitle);
                title.setText(tt.getText());
                x.setVisibility(View.VISIBLE);


            }
        });

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

    public void remove(CourseListing entry) {
        int position = inputData.indexOf(entry);
        inputData.remove(entry);
        notifyItemRemoved(position);
    }

}

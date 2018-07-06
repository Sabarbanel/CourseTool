/**
 * @author: Noah S and Lauchlan
 * CourseHolder implements the RecyclerView.ViewHolder interface
 * It's used as a template for the course listings displayed.
 */
package com.example.sarah.coursetool.CourseListing;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.util.Log;

import com.example.sarah.coursetool.R;

public class CourseHolder extends RecyclerView.ViewHolder{
    CardView cv;
    TextView courseTitle;
    TextView courseProf;
    TextView courseDept;
    TextView courseDesc;
    TextView courseStart;
    TextView courseEnd;
    TextView courseId;
    TextView courseRoom;
    TextView coursePrereqs;


    CourseHolder(View itemView){
        super(itemView);
        cv = (CardView) itemView.findViewById(R.id.cardView);
        courseTitle = (TextView) itemView.findViewById(R.id.courseTitle);
        courseProf = (TextView) itemView.findViewById(R.id.courseProf);
        courseDept = (TextView) itemView.findViewById(R.id.courseDept);
        courseDesc = (TextView) itemView.findViewById(R.id.courseDesc);
        courseStart = (TextView) itemView.findViewById(R.id.courseStart);
        courseEnd = (TextView) itemView.findViewById(R.id.courseEnd);
        courseId = (TextView) itemView.findViewById(R.id.courseId);
        courseRoom = (TextView) itemView.findViewById(R.id.courseRoom);
        coursePrereqs = (TextView) itemView.findViewById(R.id.coursePrereqs);
    }



}

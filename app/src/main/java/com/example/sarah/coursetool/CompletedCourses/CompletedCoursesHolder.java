package com.example.sarah.coursetool.CompletedCourses;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.util.Log;

import com.example.sarah.coursetool.R;
/**
 * The template for the completed course entry of a course
 *
 * @author  Noah Attwood
 * @author  Lauchlan Toal
 * @since   2018-07-18
 */
public class CompletedCoursesHolder extends RecyclerView.ViewHolder{
    CardView cv;
    TextView courseTitle;
    TextView courseProf;
    TextView courseEnd;
    TextView courseGrade;


    CompletedCoursesHolder(View itemView){
        super(itemView);
        cv = (CardView) itemView.findViewById(R.id.cardCompletedCoursesView);
        courseTitle = (TextView) itemView.findViewById(R.id.courseCompletedTitle);
        courseProf = (TextView) itemView.findViewById(R.id.courseCompletedProf);
        courseEnd = (TextView) itemView.findViewById(R.id.courseCompletedEnd);
        courseGrade = (TextView) itemView.findViewById(R.id.courseCompletedGrade);
    }
}
package com.example.sarah.coursetool.ViewCourseSchedule;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.util.Log;

import com.example.sarah.coursetool.R;
/**
 * The template for the dayview entry of a course and time
 *
 * @author  Sarah Abarbanel
 * @author  Lauchlan Toal
 * @since   2018-07-06
 */
public class DayHolder extends RecyclerView.ViewHolder{
    CardView cv;
    TextView courseTitle;
    TextView courseProf;
    TextView courseStart;
    TextView courseEnd;
    TextView courseRoom;


    DayHolder(View itemView){
        super(itemView);
        cv = (CardView) itemView.findViewById(R.id.cardDayView);
        courseTitle = (TextView) itemView.findViewById(R.id.courseDayTitle);
        courseProf = (TextView) itemView.findViewById(R.id.courseDayProf);
        courseStart = (TextView) itemView.findViewById(R.id.courseDayStart);
        courseEnd = (TextView) itemView.findViewById(R.id.courseDayEnd);
        courseRoom = (TextView) itemView.findViewById(R.id.courseDayRoom);
    }



}
